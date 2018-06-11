package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.ExchangeRateExample;
import org.trustnote.activity.common.pojo.ExchangeRate;
import org.trustnote.activity.service.iface.ExchangeRateService;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeRateMapper;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private static final Logger logger = LogManager.getLogger(ExchangeRateServiceImpl.class);
    @Resource
    private ExchangeRateMapper exchangeRateMapper;

    @Override
    public int saveExchangeRate(ExchangeRate exchangeRate) {
        ExchangeRateExample example = new ExchangeRateExample();
        ExchangeRateExample.Criteria criteria = example.createCriteria();
        criteria.andExchangeTypeEqualTo(exchangeRate.getExchangeType());
        ExchangeRate record = new ExchangeRate();
        record.setRate(exchangeRate.getRate());
        return exchangeRateMapper.updateByExampleSelective(record, example);
    }

    @Override
    public ExchangeRate queryExchangeRate(Byte type) {
        ExchangeRateExample example = new ExchangeRateExample();
        ExchangeRateExample.Criteria criteria = example.createCriteria();
        criteria.andExchangeTypeEqualTo(type);
        List<ExchangeRate> exchangeRates = exchangeRateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(exchangeRates)) return null;
        return exchangeRates.get(0);
    }

    @Override
    public BigDecimal conversion(Byte type, BigDecimal purchase, Integer turn) throws Exception {
        ExchangeRate exchangeRate = this.queryExchangeRate(type);
        if (exchangeRate == null) return new BigDecimal(0);
        BigDecimal result = null;
        if (turn == 1) {
            result = purchase.multiply(exchangeRate.getRate());
        }else if (turn == 2) {
            result = purchase.divide(exchangeRate.getRate(), 8, BigDecimal.ROUND_HALF_DOWN);
        }

        return result;
    }
}
