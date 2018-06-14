package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.trustnote.activity.common.example.FinancialLockUpExample;
import org.trustnote.activity.common.pojo.BalanceEntity;
import org.trustnote.activity.common.pojo.Financial;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.common.pojo.FinancialLockUp;
import org.trustnote.activity.common.utils.DateTimeUtils;
import org.trustnote.activity.service.iface.FinancialBenefitsService;
import org.trustnote.activity.service.iface.FinancialLockUpService;
import org.trustnote.activity.service.iface.FinancialService;
import org.trustnote.activity.skeleton.mybatis.mapper.FinancialBenefitsMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.FinancialLockUpMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl
 */
@Service
public class FinancialLockUpServiceImpl implements FinancialLockUpService {
    private static final Logger logger = LogManager.getLogger(FinancialLockUpServiceImpl.class);

    @Resource
    private FinancialLockUpMapper financialLockUpMapper;
    @Resource
    private FinancialBenefitsMapper financialBenefitsMapper;
    @Resource
    private FinancialBenefitsService financialBenefitsService;
    @Resource
    private FinancialService financialService;

    @Override
    public List<FinancialLockUp> queryFinancialLockUp(final Page<FinancialLockUp> page, final int benefitsId) throws Exception {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialBenefitsIdEqualTo(benefitsId);
        example.setOrderByClause("crt_time DESC");
        return this.convert(this.financialLockUpMapper.selectByExamplePage(page, new FinancialLockUpExample()));
    }

    @Override
    public int saveFinancialLockUp(final FinancialLockUp financialLockUp) throws Exception {
        financialLockUp.setIncomeAmount(null);
        financialLockUp.setLockUpAmount(null);
        financialLockUp.setOperationTime(LocalDateTime.now());
        return this.financialLockUpMapper.insertSelective(financialLockUp);
    }

    @Override
    public List<FinancialLockUp> queryFincialLockUpByDeviceAddress(final String deviceAddress) throws Exception {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andDeviceAddressEqualTo(deviceAddress);
        return this.convert(this.financialLockUpMapper.selectByExample(example));
    }

    @Override
    public int updateFinancialLockUp(final int id, final int orderAmount) throws Exception {
        final FinancialLockUp financialLockUp = new FinancialLockUp();
        financialLockUp.setId(id);
        financialLockUp.setOrderAmount(orderAmount);
        financialLockUp.setOperationTime(LocalDateTime.now());
        return this.financialLockUpMapper.updateByPrimaryKeySelective(financialLockUp);
    }

