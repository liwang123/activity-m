package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl
 */
public interface FinancialBenefitsService {
    FinancialBenefits queryOneFinancialBenefits(int id) throws Exception;

    List<FinancialBenefitsApi> queryFinancialBenefits(FinancialBenefitsApi financialBenefitsApi, Page<FinancialBenefits> page) throws Exception;

    int updateFinancialBenefits(FinancialBenefitsApi financialBenefitsApi) throws Exception;

    int insertFinancialBenefits(FinancialBenefitsApi financialBenefitsApi) throws Exception;

    FinancialBenefitsApi queryFinancialBenefitsByFinancialId(int financialId) throws Exception;

    FinancialBenefitsApi queryFinancialBenefitsById(int id) throws Exception;

    FinancialBenefitsApi queryFinancialBenefitsByIdExcludeNextInfo(int id) throws Exception;

    int updateFinancialStatus(int id) throws Exception;

    /**
     * 抢购结束时间小于当前时间、并且未发收益的理财产品
     *
     * @param now
     * @return
     * @throws Exception
     */
    List<FinancialBenefits> queryFinancialNotCalactionLockUp(LocalDateTime now) throws Exception;

    List<FinancialBenefits> queryFinancialInPanic(LocalDateTime now, int financialId) throws Exception;

    Map<String, Object> queryFinancialBenefitsReMap(FinancialBenefitsApi financialBenefitsApi, Page<FinancialBenefits> page);

    /**
     * 根据套餐类型ID、当前时间查询未解锁的产品ID
     *
     * @param financialBenefitsApi
     * @param now
     * @return
     */
    List<Integer> queryFinancialFinancialId(FinancialBenefitsApi financialBenefitsApi, LocalDateTime now, int type);
}
