package org.trustnote.activity.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.dto.ExchangeOrderDTO;
import org.trustnote.activity.common.model.ResponseResult;
import org.trustnote.activity.common.pojo.CheckAccount;
import org.trustnote.activity.common.pojo.ExchangeOrder;
import org.trustnote.activity.common.utils.Sending;
import org.trustnote.activity.common.utils.SendingPool;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.mapper.CheckAccountMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeOrderMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ExchangeOrderImpl implements ExchangeOrderService {
    @Autowired
    private ExchangeOrderMapper exchangeOrderMapper;

    @Autowired
    private CheckAccountMapper checkAccountMapper;


    @Override
    public synchronized void insertExchangeOrder(final ExchangeOrderDTO exchangeOrderDTO) {
        final ExchangeOrder exchangeOrder = new ExchangeOrder();
        BeanUtils.copyProperties(exchangeOrderDTO, exchangeOrder);
        this.exchangeOrderMapper.insertSelective(exchangeOrder);
        if (exchangeOrder.getStates() == 4 && exchangeOrderDTO.getReceipt().compareTo(new BigDecimal(0.5)) == -1) {
            this.addRecord(exchangeOrder);
        }
        if (exchangeOrderDTO.getStates() != 1 && exchangeOrderDTO.getStates() != 4 || exchangeOrderDTO.getReceipt().compareTo(new BigDecimal(0.5)) == 0) {
            this.sendMail(exchangeOrder);
        }
    }

    @Override
    public Page getAllOrder(final int pageIndex, final int pageSize, final int status) {
        final Page<ExchangeOrder> page = new Page<>(pageIndex, pageSize);
        page.setResult(this.exchangeOrderMapper.selectByPage((pageIndex - 1) * pageSize, pageSize, status));
        page.setTotalCount(this.exchangeOrderMapper.countByOrder(status));
        return page;
    }

    @Override
    public ResponseResult manualMoney(final Long id) {
        final ExchangeOrder exchangeOrder = this.exchangeOrderMapper.selectByPrimaryKey(id);
        if (exchangeOrder.getCreateTime().plusMinutes(10).isBefore(LocalDateTime.now())) {
            exchangeOrder.setRate(new BigDecimal(1));
        }
        //调取打款接口

        this.addRecord(exchangeOrder);
        //调取推送设备信息接口
        

        return ResponseResult.success();
    }


    private void addRecord(final ExchangeOrder exchangeOrder) {
        final CheckAccount checkAccount = CheckAccount.builder()
                .exchangeId(exchangeOrder.getId())
                .fromAddress(exchangeOrder.getFromAddress())
                .toAddress(exchangeOrder.getToAddress())
                .amount(exchangeOrder.getQuantity())
                .build();
        this.checkAccountMapper.insertSelective(checkAccount);
    }

    private void sendMail(final ExchangeOrder exchangeOrder) {
        final SendingPool pool = SendingPool.getInstance();
        pool.addThread(new Sending("13333611437@qq.com", "TrustNote email", "有订单未处理"));
    }
}
