package org.trustnote.activity.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.trustnote.activity.common.example.ExchangeOrderExample;
import org.trustnote.activity.common.pojo.ExchangeOrder;
import org.trustnote.activity.common.utils.OkHttpUtils;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeOrderMapper;

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

    @Value("${blockchainUrl}")
    private String blockchainUrl;

    /**
     * 每隔10分钟处理未支付订单
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void handleOrders() {
        final ExchangeOrderExample exchangeOrderExample = new ExchangeOrderExample();
        exchangeOrderExample.createCriteria().andStatesEqualTo(1);
        final List<ExchangeOrder> exchangeOrders = this.exchangeOrderMapper.selectByExample(exchangeOrderExample);
        exchangeOrders.stream()
                .forEach(order -> {
                    final String url = this.blockchainUrl + order.getToAddress();
                    final String body = OkHttpUtils.get(url, null);
                    System.out.println(body);
                    if (body != null) {
                        final BigDecimal btcMoney = new BigDecimal(body).divide(new BigDecimal(100000000), 8, BigDecimal.ROUND_HALF_EVEN);
                        if (btcMoney.compareTo(new BigDecimal(0.01)) == 1) {
                            order.setReceipt(btcMoney);
                            order.setRate(this.exchangeOrderService.getRate());
                            order.setQuantity(order.getReceipt().divide(order.getRate(), 8, BigDecimal.ROUND_HALF_EVEN));
                            order.setStates(5);
                            this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                        }
                    }
                });
    }


    /**
     * 每隔10分钟处理待确认订单
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void unconfirmedOrders() {
        final ExchangeOrderExample exchangeOrderExample = new ExchangeOrderExample();
        exchangeOrderExample.createCriteria().andStatesEqualTo(5);
        final List<ExchangeOrder> exchangeOrders = this.exchangeOrderMapper.selectByExample(exchangeOrderExample);
        exchangeOrders.stream()
                .forEach(order -> {
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
                                order.setStates(3);
                                this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                                this.exchangeOrderService.sendMail(order);
                            }
                        }
                    }
                });
    }

}
