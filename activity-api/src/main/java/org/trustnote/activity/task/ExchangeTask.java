package org.trustnote.activity.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.trustnote.activity.common.enume.StatesEnum;
import org.trustnote.activity.common.example.ExchangeOrderExample;
import org.trustnote.activity.common.pojo.ExchangeOrder;
import org.trustnote.activity.common.pojo.ExchangeRate;
import org.trustnote.activity.common.utils.OkHttpUtils;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeOrderMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeRateMapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl
 */
@Component
@EnableScheduling
public class ExchangeTask {

    @Autowired
    private ExchangeOrderService exchangeOrderService;

    @Autowired
    private ExchangeOrderMapper exchangeOrderMapper;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;


    @Value("${blockchainUrl}")
    private String blockchainUrl;

    /**
     * 每隔10分钟处理未支付订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void handleOrders() {
        final ExchangeOrderExample exchangeOrderExample = new ExchangeOrderExample();
        exchangeOrderExample.createCriteria().andStatesEqualTo(1);
        final List<ExchangeOrder> exchangeOrders = this.exchangeOrderMapper.selectByExample(exchangeOrderExample);
        exchangeOrders.stream()
                .forEach(order -> {
                    final String url = this.blockchainUrl + order.getToAddress();
                    final String body = OkHttpUtils.get(url, null);
                    if (body != null) {
                        try {
                            final BigDecimal btcMoney = new BigDecimal(body).divide(new BigDecimal(100000000), 0, BigDecimal.ROUND_HALF_DOWN);
                            if (btcMoney.compareTo(new BigDecimal(0.01)) != -1) {
                                order.setReceipt(btcMoney);
                                order.setRate(this.exchangeOrderService.getRate());
                                order.setQuantity(order.getReceipt().divide(order.getRate(), 0, BigDecimal.ROUND_HALF_DOWN));
                                order.setStates(StatesEnum.NOT_CONFIRM.getCode());
                                this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                            } else if (btcMoney.compareTo(new BigDecimal(0)) == 1) {
                                order.setStates(StatesEnum.LESS.getCode());
                                this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                            }
                        } catch (final Exception e) {
                            this.exchangeOrderService.sendExceptionMail(e.getMessage() + order);
                        }
                    }
                });
    }
    /**
     * 每隔10分钟处理待确认订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void unconfirmedOrders() {
        final ExchangeOrderExample exchangeOrderExample = new ExchangeOrderExample();
        exchangeOrderExample.createCriteria().andStatesEqualTo(5);
        final List<ExchangeOrder> exchangeOrders = this.exchangeOrderMapper.selectByExample(exchangeOrderExample);
        exchangeOrders.stream()
                .forEach(order -> {
                    try {
                        final String url = this.blockchainUrl + order.getToAddress();
                        final Map<String, Integer> param = new HashMap<>();
                        if (order.getReceipt().compareTo(new BigDecimal(0.5)) == -1) {
                            param.put("confirmations", 2);
                            final String body = OkHttpUtils.get(url, param);
                            if (body != null) {
                                final BigDecimal balance = new BigDecimal(body);
                                if (balance.compareTo(order.getReceipt()) != -1) {
                                    this.exchangeOrderService.manualMoney(order.getId());
                                }
                            }
                        } else {
                            param.put("confirmations", 6);
                            final String body = OkHttpUtils.get(url, param);
                            if (body != null) {
                                final BigDecimal balance = new BigDecimal(body);
                                if (balance.compareTo(order.getReceipt()) != -1) {
                                    order.setStates(StatesEnum.TO_PAYMENT.getCode());
                                    this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                                    this.exchangeOrderService.sendMail(order);
                                }
                            }
                        }
                    } catch (final Exception e) {
                        this.exchangeOrderService.sendExceptionMail(e.getMessage() + order);
                    }
                });
    }

    /**
     * 每隔10分钟处理余额不足订单
     */
    @Scheduled(cron = "0 0/50 * * * ?")
    public void handleNotEnoughOrders() {
        final ExchangeOrderExample exchangeOrderExample = new ExchangeOrderExample();
        exchangeOrderExample.createCriteria().andStatesEqualTo(2);
        final List<ExchangeOrder> exchangeOrders = this.exchangeOrderMapper.selectByExample(exchangeOrderExample);
        exchangeOrders.stream()
                .forEach(order -> {
                    final BigDecimal checkBalance = this.exchangeOrderService.checkBalance();
                    if (checkBalance.compareTo(order.getQuantity()) == 1) {
                        order.setStates(StatesEnum.TO_PAYMENT.getCode());
                        this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                    }
                });
    }

    /**
     * 处理汇率
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void getExchangeRate() {
        final ExchangeRate exchangeRate = this.exchangeRateMapper.selectByPrimaryKey(3);
        exchangeRate.setRate(this.exchangeOrderService.getRate());
        this.exchangeRateMapper.updateByPrimaryKeySelective(exchangeRate);
    }


}
