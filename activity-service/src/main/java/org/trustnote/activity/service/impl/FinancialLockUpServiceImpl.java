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
import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.api.FinancialLockUpApi;
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

    /**
     * 分页查询合约信息
     *
     * @param page
     * @param benefitsId
     * @return
     * @throws Exception
     */
    @Override
    public List<FinancialLockUpApi> queryFinancialLockUp(final Page<FinancialLockUp> page, final int benefitsId) throws Exception {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialBenefitsIdEqualTo(benefitsId);
        criteria.andLockUpAmountGreaterThan(new BigDecimal(0));
        example.setOrderByClause("operation_time DESC");
        return this.convert(this.financialLockUpMapper.selectByExamplePage(page, example), 0);
    }

    /**
     * 保存合约信息
     *
     * @param financialLockUp
     * @return
     * @throws Exception
     */
    @Override
    public int saveFinancialLockUp(final FinancialLockUp financialLockUp) throws Exception {
        financialLockUp.setIncomeAmount(null);
        financialLockUp.setLockUpAmount(null);
        financialLockUp.setOperationTime(LocalDateTime.now());
        return this.financialLockUpMapper.insertSelective(financialLockUp);
    }

    /**
     * 根据设备地址查询合约信息
     *
     * @param deviceAddress
     * @return
     * @throws Exception
     */
    @Override
    public List<FinancialLockUpApi> queryFincialLockUpByDeviceAddress(final String deviceAddress) throws Exception {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andDeviceAddressEqualTo(deviceAddress);
        return this.convert(this.financialLockUpMapper.selectByExample(example), 1);
    }

    /**
     * 根据id修改认购金额，目前已废弃
     *
     * @param id
     * @param orderAmount
     * @return
     * @throws Exception
     */
    @Override
    @Deprecated
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
    public Map<String, BigDecimal> statisticalAmount(final FinancialBenefitsApi financialBenefitsApi) {
        final Map<String, BigDecimal> statistical = new HashMap<>(6);
        final FinancialLockUpExample example = this.conditionsExample(financialBenefitsApi, 0, 0, "all");
        //已锁总额度、已抢购总额度
        final Map<String, BigDecimal> mapAlso = this.financialLockUpMapper.statisticalAmount(example);
        this.inDataMap(mapAlso, statistical, 0);
        //当前已锁总额度、当前已抢购总额度
        final Map<String, BigDecimal> mapCurr = this.financialLockUpMapper.statisticalAmount(this.conditionsExample(financialBenefitsApi, 1, 0, "fix"));
        this.inDataMap(mapCurr, statistical, 1);
        //收益、tfans
        final Map<String, BigDecimal> mapInComeTfans = this.financialLockUpMapper.statisticalInComeTfans(this.conditionsExample(financialBenefitsApi, 0, 1, "fix"));
        this.inDataMap(mapInComeTfans, statistical, 2);
        return statistical;
    }

    /**
     * 根据不同条件查询产品记录
     *
     * @param financialBenefitsApi
     * @param type                 0 不需要计算解锁时间  1 需要计算解锁时间
     * @param status               0 不需要计算已发收益  1 需要计算已发收益
     * @return
     */
    private FinancialLockUpExample conditionsExample(final FinancialBenefitsApi financialBenefitsApi, final int type, final int status, final String allOrFix) {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        final String fix = "fix";
        LocalDateTime now = null;
        if (type == 1) {
            now = LocalDateTime.now();
        }
        final List<Integer> ids = this.financialBenefitsService.queryFinancialFinancialId(financialBenefitsApi, now, status);
        if (CollectionUtils.isEmpty(ids) && fix.equals(allOrFix)) {
            ids.add(null);
        }
        criteria.andFinancialBenefitsIdIn(ids);
        return example;
    }

    /**
     * 组装数据
     *
     * @param map
     * @param statistical
     * @param type
     */
    private void inDataMap(final Map<String, BigDecimal> map, final Map<String, BigDecimal> statistical, final int type) {
        final int curr = 1;
        final int inComeTfans = 2;
        final String keyFirst = "firstValue";
        final String keySecond = "secondValue";
        String keyOne = "tempAmountAlso";
        String keyTwo = "lockUpAmountAlso";
        if (type == curr) {
            keyOne = "tempAmountCurr";
            keyTwo = "lockUpAmountCurr";
        } else if (type == inComeTfans) {
            keyOne = "inComeAmountAlso";
            keyTwo = "tFansAmountAlso";
        }
        if (map == null) {
            statistical.put(keyOne, new BigDecimal(0));
            statistical.put(keyTwo, new BigDecimal(0));
        } else {
            statistical.put(keyOne, map.get(keyFirst) == null ? new BigDecimal(0) : map.get(keyFirst));
            statistical.put(keyTwo, map.get(keySecond) == null ? new BigDecimal(0) : map.get(keySecond));
        }
    }


    /**
     * excel导出
     *
     * @param benefitsId
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, String>> export(final int benefitsId) throws Exception {
        final Page<FinancialLockUp> page = new Page<>(1, Integer.MAX_VALUE);
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialBenefitsIdEqualTo(benefitsId);
        criteria.andIncomeAmountIsNotNull();
        example.setOrderByClause("operation_time DESC");
        final List<FinancialLockUp> financialLockUps = this.financialLockUpMapper.selectByExamplePage(page, example);
        final List<FinancialLockUp> lastFi = this.convertNonApi(financialLockUps, 0);
        final List<Map<String, String>> contents = new ArrayList<>();
        for (final FinancialLockUp financialLockUp : lastFi) {
            final Map<String, String> map = new HashMap<>(5);
            map.put("0", financialLockUp.getSharedAddress());
            map.put("1", financialLockUp.getTempAmount().toString());
            map.put("2", financialLockUp.getLockUpAmount().toString());
            map.put("3", financialLockUp.getIncomeAmount() == null ? "" : financialLockUp.getIncomeAmount().toString());
            map.put("4", financialLockUp.getLockUpStatus());
            map.put("5", DateTimeUtils.formatDateTime(financialLockUp.getOperationTime(), "yyyy-MM-dd HH:mm:ss"));
            contents.add(map);
        }
        return contents;
    }

    @Override
    public List<Map<String, String>> exportTFS(final int benefitsId) throws Exception {
        final Page<FinancialLockUp> page = new Page<>(1, Integer.MAX_VALUE);
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialBenefitsIdEqualTo(benefitsId);
        criteria.andTfansAmountIsNotNull();
        example.setOrderByClause("operation_time DESC");
        final List<FinancialLockUp> financialLockUps = this.financialLockUpMapper.selectByExamplePage(page, example);
        final List<Map<String, String>> contents = new ArrayList<>();
        for (final FinancialLockUp financialLockUp : financialLockUps) {
            final Map<String, String> map = new HashMap<>(2);
            map.put("0", financialLockUp.getWalletAddress());
            map.put("1", financialLockUp.getTFansAmount().toString());
            contents.add(map);
        }
        return contents;
    }

    /**
     * 定时计算抢购m时间段内的已抢购额度、已锁额度
     */
    @Override
    public void validationPayment() {
        final long start = System.currentTimeMillis();
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算周套餐temp_amount开始---------------------------------");
        final LocalDateTime now = LocalDateTime.now();
        //第一步 只查询　在抢购时间段内的周产品
        List<FinancialBenefits> financialBenefits = null;
        try {
            financialBenefits = this.financialBenefitsService.queryFinancialInPanic(now, 1);
        } catch (final Exception e) {
            FinancialLockUpServiceImpl.logger.info("查询抢购时间段内的产品异常： {}", e);
        }
        this.toDoLockUps(financialBenefits);
        FinancialLockUpServiceImpl.logger.info("用时: {}", System.currentTimeMillis() - start);
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算周套餐temp_amount结束---------------------------------");
    }

    /**
     * 定时计算抢购时间段内的已抢购额度、已锁额度 第二步
     *
     * @param financialBenefits
     */
    private void toDoLockUps(final List<FinancialBenefits> financialBenefits) {
        //第二步　遍历产品，取出产品下的合约查询金额
        for (final FinancialBenefits benefits : financialBenefits) {
            final List<FinancialLockUp> financialLockUps = this.queryLockUpByBenefitId(benefits.getId());
            for (final FinancialLockUp financialLockUp : financialLockUps) {
                final StringBuilder sb = new StringBuilder("http://localhost:3000/checkbalance?address=");
                sb.append(financialLockUp.getSharedAddress().trim());
                final Financial financial;
                try {
                    financial = this.financialService.queryOneFinancial(benefits.getFinancialId());
                } catch (final Exception e) {
                    FinancialLockUpServiceImpl.logger.info("获取套餐异常： {}", e);
                    continue;
                }
                final BalanceEntity data = this.balanceGet(sb);
                if (data == null) {
                    continue;
                }
                FinancialLockUpServiceImpl.logger.info("解析data: {}", data);
                final BigDecimal currentAmount = data.getCurrent_balance().divide(new BigDecimal(1000000));
                FinancialLockUpServiceImpl.logger.info("合约地址当前余额: {}, 起购额度: {} 合约ID: {}", currentAmount, benefits.getMinAmount(), financialLockUp.getId());
                BigDecimal lockUpAmount = new BigDecimal(0);
                currentAmount.setScale(0, BigDecimal.ROUND_DOWN);
                FinancialLockUpServiceImpl.logger.info("取整之后余额: {}", currentAmount);
                if (currentAmount.compareTo(new BigDecimal(benefits.getMinAmount())) != -1) {
                    FinancialLockUpServiceImpl.logger.info("余额大于等于起购额度，并且套餐类型为: {} 同时限购额度为: {}", financial.getFinancialName(), benefits.getPurchaseLimit());
                    if (financial.getId() == 1) {
                        if (currentAmount.compareTo(new BigDecimal(benefits.getPurchaseLimit())) != -1) {
                            FinancialLockUpServiceImpl.logger.info("周套餐：启用限购额度");
                            lockUpAmount = new BigDecimal(benefits.getPurchaseLimit());
                        } else {
                            FinancialLockUpServiceImpl.logger.info("周套餐：启用当前余额");
                            lockUpAmount = currentAmount;
                        }
                    } else {
                        FinancialLockUpServiceImpl.logger.info("周以外套餐：启用当前余额");
                        lockUpAmount = currentAmount;
                    }
                }
                this.updateTempAndLockUpAmount(currentAmount.setScale(0, BigDecimal.ROUND_DOWN), lockUpAmount, financialLockUp.getId());
            }
            final BigDecimal sumTempAmount = this.financialLockUpMapper.sumTempAmount(benefits.getId());
            final BigDecimal sumLockUpAmount = this.financialLockUpMapper.sumLockUpAmount(benefits.getId());
            BigDecimal remainLimit = null;
            if (sumTempAmount != null && sumLockUpAmount != null) {
                if (benefits.getPanicTotalLimit() != null) {
                    remainLimit = new BigDecimal(benefits.getPanicTotalLimit()).subtract(sumLockUpAmount);
                }
                FinancialLockUpServiceImpl.logger.info("总额度: {}, 最新剩余额度: {}", benefits.getPanicTotalLimit(), remainLimit);
                final FinancialBenefits fbRecord = FinancialBenefits.builder()
                        .id(benefits.getId())
                        .alsoTempAmount(sumTempAmount)
                        .alsoLockUpAmount(sumLockUpAmount)
                        .build();
                if (remainLimit != null) {
                    fbRecord.setRemainLimit(remainLimit);
                }

                final int fbUpStatus = this.financialBenefitsMapper.updateByPrimaryKeySelective(fbRecord);
                FinancialLockUpServiceImpl.logger.info("更新剩余金额状态： {}", fbUpStatus);
            }
        }
    }

    /**
     * 计算收益
     */
    @Override
    public void saveInComeAmount() {
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算收益开始---------------------------------");
        final LocalDateTime now = LocalDateTime.now();
        //第一步　取出抢购结束时间小于当前时间、并且未发收益的理财产品
        List<FinancialBenefits> benefits = null;
        try {
            benefits = this.financialBenefitsService.queryFinancialNotCalactionLockUp(now);
        } catch (final Exception e) {
            FinancialLockUpServiceImpl.logger.info("查询产品异常： {}", e);
        }
        //第二步　遍历产品下的合约；根据合约地址查询lock_up_amount
        for (final FinancialBenefits financialBenefits : benefits) {
            FinancialLockUpServiceImpl.logger.info("产品ID: {}", financialBenefits.getId());
            final List<FinancialLockUp> financialLockUps = this.queryLockUpByBenefitIdAndCalactionStatus(financialBenefits.getId());
            for (final FinancialLockUp financialLockUp : financialLockUps) {
                FinancialLockUpServiceImpl.logger.info("_________开始计算收益__________");
                final Financial financial;
                try {
                    financial = this.financialService.queryOneFinancial(financialBenefits.getFinancialId());
                } catch (final Exception e) {
                    FinancialLockUpServiceImpl.logger.info("获取套餐异常： {}", e);
                    continue;
                }

                //本金
                final BigDecimal principal = financialLockUp.getLockUpAmount();
                //理财周期
                final BigDecimal numericalv = BigDecimal.valueOf(financial.getNumericalv());
                //年化利率
                final BigDecimal rate = BigDecimal.valueOf(financialBenefits.getFinancialRate()).setScale(2, BigDecimal.ROUND_DOWN);
                //计算收益
                final BigDecimal all = principal.multiply(numericalv).multiply(rate);
                final BigDecimal income = all.divide(new BigDecimal(360), 1, BigDecimal.ROUND_DOWN);
                final BigDecimal tfans = income.multiply(new BigDecimal(financialBenefits.getTFans()))
                        .setScale(0, BigDecimal.ROUND_DOWN);
                FinancialLockUpServiceImpl.logger.info("合约ID: {} 本金：{} 理财周期： {} 年化利率： {} 收益： {} tfans: {}", financialLockUp
                        .getId(), principal, numericalv, rate, income, tfans);

                final FinancialLockUp record = new FinancialLockUp();
                record.setId(financialLockUp.getId());
                record.setIncomeAmount(income);
                record.setTFansAmount(tfans.intValue());
                record.setCalactionStatus(1);

                final int upStatus;
                try {
                    upStatus = this.financialLockUpMapper.updateByPrimaryKeySelective(record);
                } catch (final Exception e) {
                    FinancialLockUpServiceImpl.logger.error("计算收益金额异常: {}", e);
                    continue;
                }

                FinancialLockUpServiceImpl.logger.info("更新锁仓金额状态： {}", upStatus);
            }

            final FinancialLockUpExample example = new FinancialLockUpExample();
            final FinancialLockUpExample.Criteria criteria = example.createCriteria();
            criteria.andFinancialBenefitsIdEqualTo(financialBenefits.getId());
            final Map<String, BigDecimal> mapInComeTfans = this.financialLockUpMapper.statisticalInComeTfans(example);
            final FinancialBenefits fbRecord = FinancialBenefits.builder()
                    .id(financialBenefits.getId())
                    .build();
            if (mapInComeTfans != null) {
                if (mapInComeTfans.get("firstValue") != null) {
                    fbRecord.setIncomeTotal(mapInComeTfans.get("firstValue"));
                }
                if (mapInComeTfans.get("secondValue") != null) {
                    fbRecord.setTFansTotal(mapInComeTfans.get("secondValue").intValue());
                }
            }
            FinancialLockUpServiceImpl.logger.info("更新总收益金额与总赠送TFS数量信息: {}", fbRecord.toString());
            if (fbRecord.getIncomeTotal() != null || fbRecord.getTFansTotal() != null) {
                final int fbUpStatus = this.financialBenefitsMapper.updateByPrimaryKeySelective(fbRecord);
                FinancialLockUpServiceImpl.logger.info("数据库操作状态： {}", fbUpStatus);
            }
        }
        FinancialLockUpServiceImpl.logger.info("-----------------------------------计算收益结束---------------------------------");
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

    /**
     * 数据转化
     *
     * @param financialLockUps
     * @param type
     * @return
     */
    private List<FinancialLockUpApi> convert(final List<FinancialLockUp> financialLockUps, final int type) {
        final LocalDateTime now = LocalDateTime.now();
        final List<FinancialLockUpApi> financialLockUpApis = new ArrayList<>();
        for (final FinancialLockUp financialLockUp : financialLockUps) {
            final FinancialBenefits financialBenefits = this.financialBenefitsMapper.selectByPrimaryKey(financialLockUp.getFinancialBenefitsId());
            if (financialBenefits == null) {
                financialLockUp.setLockUpStatus("");
                continue;
            }
            final LocalDateTime unLockTime = financialBenefits.getUnlockTime();
            this.changeLockUpStatus(type, now, financialLockUp, unLockTime);

            final FinancialLockUpApi financialLockUpApi = FinancialLockUpApi.builder()
                    .id(financialLockUp.getId())
                    .sharedAddress(financialLockUp.getSharedAddress())
                    .deviceAddress(financialLockUp.getDeviceAddress())
                    .financialBenefitsId(financialLockUp.getFinancialBenefitsId())
                    .lockUpAmount(financialLockUp.getLockUpAmount())
                    .incomeAmount(financialLockUp.getIncomeAmount())
                    .operationTime(DateTimeUtils.localDateTimeParseLong(financialLockUp.getOperationTime()))
                    .lockUpStatus(financialLockUp.getLockUpStatus())
                    .orderAmount(financialLockUp.getOrderAmount())
                    .tempAmount(financialLockUp.getTempAmount())
                    .tFansAmount(financialLockUp.getTFansAmount())
                    .build();
            financialLockUpApis.add(financialLockUpApi);
        }
        return financialLockUpApis;
    }

    /**
     * 改变状态
     *
     * @param type
     * @param now
     * @param financialLockUp
     * @param unLockTime
     */
    private void changeLockUpStatus(final int type, final LocalDateTime now, final FinancialLockUp financialLockUp, final LocalDateTime unLockTime) {
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

    private List<FinancialLockUp> convertNonApi(final List<FinancialLockUp> financialLockUps, final int type) {
        final LocalDateTime now = LocalDateTime.now();
        for (final FinancialLockUp financialLockUp : financialLockUps) {
            final FinancialBenefits financialBenefits = this.financialBenefitsMapper.selectByPrimaryKey(financialLockUp.getFinancialBenefitsId());
            if (financialBenefits == null) {
                financialLockUp.setLockUpStatus("");
                continue;
            }
            final LocalDateTime unLockTime = financialBenefits.getUnlockTime();
            this.changeLockUpStatus(type, now, financialLockUp, unLockTime);
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
     * 根据产品ID、为计算收益字段查询合约
     *
     * @param financialBenefitId
     * @return
     */
    private List<FinancialLockUp> queryLockUpByBenefitIdAndCalactionStatus(final int financialBenefitId) {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialBenefitsIdEqualTo(financialBenefitId);
        criteria.andCalactionStatusEqualTo(0);
        criteria.andLockUpAmountIsNotNull();
        return this.financialLockUpMapper.selectByExample(example);
    }

    /**
     * 调用ndodejs 查询余额
     *
     * @param sb
     * @return
     */
    private BalanceEntity balanceGet(final StringBuilder sb) {
        FinancialLockUpServiceImpl.logger.info("url: {}", sb.toString());
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
        sb.delete(0, sb.length());
        FinancialLockUpServiceImpl.logger.info("url_empty: {}", sb.toString());
        return data;
    }

    /**
     * 根据lockUpId更新tempAmount
     *
     * @param tempAmount
     * @param lockUpId
     */
    private void updateTempAndLockUpAmount(final BigDecimal tempAmount, final BigDecimal lockUpAmount, final int lockUpId) {
        if (tempAmount.compareTo(new BigDecimal(0)) == 1) {
            FinancialLockUpServiceImpl.logger.info("开始更新tempAmount金额，合约id: {}", lockUpId);
            final FinancialLockUp record = new FinancialLockUp();
            record.setId(lockUpId);
            record.setTempAmount(tempAmount);
            if (lockUpAmount.compareTo(new BigDecimal(0)) == 1) {
                record.setLockUpAmount(lockUpAmount);
            }
            final int upStatus = this.financialLockUpMapper.updateByPrimaryKeySelective(record);
            FinancialLockUpServiceImpl.logger.info("更新tempAmount金额状态： {}", upStatus);
        }
    }

    @Override
    public String readWalletAddress(final List list) {
        int uS = 0;
        int uF = 0;
        int i;
        for (i = 0; i < list.size(); i++) {
            final String[] excelLine = (String[]) list.get(i);
            final String sharedAddress = excelLine[0];
            final String walletAddress = excelLine[1];
            final FinancialLockUp record = FinancialLockUp.builder()
                    .walletAddress(walletAddress)
                    .build();
            final FinancialLockUpExample example = new FinancialLockUpExample();
            final FinancialLockUpExample.Criteria criteria = example.createCriteria();
            criteria.andSharedAddressEqualTo(sharedAddress);
            final int up = this.financialLockUpMapper.updateByExampleSelective(record, example);
            if (up == 1) {
                uS++;
            } else {
                uF++;
            }
        }
        return "导入总计条数: " + list.size() + " 成功修改记录: " + uS + " 失败记录: " + uF;
    }

    @Override
    public String manualTFans() {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andIncomeAmountIsNotNull();
        criteria.andTfansAmountIsNull();
        final List<FinancialLockUp> financialLockUps = this.financialLockUpMapper.selectByExample(example);
        int uS = 0;
        int uF = 0;
        for (final FinancialLockUp event : financialLockUps) {
            final FinancialBenefits financialBenefits;
            try {
                financialBenefits = this.financialBenefitsService.queryOneFinancialBenefits(event
                        .getFinancialBenefitsId());
            } catch (final Exception e) {
                FinancialLockUpServiceImpl.logger.error("根据financialBenefitsId: {} 查询financial 异常: {}", event.getFinancialBenefitsId(), e);
                continue;
            }
            if (financialBenefits != null) {
                final BigDecimal tFans = event.getIncomeAmount().multiply(new BigDecimal(financialBenefits.getTFans()));
                final FinancialLockUp record = FinancialLockUp.builder()
                        .id(event.getId())
                        .tFansAmount(tFans.setScale(0, BigDecimal.ROUND_DOWN).intValue())
                        .build();
                final int status = this.financialLockUpMapper.updateByPrimaryKeySelective(record);
                if (status == 1) {
                    uS++;
                } else {
                    uF++;
                }
            }
        }

        return "处理合约总计条数: " + financialLockUps.size() + " 成功修改记录: " + uS + " 失败记录: " + uF;
    }
}
