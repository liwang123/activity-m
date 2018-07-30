package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.api.FinancialLockSearchApi;
import org.trustnote.activity.common.api.FinancialLockUpApi;
import org.trustnote.activity.common.pojo.FinancialLockUp;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl
 */
public interface FinancialLockUpService {
    List<FinancialLockUpApi> queryFinancialLockUp(Page<FinancialLockUp> page, int benefitsId) throws Exception;

    List<FinancialLockSearchApi> queryFinancialLockUp(Page<FinancialLockUp> page, FinancialBenefitsApi financialBenefitsApi, int type, String value) throws Exception;

    int saveFinancialLockUp(FinancialLockUp financialLockUp) throws Exception;

    List<FinancialLockUpApi> queryFincialLockUpByDeviceAddress(String deviceAddress) throws Exception;

    List<Map<String, String>> export(int benefitsId) throws Exception;

    List<Map<String, String>> exportTFS(int benefitsId) throws Exception;

    void validationPayment();

    void saveInComeAmount();

    Map<String, BigDecimal> participate() throws Exception;

    int updateFinancialLockUp(final int id, final int orderAmount) throws Exception;

    FinancialLockUp queryLockUp(final FinancialLockUp financialLockUp) throws Exception;

    /**
     * 根据产品ID查询统计信息
     *
     * @param financialBenefitsApi
     * @return
     */
    Map<String, BigDecimal> statisticalAmount(FinancialBenefitsApi financialBenefitsApi);

    String readWalletAddress(final List list);

    String manualTFans();
}
