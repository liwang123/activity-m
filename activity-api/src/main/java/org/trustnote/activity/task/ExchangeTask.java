//package org.trustnote.activity.task;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.trustnote.activity.common.example.ExchangeOrderExample;
//import org.trustnote.activity.common.pojo.ExchangeOrder;
//import org.trustnote.activity.service.iface.ExchangeOrderService;
//import org.trustnote.activity.skeleton.mybatis.mapper.ExchangeOrderMapper;
//
//import java.math.BigDecimal;
//import java.util.List;
//
///**
// * @author zhuxl
// */
//@Component
//@EnableScheduling
//public class ExchangeTask {
//
//    @Autowired
//    private ExchangeOrderService exchangeOrderService;
//
//    @Autowired
//    private ExchangeOrderMapper exchangeOrderMapper;
//
//    /**
//     * 每隔5分钟处理未支付订单
//     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void handleOrders() {
//        final ExchangeOrderExample exchangeOrderExample = new ExchangeOrderExample();
//        exchangeOrderExample.createCriteria().andStatesEqualTo(1);
//        final List<ExchangeOrder> exchangeOrders = this.exchangeOrderMapper.selectByExample(exchangeOrderExample);
//        System.out.println("测试");
//        exchangeOrders.stream()
//                .forEach(order -> {
//                    //查询order并提出BTC地址是否有钱
//
//                    //如果有钱
//                    order.setReceipt(new BigDecimal(1));
//                    if (order.getReceipt().compareTo(new BigDecimal(0.5)) == -1) {
//                        this.exchangeOrderService.manualMoney(order.getId());
//                    } else {
//                        order.setStates(3);
//                    }
//                    this.exchangeOrderMapper.updateByPrimaryKeySelective(order);
//                });
//    }
//
//
//}
