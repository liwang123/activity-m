package org.trustnote.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trustnote.activity.common.dto.ExchangeOrderDTO;
import org.trustnote.activity.common.model.ResponseResult;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

@RestController
@RequestMapping("/exchange-order")
public class ExchangeOrderController {

    @Autowired
    private ExchangeOrderService exchangeOrderService;

    /**
     * 保存订单
     *
     * @param exchangeOrderDTO
     * @return
     */
    @RequestMapping(value = "/save-order", method = RequestMethod.POST)
    public ResponseResult createOrder(final ExchangeOrderDTO exchangeOrderDTO) {
        this.exchangeOrderService.insertExchangeOrder(exchangeOrderDTO);
        return ResponseResult.success();
    }


    /**
     * 查询订单
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/query-order", method = RequestMethod.GET)
    public ResponseResult queryOrder(final int pageIndex, final int pageSize, final int status) {
        final Page allOrder = this.exchangeOrderService.getAllOrder(pageIndex, pageSize, status);
        return ResponseResult.success(allOrder);
    }

    /**
     * 手动打款
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/manual-money", method = RequestMethod.POST)
    public ResponseResult manualMoney(final Long id) {
        return this.exchangeOrderService.manualMoney(id);
    }


}
