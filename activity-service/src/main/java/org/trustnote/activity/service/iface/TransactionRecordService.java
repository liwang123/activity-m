package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.InvitationApi;
import org.trustnote.activity.common.pojo.TransactionRecord;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
public interface TransactionRecordService {
    Page<TransactionRecord> queryTransactionRecord(String inviteTrustnoteAddress, String condition, String label,int index,int length) throws Exception;
    String saveTransactionRecord(InvitationApi invitationApi) throws Exception;
    List<TransactionRecord> queryAllTransactionRecord(Page<TransactionRecord> page) throws Exception;
    List<Map<String,String>> exportTransactionRecord() throws Exception;
}
