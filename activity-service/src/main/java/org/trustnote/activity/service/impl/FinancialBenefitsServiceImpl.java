package org.trustnote.activity.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.enume.PositionTypeEnum;
import org.trustnote.activity.common.example.FinancialBenefitsExample;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.common.utils.DateTimeUtils;
import org.trustnote.activity.service.iface.FinancialBenefitsService;
import org.trustnote.activity.skeleton.mybatis.mapper.FinancialBenefitsMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class FinancialBenefitsServiceImpl implements FinancialBenefitsService {

    @Resource
    private FinancialBenefitsMapper financialBenefitsMapper;

    @Override
    public FinancialBenefits queryOneFinancialBenefits(final int id) throws Exception {
        return this.financialBenefitsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FinancialBenefitsApi> queryFinancialBenefits(final Page<FinancialBenefits> page) throws Exception {
        final List<FinancialBenefits> financialBenefits = this.financialBenefitsMapper.selectByExampleAndPage(page, new FinancialBenefitsExample());
        return this.convertPojoToApi(financialBenefits);
    }

    @Override
    public int updateFinancialBenefits(final FinancialBenefitsApi financialBenefitsApi) throws Exception {
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
                .build();
        return this.financialBenefitsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertFinancialBenefits(final FinancialBenefitsApi financialBenefitsApi) throws Exception {
        List<FinancialBenefits> panic = this.queryFinancialBetweenPanic(financialBenefitsApi.getPanicStartTime());
        if (!CollectionUtils.isEmpty(panic)) {
            return 0;
        }
        panic = this.queryFinancialBetweenPanic(financialBenefitsApi.getPanicEndTime());
        if (!CollectionUtils.isEmpty(panic)) {
            return 0;
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
        if (!CollectionUtils.isEmpty(financialBenefits)) {
            final List<FinancialBenefits> nextFinancialBenefits = this.queryFinancialGreaterThanNow(financialId, now);
            final FinancialBenefits benefits = financialBenefits.get(0);
            final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                    .id(benefits.getId())
                    .financialId(benefits.getFinancialId())
                    .productName(benefits.getProductName())
                    .panicStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicStartTime()))
                    .panicEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicEndTime()))
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                    .panicTotalLimit(benefits.getPanicTotalLimit())
                    .minAmount(benefits.getMinAmount())
                    .purchaseLimit(benefits.getPurchaseLimit())
                    .remainLimit(benefits.getRemainLimit())
                    .financialStatusName("进行中")
                    .build();
            if (!CollectionUtils.isEmpty(nextFinancialBenefits)) {
                financialBenefitsApi.setNextPanicStartTime(DateTimeUtils.localDateTimeParseLong(nextFinancialBenefits.get(0).getPanicStartTime()));
                financialBenefitsApi.setNextPanicEndTime(DateTimeUtils.localDateTimeParseLong(nextFinancialBenefits.get(0).getPanicEndTime()));
            }
            return financialBenefitsApi;
        }
        //第二步：如果没有进行中 优先查询是否有未开启的
        final List<FinancialBenefits> nextFinancialBenefits = this.queryFinancialGreaterThanNow(financialId, now);
        if (!CollectionUtils.isEmpty(nextFinancialBenefits)) {
            final FinancialBenefits benefits = nextFinancialBenefits.get(0);
            final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                    .id(benefits.getId())
                    .financialId(benefits.getFinancialId())
                    .productName(benefits.getProductName())
                    .panicStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicStartTime()))
                    .panicEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicEndTime()))
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                    .panicTotalLimit(benefits.getPanicTotalLimit())
                    .minAmount(benefits.getMinAmount())
                    .purchaseLimit(benefits.getPurchaseLimit())
                    .remainLimit(benefits.getRemainLimit())
                    .financialStatusName("未开启")
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
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                    .panicTotalLimit(benefits.getPanicTotalLimit())
                    .minAmount(benefits.getMinAmount())
                    .purchaseLimit(benefits.getPurchaseLimit())
                    .remainLimit(benefits.getRemainLimit())
                    .financialStatusName("抢购已结束")
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
    public int updateFinancialStatus(final int id) throws Exception {
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
        final Page<FinancialBenefits> page = new Page<>(1, 1);
        return this.financialBenefitsMapper.selectByExampleAndPage(page, example);
    }

    /**
     * 根据financialid,当前时间查询抢购开始时间大于now的数据
     *
     * @param financialId
     * @param now
     * @return
     */
    public List<FinancialBenefits> queryFinancialGreaterThanNow(final int financialId, final LocalDateTime now) throws Exception {
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialIdEqualTo(financialId);
        criteria.andPanicStartTimeGreaterThan(now);
        example.setOrderByClause("panic_start_time");
        final Page<FinancialBenefits> page = new Page<>(1, 1);
        return this.financialBenefitsMapper.selectByExampleAndPage(page, example);
    }

    /**
     * 根据panic判断是否存在抢购时间段内的数据
     *
     * @param panic
     * @return
     */
    public List<FinancialBenefits> queryFinancialBetweenPanic(final long panic) throws Exception {
        final LocalDateTime panicTime = DateTimeUtils.longParseLocalDateTime(panic);
        final FinancialBenefitsExample example = new FinancialBenefitsExample();
        final FinancialBenefitsExample.Criteria criteria = example.createCriteria();
        criteria.andPanicStartTimeLessThanOrEqualTo(panicTime);
        criteria.andPanicEndTimeGreaterThanOrEqualTo(panicTime);
        return this.financialBenefitsMapper.selectByExample(example);
    }

    private List<FinancialBenefitsApi> convertPojoToApi(final List<FinancialBenefits> financialBenefits) {
        final LocalDateTime now = LocalDateTime.now();
        final List<FinancialBenefitsApi> lists = new ArrayList<>();
        for (final FinancialBenefits benefits : financialBenefits) {
            final PositionTypeEnum positionTypeEnum = PositionTypeEnum.getItem(benefits.getFinancialStatus());
            String statusName = null;
            if ("".equals(positionTypeEnum.getValue())) {
                if (now.isBefore(benefits.getPanicStartTime())) {
                    statusName = "未开启";
                } else if (now.compareTo(benefits.getPanicStartTime()) >= 0 && now.compareTo(benefits.getPanicEndTime()) < 0) {
                    statusName = "抢购进行中";
                } else if (now.isAfter(benefits.getPanicEndTime()) && now.isBefore(benefits.getInterestStartTime())) {
                    statusName = "抢购已结束";
                } else if (now.isAfter(benefits.getUnlockTime())) {
                    statusName = "已解锁";
                } else if (now.isAfter(benefits.getInterestStartTime()) && now.isBefore(benefits.getUnlockTime())) {
                    statusName = "未解锁";
                }
            } else {
                statusName = positionTypeEnum.getValue();
            }
            final FinancialBenefitsApi financialBenefitsApi = FinancialBenefitsApi.builder()
                    .id(benefits.getId())
                    .financialId(benefits.getFinancialId())
                    .productName(benefits.getProductName())
                    .panicStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicStartTime()))
                    .panicEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getPanicEndTime()))
                    .interestStartTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestEndTime()))
                    .interestEndTime(DateTimeUtils.localDateTimeParseLong(benefits.getInterestStartTime()))
                    .unlockTime(DateTimeUtils.localDateTimeParseLong(benefits.getUnlockTime()))
                    .panicTotalLimit(benefits.getPanicTotalLimit())
                    .minAmount(benefits.getMinAmount())
                    .purchaseLimit(benefits.getPurchaseLimit())
                    .remainLimit(benefits.getRemainLimit())
                    .financialStatusName(statusName)
                    .build();
            lists.add(financialBenefitsApi);
        }
        return lists;
    }
}