    /**
     * 根据设备地址、合约地址、产品ID查询合约信息
     *
     * @param financialLockUp
     * @return
     * @throws Exception
     */
    @Override
    public FinancialLockUp queryLockUp(final FinancialLockUp financialLockUp) throws Exception {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andSharedAddressEqualTo(financialLockUp.getSharedAddress());
        criteria.andDeviceAddressEqualTo(financialLockUp.getDeviceAddress());
        criteria.andFinancialBenefitsIdEqualTo(financialLockUp.getFinancialBenefitsId());
        final List<FinancialLockUp> financialLockUps = this.financialLockUpMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(financialLockUps)) {
            return financialLockUps.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, String>> export(final int benefitsId) throws Exception {
        final Page<FinancialLockUp> page = new Page<>(1, Integer.MAX_VALUE);
        final List<FinancialLockUp> financialLockUps = this.queryFinancialLockUp(page, benefitsId);
        final List<FinancialLockUp> lastFi = this.convert(financialLockUps);
        final List<Map<String, String>> contents = new ArrayList<>();
        for (final FinancialLockUp financialLockUp : lastFi) {
            final Map<String, String> map = new HashMap<>();
            map.put("0", financialLockUp.getSharedAddress());
            map.put("1", financialLockUp.getLockUpAmount().toString());
            map.put("2", financialLockUp.getIncomeAmount() == null ? "" : financialLockUp.getIncomeAmount().toString());
            map.put("3", financialLockUp.getLockUpStatus());
            map.put("4", DateTimeUtils.formatDateTime(financialLockUp.getOperationTime(), "yyyy-MM-dd HH:mm:ss"));
            contents.add(map);
        }
        return contents;
    }

    @Override
    public void saveInComeAmount() {
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算收益开始---------------------------------");
        //第一步 先获取计划收益开始后并且未计算收益的产品；
        final LocalDateTime now = LocalDateTime.now();
        final List<FinancialBenefits> benefits;
        try {
            benefits = this.financialBenefitsService.queryFinancialInterestGreaterThanNow(now);
        } catch (final Exception e) {
            FinancialLockUpServiceImpl.logger.error("获取未计算收益的产品异常： {}", e);
            return;
        }
        FinancialLockUpServiceImpl.logger.info("未计算的收益产品数量为: {}", benefits.size());
        int index = 1;
        for (final FinancialBenefits financialBenefits : benefits) {
            //第二步 根据financial_benefits_id查询lock_up_amount不为空的合约地址计算收益
            //首先 查询当前产品所属的套餐，得到收益率
            FinancialLockUpServiceImpl.logger.info("开始计算第{}个产品，产品ID为:{}", index, financialBenefits.getId());
            Financial financial;
            try {
                financial = this.financialService.queryOneFinancial(financialBenefits.getFinancialId());
            } catch (final Exception e) {
                FinancialLockUpServiceImpl.logger.error("获取年化收益率异常： {}", e);
                financial = null;
            }
            if (financial == null) {
                continue;
            }
            final FinancialLockUpExample example = new FinancialLockUpExample();
            final FinancialLockUpExample.Criteria criteria = example.createCriteria();
            criteria.andFinancialBenefitsIdEqualTo(financialBenefits.getId());
            criteria.andLockUpAmountIsNotNull();
            final List<FinancialLockUp> financialLockUps = this.financialLockUpMapper.selectByExample(example);
            FinancialLockUpServiceImpl.logger.info("获取当前产品下的合约地址信息数量为: {}", financialLockUps.size());
            final int j = 1;
            for (final FinancialLockUp financialLockUp : financialLockUps) {
                FinancialLockUpServiceImpl.logger.info("开始计算第{}个合约，合约ID为:{}", j, financialLockUp.getId());
                //本金
                final BigDecimal principal = financialLockUp.getLockUpAmount();
                //理财周期
                final BigDecimal numericalv = BigDecimal.valueOf(financial.getNumericalv());
                //年化利率
                final BigDecimal rate = BigDecimal.valueOf(financial.getFinancialRate());
                //计算收益
                final BigDecimal all = principal.multiply(numericalv).multiply(rate);
                final BigDecimal income = all.divide(new BigDecimal(360), 6, BigDecimal.ROUND_DOWN);
                FinancialLockUpServiceImpl.logger.info("本金：{} 理财周期： {} 年化利率： {} 收益： {}", principal, numericalv, rate, income);
                final FinancialLockUp upLockUp = new FinancialLockUp();
                upLockUp.setId(financialLockUp.getId());
                upLockUp.setIncomeAmount(income);

                try {
                    this.financialLockUpMapper.updateByPrimaryKeySelective(upLockUp);
                } catch (final Exception e) {
                    FinancialLockUpServiceImpl.logger.error("计算收益金额异常: {}", e);
                    continue;
                }
            }
            final FinancialBenefits upBenefits = FinancialBenefits.builder()
                    .id(financialBenefits.getId())
                    .calactionStatus(1)
                    .build();
            try {
                this.financialBenefitsMapper.updateByPrimaryKeySelective(upBenefits);
            } catch (final Exception e) {
                FinancialLockUpServiceImpl.logger.error("更新产品的是否以计算收益状态异常: {}", e);
                continue;
            }
        }
        index++;
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算收益结束---------------------------------");
    }

    @Override
    public void validationPayment() {
        FinancialLockUpServiceImpl.logger.info("-----------------------------------验证开始---------------------------------");
        final StringBuilder sb = new StringBuilder("http://localhost:3000/checkbalance?address=");
        //第一步 只过滤30分钟之内的交易
        final LocalDateTime now = LocalDateTime.now();
        final List<FinancialLockUp> financialLockUps;
        try {
            financialLockUps = this.queryWithinThirtyMinutes(now);
        } catch (final Exception e) {
            FinancialLockUpServiceImpl.logger.error("获取30分钟内的合约地址数据异常： {}", e);
            return;
        }
        for (final FinancialLockUp financialLockUp : financialLockUps) {
            final FinancialBenefits financialBenefits = this.financialBenefitsMapper.selectByPrimaryKey(financialLockUp.getFinancialBenefitsId());
            final Financial financial;
            try {
                financial = this.financialService.queryOneFinancial(financialBenefits.getFinancialId());
            } catch (final Exception e) {
                FinancialLockUpServiceImpl.logger.info("获取套餐异常： {}", e);
                continue;
            }
            sb.append(financialLockUp.getSharedAddress().trim());
            // 根据地址获取请求
            final HttpGet request = new HttpGet(sb.toString());
            // 获取当前客户端对象
            final CloseableHttpClient httpClient = HttpClients.createDefault();
            try (final CloseableHttpResponse httpResponse = httpClient.execute(request)) {
                // 判断网络连接状态码是否正常(0--200都数正常)
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    final String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                    final JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
                    final BalanceEntity data = jsonObject.getObject("data", BalanceEntity.class);
                    final BigDecimal currentAmount = data.getAll_balance().divide(new BigDecimal(1000000));
                    BigDecimal lockUpAmount = new BigDecimal(0);
                    if (currentAmount.compareTo(new BigDecimal(financialBenefits.getMinAmount())) != -1) {
                        if (financial.getId() == 1) {
                            if (currentAmount.compareTo(new BigDecimal(financialBenefits.getPurchaseLimit())) != -1) {
                                lockUpAmount = new BigDecimal(financialBenefits.getPurchaseLimit());
                            } else {
                                lockUpAmount = currentAmount;
                            }
                        } else {
                            lockUpAmount = currentAmount;
                        }
                    }
                    if (lockUpAmount.compareTo(new BigDecimal(0)) == 1) {
                        final FinancialLockUp record = new FinancialLockUp();
                        record.setId(financialLockUp.getId());
                        record.setLockUpAmount(lockUpAmount);
                        this.financialLockUpMapper.updateByPrimaryKeySelective(record);
                        //计算剩余额度
                        final FinancialBenefits fbRecord = FinancialBenefits.builder()
                                .id(financialBenefits.getId())
                                .remainLimit(financialBenefits.getRemainLimit().subtract(lockUpAmount))
                                .build();
                        this.financialBenefitsMapper.updateByPrimaryKeySelective(fbRecord);
                    }
                }
            } catch (final IOException e) {
                FinancialLockUpServiceImpl.logger.info("调用nodejs异常： {}", e);
                continue;
            }
        }
        FinancialLockUpServiceImpl.logger.info("-----------------------------------验证结束---------------------------------");
    }

    @Override
    public Map<String, BigDecimal> participate() throws Exception {
        return this.financialLockUpMapper.participate();
    }

    private List<FinancialLockUp> convert(final List<FinancialLockUp> financialLockUps) {
        final LocalDateTime now = LocalDateTime.now();
        for (final FinancialLockUp financialLockUp : financialLockUps) {
            final FinancialBenefits financialBenefits = this.financialBenefitsMapper.selectByPrimaryKey(financialLockUp.getFinancialBenefitsId());
            if (financialBenefits == null) {
                financialLockUp.setLockUpStatus("");
                continue;
            }
            final LocalDateTime unLockTime = financialBenefits.getUnlockTime();
            if (now.isAfter(unLockTime)) {
                financialLockUp.setLockUpStatus("已解锁");
            } else {
                financialLockUp.setLockUpStatus("未解锁");
            }
        }
        return financialLockUps;
    }

    private List<FinancialLockUp> queryWithinThirtyMinutes(final LocalDateTime now) throws Exception {
        final LocalDateTime beginTime = now.minusMinutes(30);
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andOperationTimeGreaterThan(beginTime);
        return this.financialLockUpMapper.selectByExample(example);
    }
}
