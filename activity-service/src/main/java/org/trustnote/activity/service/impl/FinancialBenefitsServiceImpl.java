package org.trustnote.activity.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.example.FinancialBenefitsExample;
import org.trustnote.activity.common.pojo.Financial;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.common.utils.DateTimeUtils;
import org.trustnote.activity.service.iface.FinancialBenefitsService;
import org.trustnote.activity.service.iface.FinancialLockUpService;
import org.trustnote.activity.service.iface.FinancialService;
import org.trustnote.activity.skeleton.mybatis.mapper.FinancialBenefitsMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
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
public class FinancialBenefitsServiceImpl implements FinancialBenefitsService {
    private static final Logger logger = LogManager.getLogger(FinancialBenefitsServiceImpl.class);

    @Resource
    private FinancialBenefitsMapper financialBenefitsMapper;
    @Resource
    private FinancialService financialService;
    @Resource
    private FinancialLockUpService financialLockUpService;

    @Override
    public FinancialBenefits queryOneFinancialBenefits(final int id) {
        return this.financialBenefitsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FinancialBenefitsApi> queryFinancialBenefits(final FinancialBenefitsApi financialBenefitsApi, final Page<FinancialBenefits> page) {
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        if (financialBenefitsApi.getFinancialId() != null) {
            criteria.andFinancialIdEqualTo(financialBenefitsApi.getFinancialId());
        }
        example.setOrderByClause("id DESC");
        final List<FinancialBenefits> financialBenefits = this.financialBenefitsMapper.selectByExampleAndPage(page, example);
        return this.convertPojoToApi(financialBenefits);
    }

    @Override
    public int updateFinancialBenefits(final FinancialBenefitsApi financialBenefitsApi) throws Exception {
        final List<FinancialBenefits> panic = this.queryFinancialBetweenPanic(financialBenefitsApi.getPanicStartTime(), financialBenefitsApi.getPanicEndTime(),
                1, financialBenefitsApi.getId(), financialBenefitsApi.getFinancialId());
        if (!CollectionUtils.isEmpty(panic)) {
            return -1;
        }
        BigDecimal remainLimit = null;
        if (financialBenefitsApi.getFinancialId() == 1) {
            remainLimit = new BigDecimal(financialBenefitsApi.getPanicTotalLimit());
        } else {
            if (financialBenefitsApi.getPanicTotalLimit() != null) {
                remainLimit = new BigDecimal(financialBenefitsApi.getPanicTotalLimit());
            }
        }
        final FinancialBenefits record = FinancialBenefits.builder()
                .id(financialBenefitsApi.getId())
                .financialId(financialBenefitsApi.getFinancialId())
                .productName(financialBenefitsApi.getProductName())
                .panicStartTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getPanicStartTime()))
                .panicEndTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getPanicEndTime()))
                .interestStartTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getInterestStartTime()))
                .interestEndTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getInterestEndTime()))
                .unlockTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getUnlockTime()))
                .panicTotalLimit(financialBenefitsApi.getPanicTotalLimit())
                .minAmount(financialBenefitsApi.getMinAmount())
                .purchaseLimit(financialBenefitsApi.getPurchaseLimit())
                .remainLimit(remainLimit)
                .financialRate(financialBenefitsApi.getFinancialRate())
                .tFans(financialBenefitsApi.getTFans())
                .build();
        return this.financialBenefitsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertFinancialBenefits(final FinancialBenefitsApi financialBenefitsApi) throws Exception {
        final List<FinancialBenefits> panic = this.queryFinancialBetweenPanic(financialBenefitsApi.getPanicStartTime(), financialBenefitsApi.getPanicEndTime(),
                0, 0, financialBenefitsApi.getFinancialId());
        if (!CollectionUtils.isEmpty(panic)) {
            return -1;
        }
        BigDecimal remainLimit = null;
        if (financialBenefitsApi.getFinancialId() == 1) {
            remainLimit = new BigDecimal(financialBenefitsApi.getPanicTotalLimit());
        } else {
            if (financialBenefitsApi.getPanicTotalLimit() != null) {
                remainLimit = new BigDecimal(financialBenefitsApi.getPanicTotalLimit());
            }
        }
        final FinancialBenefits financialBenefits = FinancialBenefits.builder()
                .financialId(financialBenefitsApi.getFinancialId())
                .productName(financialBenefitsApi.getProductName())
                .panicStartTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getPanicStartTime()))
                .panicEndTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getPanicEndTime()))
                .interestStartTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getInterestStartTime()))
                .interestEndTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getInterestEndTime()))
                .unlockTime(DateTimeUtils.longParseLocalDateTime(financialBenefitsApi.getUnlockTime()))
                .panicTotalLimit(financialBenefitsApi.getPanicTotalLimit())
                .minAmount(financialBenefitsApi.getMinAmount())
                .purchaseLimit(financialBenefitsApi.getPurchaseLimit())
                .remainLimit(remainLimit)
                .financialRate(financialBenefitsApi.getFinancialRate())
                .tFans(financialBenefitsApi.getTFans())
                .build();
        return this.financialBenefitsMapper.insertSelective(financialBenefits);
    }

    @Override
    public FinancialBenefitsApi queryFinancialBenefitsByFinancialId(final int financialId) throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialIdEqualTo(financialId);
        criteria.andPanicStartTimeLessThanOrEqualTo(now);
        criteria.andPanicEndTimeGreaterThanOrEqualTo(now);
        final List<FinancialBenefits> financialBenefits = this.financialBenefitsMapper.selectByExample(example);
        example.setOrderByClause("panic_start_time");
        //第一步：有进行中的产品，查询是否存在下期的产品
        final List<FinancialBenefits> nextFinancialBenefits = this.queryFinancialGreaterThanNow(financialId, now);
        if (!CollectionUtils.isEmpty(financialBenefits)) {
            final FinancialBenefits benefits = financialBenefits.get(0);
            final StringBuilder sb = new StringBuilder("抢购进行中");
            if (benefits.getPanicTotalLimit() == null) {
                sb.delete(0, sb.length());
                sb.append("抢购进行中");
            } else {
                if (benefits.getRemainLimit() != null) {
                    if ((benefits.getRemainLimit().compareTo(new BigDecimal(0)) != 1)) {
                        sb.delete(0, sb.length());
                        sb.append("抢购已结束");
                    } else {
                        sb.delete(0, sb.length());
                        sb.append("抢购进行中");
                    }
                }
            }
            final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                    .id(benefits.getId())
                    .financialId(benefits.getFinancialId())
                    .productName(benefits.getProductName())
                    .panicStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicStartTime()))
                    .panicEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicEndTime()))
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                    .panicTotalLimit(benefits.getPanicTotalLimit())
                    .minAmount(benefits.getMinAmount())
                    .purchaseLimit(benefits.getPurchaseLimit())
                    .remainLimit(benefits.getRemainLimit())
                    .financialStatus(benefits.getFinancialStatus())
                    .financialRate(benefits.getFinancialRate())
                    .activityStatus(sb.toString())
                    .alsoLockUpAmount(benefits.getAlsoLockUpAmount())
                    .alsoTempAmount(benefits.getAlsoTempAmount())
                    .tFans(benefits.getTFans())
                    .incomeTotal(benefits.getIncomeTotal())
                    .tFansTotal(benefits.getTFansTotal())
                    .build();
            if (!CollectionUtils.isEmpty(nextFinancialBenefits)) {
                financialBenefitsApi.setNextPanicStartTime(DateTimeUtils.localDateTimeParseLong(nextFinancialBenefits.get(0).getPanicStartTime()));
                financialBenefitsApi.setNextPanicEndTime(DateTimeUtils.localDateTimeParseLong(nextFinancialBenefits.get(0).getPanicEndTime()));
            }
            return financialBenefitsApi;
        }
        //第二步：如果没有进行中 优先查询是否有未开启的
        if (!CollectionUtils.isEmpty(nextFinancialBenefits)) {
            final FinancialBenefits benefits = nextFinancialBenefits.get(0);
            final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                    .id(benefits.getId())
                    .financialId(benefits.getFinancialId())
                    .productName(benefits.getProductName())
                    .panicStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicStartTime()))
                    .panicEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicEndTime()))
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                    .panicTotalLimit(benefits.getPanicTotalLimit())
                    .minAmount(benefits.getMinAmount())
                    .purchaseLimit(benefits.getPurchaseLimit())
                    .remainLimit(benefits.getRemainLimit())
                    .financialStatus(benefits.getFinancialStatus())
                    .financialRate(benefits.getFinancialRate())
                    .activityStatus("未开启")
                    .alsoLockUpAmount(benefits.getAlsoLockUpAmount())
                    .alsoTempAmount(benefits.getAlsoTempAmount())
                    .tFans(benefits.getTFans())
                    .incomeTotal(benefits.getIncomeTotal())
                    .tFansTotal(benefits.getTFansTotal())
                    .build();
            //查询未开启后续是否还有
            final List<FinancialBenefits> nextTwo = this.queryFinancialGreaterThanNow(financialId, benefits.getPanicEndTime());
            if (!CollectionUtils.isEmpty(nextTwo)) {
                financialBenefitsApi.setNextPanicStartTime(DateTimeUtils.localDateTimeParseLong(nextTwo.get(0).getPanicStartTime()));
                financialBenefitsApi.setNextPanicEndTime(DateTimeUtils.localDateTimeParseLong(nextTwo.get(0).getPanicEndTime()));
            }
            return financialBenefitsApi;
        }
        //第三步：没有进行中，没有未开启的，查询抢购已结束的
        final List<FinancialBenefits> prev = this.queryFinancialLessThanNow(financialId, now);
        if (!CollectionUtils.isEmpty(prev)) {
            final FinancialBenefits benefits = prev.get(0);
            final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                    .id(benefits.getId())
                    .financialId(benefits.getFinancialId())
                    .productName(benefits.getProductName())
                    .panicStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicStartTime()))
                    .panicEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicEndTime()))
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                    .panicTotalLimit(benefits.getPanicTotalLimit())
                    .minAmount(benefits.getMinAmount())
                    .purchaseLimit(benefits.getPurchaseLimit())
                    .remainLimit(benefits.getRemainLimit())
                    .financialStatus(benefits.getFinancialStatus())
                    .financialRate(benefits.getFinancialRate())
                    .activityStatus("抢购已结束")
                    .alsoLockUpAmount(benefits.getAlsoLockUpAmount())
                    .alsoTempAmount(benefits.getAlsoTempAmount())
                    .tFans(benefits.getTFans())
                    .incomeTotal(benefits.getIncomeTotal())
                    .tFansTotal(benefits.getTFansTotal())
                    .build();

            //查询抢购结束后续是否还有
            final List<FinancialBenefits> next = this.queryFinancialGreaterThanNow(financialId, benefits.getPanicEndTime());
            if (!CollectionUtils.isEmpty(next)) {
                financialBenefitsApi.setNextPanicStartTime(DateTimeUtils.localDateTimeParseLong(next.get(0).getPanicStartTime()));
                financialBenefitsApi.setNextPanicEndTime(DateTimeUtils.localDateTimeParseLong(next.get(0).getPanicEndTime()));
            }
            return financialBenefitsApi;
        }
        return null;
    }

    @Override
    public FinancialBenefitsApi queryFinancialBenefitsById(final int id) throws Exception {
        final FinancialBenefits financialBenefits = this.financialBenefitsMapper.selectByPrimaryKey(id);
        if (financialBenefits != null) {
            final LocalDateTime now = LocalDateTime.now();
            final FinancialBenefitsApi financialBenefitsApi = this.convertBenefitsToBenefitsApi(financialBenefits, now);
            final List<FinancialBenefits> nextTwo = this.queryFinancialGreaterThanTime(financialBenefits.getFinancialId(), financialBenefits
                    .getPanicEndTime());
            if (!CollectionUtils.isEmpty(nextTwo)) {
                financialBenefitsApi.setNextPanicStartTime(DateTimeUtils.localDateTimeParseLong(nextTwo.get(0)
                        .getPanicStartTime()));
                financialBenefitsApi.setNextPanicEndTime(DateTimeUtils.localDateTimeParseLong(nextTwo.get(0)
                        .getPanicEndTime()));
            }
            return financialBenefitsApi;
        }
        return null;
    }

    @Override
    public FinancialBenefitsApi queryFinancialBenefitsByIdExcludeNextInfo(final int id) {
        final FinancialBenefits financialBenefits = this.financialBenefitsMapper.selectByPrimaryKey(id);
        if (financialBenefits != null) {
            final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                    .id(financialBenefits.getId())
                    .financialId(financialBenefits.getFinancialId())
                    .productName(financialBenefits.getProductName())
                    .panicStartTime(DateTimeUtils.localDateTimeParseLong(financialBenefits.getPanicStartTime()))
                    .panicEndTime(DateTimeUtils.localDateTimeParseLong(financialBenefits.getPanicEndTime()))
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(financialBenefits.getInterestStartTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(financialBenefits.getInterestEndTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(financialBenefits.getUnlockTime()))
                    .panicTotalLimit(financialBenefits.getPanicTotalLimit())
                    .minAmount(financialBenefits.getMinAmount())
                    .purchaseLimit(financialBenefits.getPurchaseLimit())
                    .financialRate(financialBenefits.getFinancialRate())
                    .alsoLockUpAmount(financialBenefits.getAlsoLockUpAmount())
                    .alsoTempAmount(financialBenefits.getAlsoTempAmount())
                    .tFans(financialBenefits.getTFans())
                    .incomeTotal(financialBenefits.getIncomeTotal())
                    .tFansTotal(financialBenefits.getTFansTotal())
                    .build();
            return financialBenefitsApi;
        }
        return null;
    }

    @Override
    public int updateFinancialStatus(final int id) {
        final FinancialBenefits record = FinancialBenefits.builder()
                .id(id)
                .financialStatus(1)
                .build();
        return this.financialBenefitsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据financialid,当前时间查询抢购结束时间小于now的数据
     *
     * @param financialId
     * @param now
     * @return
     */
    public List<FinancialBenefits> queryFinancialLessThanNow(final int financialId, final LocalDateTime now) {
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialIdEqualTo(financialId);
        criteria.andPanicEndTimeLessThan(now);
        example.setOrderByClause("panic_end_time DESC");
        return this.financialBenefitsMapper.selectByExample(example);
    }

    /**
     * 根据financialid,当前时间查询抢购开始时间大于now的数据
     *
     * @param financialId
     * @param now
     * @return
     */
    public List<FinancialBenefits> queryFinancialGreaterThanNow(final int financialId, final LocalDateTime now) {
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialIdEqualTo(financialId);
        criteria.andPanicStartTimeGreaterThan(now);
        example.setOrderByClause("panic_start_time");
        return this.financialBenefitsMapper.selectByExample(example);
    }

    /**
     * 根据financialId,抢购结束时间查询大于等于抢购结束时间的产品。
     *
     * @param financialId
     * @param time
     * @return
     */
    public List<FinancialBenefits> queryFinancialGreaterThanTime(final int financialId, final LocalDateTime time) {
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialIdEqualTo(financialId);
        criteria.andPanicStartTimeGreaterThanOrEqualTo(time);
        example.setOrderByClause("panic_start_time");
        return this.financialBenefitsMapper.selectByExample(example);
    }

    @Override
    public List<FinancialBenefits> queryFinancialNotCalactionLockUp(final LocalDateTime now) {
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andPanicEndTimeLessThan(now);
        criteria.andFinancialStatusEqualTo(0);
        criteria.andUnlockTimeGreaterThan(now);
        return this.financialBenefitsMapper.selectByExample(example);
    }

    /**
     * 查询在抢购时间段内的产品
     *
     * @param now
     * @param financialId
     * @return
     */
    @Override
    public List<FinancialBenefits> queryFinancialInPanic(final LocalDateTime now, final int financialId) {
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andPanicStartTimeLessThan(now);
        criteria.andPanicEndTimeGreaterThan(now);
        return this.financialBenefitsMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> queryFinancialBenefitsReMap(final FinancialBenefitsApi financialBenefitsApi, final Page<FinancialBenefits> page) {
        final Map<String, Object> entity = new HashMap<>(2);
        final List<FinancialBenefitsApi> financialBenefitsApis = this.queryFinancialBenefits(financialBenefitsApi, page);
        entity.put("grid_data", financialBenefitsApis);
        entity.put("statistical", this.financialLockUpService.statisticalAmount(financialBenefitsApi));
        return entity;
    }

    @Override
    public List<Integer> queryFinancialFinancialId(final FinancialBenefitsApi financialBenefitsApi, final LocalDateTime now, final int type) {
        final List<Integer> financialBenefitsIds = new ArrayList<>();
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        if (financialBenefitsApi.getFinancialId() != null) {
            criteria.andFinancialIdEqualTo(financialBenefitsApi.getFinancialId());
        }
        if (now != null) {
            criteria.andUnlockTimeGreaterThan(now);
        }
        if (type == 1) {
            criteria.andFinancialStatusEqualTo(1);
        }
        this.financialBenefitsMapper.selectByExample(example)
                .stream()
                .forEach(event -> financialBenefitsIds.add(event.getId()));
        return financialBenefitsIds;
    }

    /**
     * 根据panic判断是否存在抢购时间段内的数据
     * @param panicStart
     * @param panicEnd
     * @param type
     * @param id
     * @param financialId
     * @return
     */
    public List<FinancialBenefits> queryFinancialBetweenPanic(final long panicStart, final long panicEnd, final int type, final int id, final int financialId) {
        final LocalDateTime panicStartTime = DateTimeUtils.longParseLocalDateTime(panicStart);
        final LocalDateTime panicEndTime = DateTimeUtils.longParseLocalDateTime(panicEnd);

        final FinancialBenefitsExample example = new FinancialBenefitsExample();

        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialIdEqualTo(financialId);
        if (type == 1) {
            criteria.andIdNotEqualTo(id);
        }

        criteria.andOr(panicStartTime, panicEndTime);
        return this.financialBenefitsMapper.selectByExample(example);
    }

    private List<FinancialBenefitsApi> convertPojoToApi(final List<FinancialBenefits> financialBenefits) {
        final LocalDateTime now = LocalDateTime.now();
        final List<FinancialBenefitsApi> lists = new ArrayList<>();
        for (final FinancialBenefits benefits : financialBenefits) {
            lists.add(this.convertBenefitsToBenefitsApi(benefits, now));
        }
        return lists;
    }

    public FinancialBenefitsApi convertBenefitsToBenefitsApi(final FinancialBenefits benefits, final LocalDateTime now) {
        final String statusName;
        if (now.isBefore(benefits.getPanicStartTime())) {
            statusName = "未开启";
        } else if (now.compareTo(benefits.getPanicStartTime()) >= 0 && now.compareTo(benefits.getPanicEndTime()) < 0) {
            if (benefits.getPanicTotalLimit() == null) {
                statusName = "抢购进行中";
            } else {
                if (benefits.getRemainLimit() != null) {
                    if ((benefits.getRemainLimit().compareTo(new BigDecimal(0)) != 1)) {
                        statusName = "抢购已结束";
                    } else {
                        statusName = "抢购进行中";
                    }
                } else {
                    statusName = "抢购进行中";
                }
            }
        } else {
            statusName = "抢购已结束";
        }
        Financial financial = null;
        try {
            financial = this.financialService.queryOneFinancial(benefits.getFinancialId());
        } catch (final Exception e) {
            FinancialBenefitsServiceImpl.logger.info("查询抢购时间段内的产品异常： {}", e);
        }
        final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                .id(benefits.getId())
                .financialId(benefits.getFinancialId())
                .productName(benefits.getProductName())
                .panicStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicStartTime()))
                .panicEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicEndTime()))
                .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                .panicTotalLimit(benefits.getPanicTotalLimit())
                .minAmount(benefits.getMinAmount())
                .purchaseLimit(benefits.getPurchaseLimit())
                .remainLimit(benefits.getRemainLimit())
                .financialStatus(benefits.getFinancialStatus())
                .financialRate(benefits.getFinancialRate())
                .activityStatus(statusName)
                .numericalv(financial == null ? 0 : financial.getNumericalv())
                .alsoLockUpAmount(benefits.getAlsoLockUpAmount())
                .alsoTempAmount(benefits.getAlsoTempAmount())
                .tFans(benefits.getTFans())
                .incomeTotal(benefits.getIncomeTotal())
                .tFansTotal(benefits.getTFansTotal())
                .build();
        return financialBenefitsApi;
    }

}
