package org.trustnote.activity.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.trustnote.activity.service.iface.FinancialLockUpService;

/**
 * 〈抓取数据task〉
 *
 * @author WangYu
 * @create 2018/5/29
 * @since 1.0.0
 */
@Component
@EnableScheduling
public class TickerTask {

    private static final Logger logger = LogManager.getLogger(TickerTask.class);

    @Autowired
    private FinancialLockUpService financialLockUpService;

    /**
     * 每天2:00执行计算在计划收益期间的收益
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void calculateInComeAmount() {
        this.financialLockUpService.saveInComeAmount();
    }

    /**
     * 每隔10分钟计算周套餐产品的剩余额度
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void validationPaymentWeek() {
        this.financialLockUpService.validationPaymentWeek();
    }

    /**
     * 每隔35分钟计算其他套餐产品的剩余额度
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void validationPaymentOther() {
        this.financialLockUpService.validationPaymentWeek();
    }


    /**
     * 每天0:30执行计算抢购活动结束的lockUpAmount
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void calculateLockUpAmount() {
        this.financialLockUpService.saveLockUpAmount();
    }

    /**
     @Scheduled(cron = "0/2 * * * * ? ")
     public void getBizTTTBTC(){
     System.out.println(1111);
     tickerService.saveBitZTicker("ttt_btc");
     }

     @Scheduled(cron = "0/2 * * * * ? ")
     public void getBizTTTETH(){
     tickerService.saveBitZTicker("ttt_eth");
     }

     @Scheduled(cron = "0/2 * * * * ? ")
     public void getBizBTCDKKT(){
     tickerService.saveBitZTicker("btc_dkkt");
     }

     @Scheduled(cron = "0/2 * * * * ? ")
     public void getBizETHDKKT(){
     tickerService.saveBitZTicker("eth_dkkt");
     }*/

}
