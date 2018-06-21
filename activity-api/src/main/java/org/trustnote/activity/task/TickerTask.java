package org.trustnote.activity.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

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
