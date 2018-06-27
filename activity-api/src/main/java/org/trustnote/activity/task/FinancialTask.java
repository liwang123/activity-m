package org.trustnote.activity.task;

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

    @Autowired
    private FinancialLockUpService financialLockUpService;

    /**
     * 每隔5分钟计算剩余额度、已抢购额度、已锁额度
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void validationPaymentWeek() {
        this.financialLockUpService.validationPayment();
    }

    /**
     * 每隔１小时执行计算收益
     */
    @Scheduled(cron = "* * 1 * * ?")
    public void calculateLockUpAmount() {
        this.financialLockUpService.saveInComeAmount();
    }

}
