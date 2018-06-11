package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.ExchangeRate;

import java.math.BigDecimal;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
public interface ExchangeRateService {
    int saveExchangeRate(ExchangeRate exchangeRate) throws Exception;
    ExchangeRate queryExchangeRate(Byte type) throws Exception;
    BigDecimal conversion(Byte type, BigDecimal purchase, Integer turn) throws Exception;
}
