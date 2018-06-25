package org.trustnote.activity.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.trustnote.activity.service.iface.FinancialLockUpService;

/**
 * @author zhuxl
 */
@Component
@EnableScheduling
public class FinancialTask {
    private static final Logger logger = LogManager.getLogger(FinancialTask.class);

    @Autowired
    private FinancialLockUpService financialLockUpService;

    /**
     * 每天2:00执行计算在计划收益期间的收益
     */
    @Scheduled(cron = "0 15 14 * * ?")
    public void calculateInComeAmount() {
        this.financialLockUpService.saveInComeAmount();
    }

    /**
     * 每隔5分钟计算周套餐产品的剩余额度
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void validationPaymentWeek() {
        this.financialLockUpService.validationPaymentWeek();
    }

    /**
     * 每隔23分钟计算其他套餐产品的剩余额度
     */
    @Scheduled(cron = "0 0/23 * * * ?")
    public void validationPaymentOther() {
        this.financialLockUpService.validationPaymentOther();
    }


    /**
     * 每天0:30执行计算抢购活动结束的lockUpAmount
     */
    @Scheduled(cron = "0 40 14 * * ?")
    public void calculateLockUpAmount() {
        this.financialLockUpService.saveLockUpAmount();
    }

}
