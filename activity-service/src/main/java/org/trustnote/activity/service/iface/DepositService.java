package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.dto.DepositLockDTO;
import org.trustnote.activity.common.pojo.DepositLock;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl 18-1-29
 * @since v0.3
 */
public interface DepositService {
    String insert(String address, int status, String publicKey);

    boolean querybalance(String address);

    void queryPayed(final List<DepositLock> depositLockList);

    Page<DepositLockDTO> queryAll(int index, int length);
}
