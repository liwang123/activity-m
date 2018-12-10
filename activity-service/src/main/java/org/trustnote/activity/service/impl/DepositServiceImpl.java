package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.dto.DepositLockDTO;
import org.trustnote.activity.common.enume.LockStatesEnum;
import org.trustnote.activity.common.pojo.DepositLock;
import org.trustnote.activity.common.pojo.GenerateAddress;
import org.trustnote.activity.common.utils.OkHttpUtils;
import org.trustnote.activity.service.iface.DepositService;
import org.trustnote.activity.skeleton.mybatis.mapper.DepositLockMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.GenerateAddressMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class DepositServiceImpl implements DepositService {
    private static final Logger logger = LogManager.getLogger(DepositServiceImpl.class);

    @Autowired
    private DepositLockMapper depositLockMapper;
    @Autowired
    private GenerateAddressMapper generateAddressMapper;

    @Override
    public String insert(final String address, final int status, final String publicKey) {
        final DepositLock depositLock = DepositLock.builder()
                .packageType(status)
                .publicKey(publicKey)
                .receiptAddress(address)
                .lockStatus(LockStatesEnum.PENDING_LOCK.getCode())
                .lockTime(LocalDateTime.now())
                .lockAmount(new BigDecimal(0))
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        final List<GenerateAddress> generateAddressList = this.generateAddressMapper.selectByExample(null);
        final GenerateAddress generateAddress = generateAddressList.get(0);
        depositLock.setWalletAddress(generateAddress.getAddress());
        this.depositLockMapper.insert(depositLock);
        generateAddress.setStatus(2);
        this.generateAddressMapper.updateByPrimaryKey(generateAddress);
        return generateAddress.getAddress();
    }

    @Override
    public boolean querybalance(final String address) {
        final BigDecimal dataBigDecimal = this.query(address);
        if (dataBigDecimal.compareTo(new BigDecimal(0)) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void queryPayed(final List<DepositLock> depositLockList) {
        depositLockList.stream()
                .forEach(depositLock -> {
                    final BigDecimal dataBigDecimal = this.query(depositLock.getWalletAddress());
                    depositLock.setLockAmount(dataBigDecimal);
                    final Integer packageType = depositLock.getPackageType();
                    if (dataBigDecimal.compareTo(new BigDecimal(0)) != 0 && dataBigDecimal.compareTo(new BigDecimal(25000)) != -1) {
                        BigDecimal rate = null;
                        BigDecimal numericalv = null;
                        BigDecimal tfs = null;

                        if (packageType == 1) {
                            rate = new BigDecimal(0.3);
                            numericalv = new BigDecimal(180);
                            tfs = new BigDecimal(30);
                        } else {
                            rate = new BigDecimal(0.4);
                            numericalv = new BigDecimal(360);
                            tfs = new BigDecimal(40);
                        }
                        //计算收益
                        final BigDecimal all = dataBigDecimal.multiply(numericalv).multiply(rate);
                        final BigDecimal income = all.divide(new BigDecimal(360), 1, BigDecimal.ROUND_DOWN);
                        final BigDecimal tfans = income.multiply(tfs);
                        depositLock.setIncomeAmount(income);
                        depositLock.setTfsAmount(tfans);
                        depositLock.setLockStatus(LockStatesEnum.LOCKING.getCode());
                        depositLock.setReturnAmount(income.add(dataBigDecimal));
                        depositLock.setLockTime(LocalDateTime.now());
                        depositLock.setUpdateTime(LocalDateTime.now());
                    }
                    depositLock.setLockAmount(dataBigDecimal);
                    depositLock.setUnlockTime(depositLock.getLockTime().plusDays(packageType == 1 ? 180 : 360));
                    this.depositLockMapper.updateByPrimaryKeySelective(depositLock);
                });
    }

    @Override
    public Page<DepositLockDTO> queryAll(final int startIndex, final int pageSize) {
        final Page<DepositLockDTO> page = new Page<>();
        final List<DepositLock> depositLockList = this.depositLockMapper.selectByPage((startIndex - 1) * pageSize, pageSize);
        final List<DepositLockDTO> depositLockDTOArrayList = new ArrayList<>();
        depositLockList.stream()
                .forEach(depositLock -> {
                    final DepositLockDTO depositLockDTO = DepositLockDTO.builder().build();
                    BeanUtils.copyProperties(depositLock, depositLockDTO);
                    depositLockDTO.setLockStatus(LockStatesEnum.getName(depositLock.getLockStatus()));
                    depositLockDTO.setLockTime(depositLock.getLockTime().toString().replaceAll("T", " "));
                    depositLockDTO.setUnlockTime(depositLock.getUnlockTime() == null ? null : depositLock.getUnlockTime().toString().replaceAll("T", " "));
                    depositLockDTO.setPackageType(depositLock.getPackageType() == 1 ? "180天" : "360天");
                    depositLockDTOArrayList.add(depositLockDTO);
                });
        final int count = this.depositLockMapper.countByExample(null);
        page.setResult(depositLockDTOArrayList);
        page.setTotalCount(count);
        return page;
    }

    public BigDecimal query(final String address) {
        final String url = "http://150.109.57.242:6002/api/v1/asset/balance/" + address + "/TTT";
        final String result = OkHttpUtils.get(url, null);
        final JSONObject parse = (JSONObject) JSONObject.parse(result);
        final JSONObject data = parse.getJSONObject("data");
        BigDecimal dataBigDecimal = data.getBigDecimal("pending");
        if (dataBigDecimal.compareTo(new BigDecimal(0)) == 0) {
            dataBigDecimal = data.getBigDecimal("stable");
        }
        return dataBigDecimal.divide(new BigDecimal(1000000));
    }
}
