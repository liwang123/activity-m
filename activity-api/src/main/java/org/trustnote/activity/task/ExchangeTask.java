package org.trustnote.activity.task;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.trustnote.activity.common.example.ExchangeOrderExample;
import org.trustnote.activity.common.pojo.ExchangeOrder;
import org.trustnote.activity.common.utils.OkHttpUtils;
import org.trustnote.activity.service.iface.ExchangeOrderService;
import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeOrderMapper;

import java.math.BigDecimal;
import java.util.List;

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
                    //查询order并提出BTC地址是否有钱
//                    final String url = "http://150.109.32.56:9000/getBtcBalance";
//                    final HashMap<String, String> param = Maps.newHashMap();
//                    final String body = OkHttpUtils.get(url, param);
//                    final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
//                    final BigDecimal btcMoney = new BigDecimal(jsonObject.get("data").toString());
                    final String url = "https://insight.bitpay.com/api/addr/" + order.getToAddress() + "/?noTxList=1";
                    final String body = OkHttpUtils.get(url, null);
                    final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
                    final BigDecimal btcMoney = new BigDecimal(jsonObject.get("balance").toString());
                    if (btcMoney.compareTo(new BigDecimal(0.01)) == 1) {
                        order.setReceipt(btcMoney);
                        order.setStates(5);
                        this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                    }
                });
    }


    /**
     * 每隔10分钟处理待确认订单
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void unconfirmedOrders() {
        final ExchangeOrderExample exchangeOrderExample = new ExchangeOrderExample();
        exchangeOrderExample.createCriteria().andStatesEqualTo(5);
        final List<ExchangeOrder> exchangeOrders = this.exchangeOrderMapper.selectByExample(exchangeOrderExample);
        exchangeOrders.stream()
                .forEach(order -> {
                    final String url = "https://insight.bitpay.com/api/addr/" + order.getToAddress() + "/?noTxList=1";
                    final String body = OkHttpUtils.get(url, null);
                    final JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
                    final BigDecimal balance = new BigDecimal(jsonObject.get("balance").toString());
                    final int unconfirmed_transactions_count = (int) jsonObject.get("unconfirmedBalance");
                    if (unconfirmed_transactions_count == 0) {
                        if (balance.compareTo(new BigDecimal(0.05)) == -1) {
                            this.exchangeOrderService.manualMoney(order.getId());
                        } else {
                            this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
                            this.exchangeOrderService.sendMail(order);
                        }
                    }
                });
    }

}
