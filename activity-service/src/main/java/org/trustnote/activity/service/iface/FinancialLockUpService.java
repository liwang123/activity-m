package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.FinancialLockUp;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl
 */
public interface FinancialLockUpService {
    List<FinancialLockUp> queryFinancialLockUp(Page<FinancialLockUp> page, int benefitsId) throws Exception;

    int saveFinancialLockUp(FinancialLockUp financialLockUp) throws Exception;

    List<FinancialLockUp> queryFincialLockUpByDeviceAddress(String deviceAddress) throws Exception;

    List<Map<String, String>> export(int benefitsId) throws Exception;

    void saveInComeAmount();

    Map<String, BigDecimal> participate() throws Exception;
}
