package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.InviteRecord;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
public interface InviteRecordService {
    int saveInviteRecord(InviteRecord inviteRecord) throws Exception;
    InviteRecord saveInvite(InviteRecord inviteRecord) throws Exception;
    InviteRecord queryInviteRecordByTrustnoteAddress(String trustnoteAddress) throws Exception;
    List<InviteRecord> queryInviteRecord(Page<InviteRecord> page, String inviteCode);
    List<Map<String,String>> exportInviteRecord();
    int updateInviteTtnAndSeveral(Integer id, Integer rewardTtn) throws Exception;
    /**
     * 根据手机号查询邀请记录
     * @param mobile
     * @return
     * @throws Exception
     */
    List<InviteRecord> queryInviteRecordByMobile(String mobile) throws Exception;
    InviteRecord selectByPrimaryKey(int id) throws Exception;
    int updateInviteCodeByPrimaryKey(int id, String inviteCode) throws Exception;
    InviteRecord queryInviteRecordByInviteCode(String inviteCode) throws Exception;
    //根据手机号查询邀请码(改)
    List<InviteRecord> queryByMobile(String mobile) throws Exception;

    //根据验证码和时间查询活动邀请者
    InviteRecord queryByInviteCode(String inviteCode,Activity activity);

    List<InviteRecord> queryByCopyMobile(String mobile) throws Exception;

    //导出邀请人
    List<InviteRecord> exportInviteRecordBySelective(String label,String condition,int type);

}
