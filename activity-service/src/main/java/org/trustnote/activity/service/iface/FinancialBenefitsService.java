package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl
 */
public interface FinancialBenefitsService {
    FinancialBenefits queryOneFinancialBenefits(int id) throws Exception;

    List<FinancialBenefitsApi> queryFinancialBenefits(Page<FinancialBenefits> page) throws Exception;

    int updateFinancialBenefits(FinancialBenefitsApi financialBenefitsApi) throws Exception;

    int insertFinancialBenefits(FinancialBenefitsApi financialBenefitsApi) throws Exception;

    FinancialBenefitsApi queryFinancialBenefitsByFinancialId(int financialId) throws Exception;

    int updateFinancialStatus(int id) throws Exception;
}
