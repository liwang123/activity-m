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
        return this.convert(this.financialLockUpMapper.selectByExamplePage(page, new FinancialLockUpExample()), 0);
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
        return this.convert(this.financialLockUpMapper.selectByExample(example), 1);
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
        final List<FinancialLockUp> lastFi = this.convert(financialLockUps, 0);
        final List<Map<String, String>> contents = new ArrayList<>();
        for (final FinancialLockUp financialLockUp : lastFi) {
            final Map<String, String> map = new HashMap<>(5);
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
            //首先 查询当前产品所属的套餐，得到理财周期
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
                final BigDecimal rate = BigDecimal.valueOf(financialBenefits.getFinancialRate()).setScale(2, BigDecimal.ROUND_DOWN);
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
    public void validationPaymentWeek() {
        final long start = System.currentTimeMillis();
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算周套餐temp_amount开始---------------------------------");
        final StringBuilder sb = new StringBuilder("http://localhost:3000/checkbalance?address=");
        final LocalDateTime now = LocalDateTime.now();
        //第一步 只查询　在抢购时间段内的周产品
        List<FinancialBenefits> financialBenefits = null;
        try {
            financialBenefits = this.financialBenefitsService.queryFinancialInPanic(now, 1, 0);
        } catch (final Exception e) {
            FinancialLockUpServiceImpl.logger.info("查询抢购时间段内的产品异常： {}", e);
        }
        this.toDoLockUps(financialBenefits, sb);
        FinancialLockUpServiceImpl.logger.info("用时: {}", System.currentTimeMillis() - start);
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算周套餐temp_amount结束---------------------------------");
    }

    /**
     * 验证payment 第二步
     *
     * @param financialBenefits
     * @param sb
     */
    private void toDoLockUps(final List<FinancialBenefits> financialBenefits, final StringBuilder sb) {
        //第二步　遍历产品，取出产品下的合约查询金额
        for (final FinancialBenefits benefits : financialBenefits) {
            final List<FinancialLockUp> financialLockUps = this.queryLockUpByBenefitId(benefits.getId());
            for (final FinancialLockUp financialLockUp : financialLockUps) {
                sb.append(financialLockUp.getSharedAddress().trim());
                final BalanceEntity data = this.balanceGet(sb);
                if (data == null) {
                    continue;
                }
                FinancialLockUpServiceImpl.logger.info("解析data: {}", data);
                final BigDecimal currentAmount = data.getCurrent_balance().divide(new BigDecimal(1000000));
                final BigDecimal tempAmount = new BigDecimal(0);
                FinancialLockUpServiceImpl.logger.info("合约地址当前余额: {}, 起购额度: {}", currentAmount, benefits.getMinAmount());
                this.updateTempAmount(tempAmount, financialLockUp.getId());
            }
            final BigDecimal sumLockUpAmount = this.financialLockUpMapper.sumTempAmount(benefits.getId());
            BigDecimal remainLimit = new BigDecimal(0);
            if (sumLockUpAmount != null) {
                if (benefits.getPanicTotalLimit() != null) {
                    remainLimit = new BigDecimal(benefits.getPanicTotalLimit()).subtract(sumLockUpAmount);
                }
                FinancialLockUpServiceImpl.logger.info("总额度: {}, 最新剩余额度: {}", benefits.getPanicTotalLimit(), remainLimit);
                //计算剩余额度
                final FinancialBenefits fbRecord = FinancialBenefits.builder()
                        .id(benefits.getId())
                        .remainLimit(remainLimit)
                        .alsoTempAmount(sumLockUpAmount)
                        .build();
                final int fbUpStatus = this.financialBenefitsMapper.updateByPrimaryKeySelective(fbRecord);
                FinancialLockUpServiceImpl.logger.info("更新剩余金额状态： {}", fbUpStatus);
            }
        }

    }

    @Override
    public void validationPaymentOther() {
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算其他套餐temp_amount开始---------------------------------");
        final StringBuilder sb = new StringBuilder("http://localhost:3000/checkbalance?address=");
        final LocalDateTime now = LocalDateTime.now();
        //第一步 只查询　在抢购时间段内的除了周产品
        List<FinancialBenefits> financialBenefits = null;
        try {
            financialBenefits = this.financialBenefitsService.queryFinancialInPanic(now, 1, 1);
        } catch (final Exception e) {
            FinancialLockUpServiceImpl.logger.info("查询抢购时间段内的产品异常： {}", e);
        }
        this.toDoLockUps(financialBenefits, sb);
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算其他套餐temp_amount结束---------------------------------");
    }

    @Override
    public void saveLockUpAmount() {
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算lock_up_amount开始---------------------------------");
        final StringBuilder sb = new StringBuilder("http://localhost:3000/checkbalance?address=");
        final LocalDateTime now = LocalDateTime.now();
        //第一步　取出抢购结束时间小于当前时间、并且未计算的理财产品
        List<FinancialBenefits> benefits = null;
        try {
            benefits = this.financialBenefitsService.queryFinancialNotCalactionLockUp(now);
        } catch (final Exception e) {
            FinancialLockUpServiceImpl.logger.info("查询未计算lockup的产品异常： {}", e);
        }
        //第二步　遍历产品下的合约；根据合约地址查询lock_up_amount
        for (final FinancialBenefits financialBenefits : benefits) {
            final List<FinancialLockUp> financialLockUps = this.queryLockUpByBenefitId(financialBenefits.getId());
            for (final FinancialLockUp financialLockUp : financialLockUps) {
                final Financial financial;
                try {
                    financial = this.financialService.queryOneFinancial(financialBenefits.getFinancialId());
                } catch (final Exception e) {
                    FinancialLockUpServiceImpl.logger.info("获取套餐异常： {}", e);
                    continue;
                }
                sb.append(financialLockUp.getSharedAddress().trim());
                final BalanceEntity data = this.balanceGet(sb);
                if (data == null) {
                    continue;
                }
                FinancialLockUpServiceImpl.logger.info("解析data: {}", data);
                final BigDecimal currentAmount = data.getCurrent_balance().divide(new BigDecimal(1000000));
                BigDecimal lockUpAmount = new BigDecimal(0);
                FinancialLockUpServiceImpl.logger.info("合约地址当前余额: {}, 起购额度: {}", currentAmount, financialBenefits.getMinAmount());
                if (currentAmount.compareTo(new BigDecimal(financialBenefits.getMinAmount())) != -1) {
                    FinancialLockUpServiceImpl.logger.info("余额大于等于起购额度，并且套餐类型为: {} 同时限购额度为: {}", financial.getFinancialName(), financialBenefits.getPurchaseLimit());
                    if (financial.getId() == 1) {
                        if (currentAmount.compareTo(new BigDecimal(financialBenefits.getPurchaseLimit())) != -1) {
                            FinancialLockUpServiceImpl.logger.info("周套餐：启用限购额度");
                            lockUpAmount = new BigDecimal(financialBenefits.getPurchaseLimit());
                        } else {
                            FinancialLockUpServiceImpl.logger.info("周套餐：启用当前余额");
                            lockUpAmount = currentAmount;
                        }
                    } else {
                        FinancialLockUpServiceImpl.logger.info("周以外套餐：启用当前余额");
                        lockUpAmount = currentAmount;
                    }
                }
                FinancialLockUpServiceImpl.logger.info("决定是否更新锁仓金额以及剩余额度，当前金额为: {}", lockUpAmount);
                if (lockUpAmount.compareTo(new BigDecimal(0)) == 1) {
                    FinancialLockUpServiceImpl.logger.info("开始更新锁仓金额，合约id: {}", financialLockUp.getId());
                    final FinancialLockUp record = new FinancialLockUp();
                    record.setId(financialLockUp.getId());
                    record.setLockUpAmount(lockUpAmount);
                    final int upStatus = this.financialLockUpMapper.updateByPrimaryKeySelective(record);
                    FinancialLockUpServiceImpl.logger.info("更新锁仓金额状态： {}", upStatus);
                    FinancialLockUpServiceImpl.logger.info("开始计算剩余额度, 产品id:{}", financialBenefits.getId());
                }
            }
            final BigDecimal sumLockUpAmount = this.financialLockUpMapper.sumLockUpAmount(financialBenefits.getId());
            if (sumLockUpAmount != null) {
                FinancialLockUpServiceImpl.logger.info("总额度: {}", financialBenefits.getPanicTotalLimit());
                //计算剩余额度
                final FinancialBenefits fbRecord = FinancialBenefits.builder()
                        .id(financialBenefits.getId())
                        .calactionLockupStatus(1)
                        .alsoLockUpAmount(sumLockUpAmount)
                        .build();
                final int fbUpStatus = this.financialBenefitsMapper.updateByPrimaryKeySelective(fbRecord);
                FinancialLockUpServiceImpl.logger.info("更新剩余金额状态： {}", fbUpStatus);
            }
        }
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算lock_up_amount结束---------------------------------");
    }

    /**
     * 计算参与者
     *
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, BigDecimal> participate() throws Exception {
        final String keyUser = "total_user";
        final String keyAmount = "total_amount";
        Map<String, BigDecimal> map = this.financialLockUpMapper.participate();
        if (map == null) {
            map = new HashMap<>(2);
            map.put(keyUser, new BigDecimal(0));
            map.put(keyAmount, new BigDecimal(0));
        } else {
            if (map.get(keyUser) == null) {
                map.put(keyUser, new BigDecimal(0));
            }
            if (map.get(keyAmount) == null) {
                map.put(keyAmount, new BigDecimal(0));
            }
        }
        return map;
    }

    private List<FinancialLockUp> convert(final List<FinancialLockUp> financialLockUps, final int type) {
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
                if (type == 1) {
                    if (financialLockUp.getTempAmount() == null || financialLockUp.getOrderAmount() == null) {
                        financialLockUp.setLockUpStatus("未完成");
                    } else {
                        if (financialLockUp.getTempAmount().compareTo(new BigDecimal(financialLockUp.getOrderAmount())) == -1) {
                            financialLockUp.setLockUpStatus("未完成");
                        }
                    }
                }
            }
        }
        return financialLockUps;
    }

    /**
     * 根据benefitId查询lockUp
     *
     * @param financialBenefitdId
     * @return
     */
    private List<FinancialLockUp> queryLockUpByBenefitId(final int financialBenefitdId) {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialBenefitsIdEqualTo(financialBenefitdId);
        return this.financialLockUpMapper.selectByExample(example);
    }

    /**
     * 调用ndodejs 查询余额
     *
     * @param sb
     * @return
     */
    private BalanceEntity balanceGet(final StringBuilder sb) {
        BalanceEntity data = null;
        // 根据地址获取请求
        final HttpGet request = new HttpGet(sb.toString());
        // 获取当前客户端对象
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try (final CloseableHttpResponse httpResponse = httpClient.execute(request)) {
            // 判断网络连接状态码是否正常(0--200都数正常)
            FinancialLockUpServiceImpl.logger.info("调用nodejs接口　response: {}", httpResponse.getStatusLine().getStatusCode());
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                final String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                FinancialLockUpServiceImpl.logger.info("解析json: {}", json);
                final JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
                data = jsonObject.getObject("data", BalanceEntity.class);
            }
        } catch (final IOException e) {
            FinancialLockUpServiceImpl.logger.info("调用nodejs异常： {}", e);
            data = null;
        }
        return data;
    }

    /**
     * 根据lockUpId更新tempAmount
     *
     * @param tempAmount
     * @param lockUpId
     */
    private void updateTempAmount(final BigDecimal tempAmount, final int lockUpId) {
        if (tempAmount.compareTo(new BigDecimal(0)) == 1) {
            FinancialLockUpServiceImpl.logger.info("开始更新tempAmount金额，合约id: {}", lockUpId);
            final FinancialLockUp record = new FinancialLockUp();
            record.setId(lockUpId);
            record.setTempAmount(tempAmount);
            final int upStatus = this.financialLockUpMapper.updateByPrimaryKeySelective(record);
            FinancialLockUpServiceImpl.logger.info("更新tempAmount金额状态： {}", upStatus);
        }
    }

    private List<FinancialLockUp> queryWithinThirtyMinutes(final LocalDateTime now) throws Exception {
        final LocalDateTime beginTime = now.minusMinutes(30);
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andOperationTimeGreaterThan(beginTime);
        return this.financialLockUpMapper.selectByExample(example);
    }

}
