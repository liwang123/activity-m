package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.FinancialApi;
import org.trustnote.activity.common.pojo.Financial;

import java.util.List;

/**
 * @author zhuxl
 */
public interface FinancialService {
    Financial queryOneFinancial(int id) throws Exception;

    List<FinancialApi> queryFinancial() throws Exception;

    int updateFinancial(int id, Float rate) throws Exception;
}
