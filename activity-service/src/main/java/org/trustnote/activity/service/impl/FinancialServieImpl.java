package org.trustnote.activity.service.impl;

import org.springframework.stereotype.Service;
import org.trustnote.activity.common.api.FinancialApi;
import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.example.FinancialExample;
import org.trustnote.activity.common.pojo.Financial;
import org.trustnote.activity.service.iface.FinancialBenefitsService;
import org.trustnote.activity.service.iface.FinancialService;
import org.trustnote.activity.skeleton.mybatis.mapper.FinancialMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class FinancialServieImpl implements FinancialService {

    @Resource
    private FinancialMapper financialMapper;
    @Resource
    private FinancialBenefitsService financialBenefitsService;

    @Override
    public Financial queryOneFinancial(final int id) throws Exception {
        return this.financialMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FinancialApi> queryFinancial() throws Exception {
        final List<FinancialApi> financialApis = new ArrayList<>();
        final List<Financial> financials = this.financialMapper.selectByExample(new FinancialExample());
        for (final Financial financial : financials) {
            final FinancialBenefitsApi financialBenefitsApi = this.financialBenefitsService.queryFinancialBenefitsByFinancialId(financial.getId());
            if (financialBenefitsApi != null) {
                final FinancialApi financialApi = FinancialApi.builder()
                        .id(financial.getId())
                        .financialName(financial.getFinancialName())
                        .financialRate(financialBenefitsApi.getFinancialRate())
                        .financialBenefitsId(financialBenefitsApi.getId())
                        .build();
                financialApis.add(financialApi);
            }
        }
        return financialApis;
    }

    @Override
    public int updateFinancial(final int id, final Float rate) throws Exception {
        final Financial financial = new Financial();
        financial.setId(id);
        financial.setFinancialRate(rate);
        return this.financialMapper.updateByPrimaryKeySelective(financial);
    }
}
