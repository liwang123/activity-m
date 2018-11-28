package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.DepositLock;

import java.util.List;

/**
 * @author zhuxl 18-1-29
 * @since v0.3
 */
public interface DepositService {
    String insert(String address, int status);

    boolean querybalance(String address);

    void queryPayed(final List<DepositLock> depositLockList);
}
