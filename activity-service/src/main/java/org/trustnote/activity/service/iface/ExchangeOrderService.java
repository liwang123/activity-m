package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.dto.ExchangeOrderDTO;
import org.trustnote.activity.common.model.ResponseResult;
import org.trustnote.activity.common.pojo.ExchangeOrder;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.math.BigDecimal;

public interface ExchangeOrderService {
    void insertExchangeOrder(final ExchangeOrderDTO exchangeOrderDTO);

    Page getAllOrder(int pageIndex, int pageSize, int status, String condition);

    ResponseResult manualMoney(Long id);

    BigDecimal checkBalance();

    String getExchangeOrder();

    void sendMail(ExchangeOrder exchangeOrder);

    BigDecimal getRate();

}
