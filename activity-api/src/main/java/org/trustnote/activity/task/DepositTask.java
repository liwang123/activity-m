package org.trustnote.activity.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.trustnote.activity.common.example.DepositLockExample;
import org.trustnote.activity.common.pojo.DepositLock;
import org.trustnote.activity.service.iface.DepositService;
import org.trustnote.activity.skeleton.mybatis.mapper.DepositLockMapper;

import java.util.List;

/**
 * @author zhuxl
 */
@Component
@EnableScheduling
public class DepositTask {
    @Autowired
    private DepositService depositService;

    @Autowired
    private DepositLockMapper depositLockMapper;

    @Value("${blockchainUrl}")
    private String blockchainUrl;


    /**
     * 处理押金锁仓
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void getExchangeRate() {
        final DepositLockExample depositLockExample = new DepositLockExample();
        depositLockExample.createCriteria()
                .andLockStatusEqualTo(1);
        final List<DepositLock> depositLockList =
                this.depositLockMapper.selectByExample(depositLockExample);
        this.depositService.queryPayed(depositLockList);
    }


}
