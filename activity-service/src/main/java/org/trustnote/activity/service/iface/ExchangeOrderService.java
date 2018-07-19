package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.dto.ExchangeOrderDTO;
import org.trustnote.activity.common.model.ResponseResult;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

public interface ExchangeOrderService {
    void insertExchangeOrder(final ExchangeOrderDTO exchangeOrderDTO);

    Page getAllOrder(int pageIndex, int pageSize, int status);

    ResponseResult manualMoney(Long id);
}
