package org.trustnote.activity.service.impl;

import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.FinancialLockUpExample;
import org.trustnote.activity.common.pojo.FinancialLockUp;
import org.trustnote.activity.service.iface.FinancialLockUpService;
import org.trustnote.activity.skeleton.mybatis.mapper.FinancialLockUpMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class FinancialLockUpServiceImpl implements FinancialLockUpService {

    @Resource
    private FinancialLockUpMapper financialLockUpMapper;

    @Override
    public List<FinancialLockUp> queryFinancialLockUp(final Page<FinancialLockUp> page, final int benefitsId) throws Exception {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andFinancialBenefitsIdEqualTo(benefitsId);
        example.setOrderByClause("crt_time DESC");
        return this.financialLockUpMapper.selectByExamplePage(page, new FinancialLockUpExample());
    }

    @Override
    public int saveFinancialLockUp(final FinancialLockUp financialLockUp) throws Exception {
        return this.financialLockUpMapper.insertSelective(financialLockUp);
    }

    @Override
    public List<FinancialLockUp> queryFincialLockUpByDeviceAddress(final String deviceAddress) throws Exception {
        final FinancialLockUpExample example = new FinancialLockUpExample();
        final FinancialLockUpExample.Criteria criteria = example.createCriteria();
        criteria.andDeviceAddressEqualTo(deviceAddress);
        return this.financialLockUpMapper.selectByExample(example);
    }
}
