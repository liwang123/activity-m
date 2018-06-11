package org.trustnote.activity.service.impl;

import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.FinancialExample;
import org.trustnote.activity.common.pojo.Financial;
import org.trustnote.activity.service.iface.FinancialService;
import org.trustnote.activity.skeleton.mybatis.mapper.FinancialMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class FinancialServieImpl implements FinancialService {

    @Resource
    private FinancialMapper financialMapper;

    @Override
    public Financial queryOneFinancial(final int id) throws Exception {
        return this.financialMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Financial> queryFinancial() throws Exception {
        return this.financialMapper.selectByExample(new FinancialExample());
    }

    @Override
    public int updateFinancial(final int id, final Float rate) throws Exception {
        final Financial financial = new Financial();
        financial.setId(id);
        financial.setFinancialRate(rate);
        return this.financialMapper.updateByPrimaryKeySelective(financial);
    }
}
