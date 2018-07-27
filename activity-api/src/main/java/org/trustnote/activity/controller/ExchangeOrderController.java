package org.trustnote.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trustnote.activity.common.dto.ConfirmBalanceDTO;
import org.trustnote.activity.common.dto.ExchangeOrderDTO;
import org.trustnote.activity.common.model.ResponseResult;
import org.trustnote.activity.common.utils.DateTimeUtils;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

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
    @RequestMapping(value = "/save-order", method = RequestMethod.GET)
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
    public ResponseResult queryOrder(final int pageIndex, final int pageSize, final int status, final String condition) {
        final Page allOrder = this.exchangeOrderService.getAllOrder(pageIndex, pageSize, status, condition);
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

    /**
     * 地址余额
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/check-balance", method = RequestMethod.GET)
    public ResponseResult checkBalance() {
        final BigDecimal checkBalance = this.exchangeOrderService.checkBalance();
        return ResponseResult.success(checkBalance);
    }

    /**
     * 确认余额
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/confirm-balance", method = RequestMethod.GET)
    public ResponseResult confirmBalance(final Long id) {
        final ConfirmBalanceDTO confirmBalanceDTO = this.exchangeOrderService.confirmBalance(id);
        return ResponseResult.success(confirmBalanceDTO);
    }


    /**
     * 导出execl
     *
     * @param response
     */
    @RequestMapping(value = "/export-order", method = RequestMethod.GET)
    public void exportCathecticCountCSV(final HttpServletResponse response) {
        final String fileName = "cathectic Total" + DateTimeUtils.formatDateTime(DateTimeUtils.secondPattern) + ".csv";
        response.setContentType("text/csv;charset=GBK");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            final String content = this.exchangeOrderService.getExchangeOrder();
            printWriter.write(content);
            printWriter.flush();
        } catch (final IOException e) {

        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
