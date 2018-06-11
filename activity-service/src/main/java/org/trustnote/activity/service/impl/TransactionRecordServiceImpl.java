package org.trustnote.activity.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.api.InvitationApi;
import org.trustnote.activity.common.example.ActivityExample;
import org.trustnote.activity.common.example.TransactionRecordExample;
import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.GiftSet;
import org.trustnote.activity.common.pojo.InviteRecord;
import org.trustnote.activity.common.pojo.TransactionRecord;
import org.trustnote.activity.common.utils.InviteCodeUtil;
import org.trustnote.activity.service.iface.ActivityService;
import org.trustnote.activity.service.iface.GiftSetService;
import org.trustnote.activity.service.iface.InviteRecordService;
import org.trustnote.activity.service.iface.TransactionRecordService;
import org.trustnote.activity.skeleton.mybatis.mapper.ActivityMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.TransactionRecordMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {
    private static final Logger logger = LogManager.getLogger(TransactionRecordServiceImpl.class);

    @Resource
    private TransactionRecordMapper transactionRecordMapper;
    @Resource
    private InviteRecordService inviteRecordService;
    @Resource
    private GiftSetService giftSetService;

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Page<TransactionRecord> queryTransactionRecord(String inviteTrustnoteAddress, String condition, String label,int index,int length) {
        LocalDateTime starttime=null;
        LocalDateTime endtime=null;
        if(!"TxgF9gEHHyKLxSdF".equals(label)){
            ActivityExample e = new ActivityExample();
            e.or().andLabelEqualTo(label);
            List<Activity> list = activityMapper.selectByExample(e);
            if(list.size()==0){return null;}
            starttime = list.get(0).getStarttime();
            endtime = list.get(0).getEndtime();}
        List<TransactionRecord> transactionRecords = transactionRecordMapper.queryTransactionByaddress(condition,label,inviteTrustnoteAddress, starttime, endtime,(index - 1) * length, length);
        List<TransactionRecord> list = transactionRecordMapper.queryTransactionByaddress(condition,label,inviteTrustnoteAddress, starttime, endtime,0, 1000000000);
        // List<TransactionRecord> transactionRecords = transactionRecordMapper.queryTransactionRecordPages(params, page);
        for (TransactionRecord transactionRecord : transactionRecords) {
            Instant instant = transactionRecord.getSubmitTime().toInstant();
            transactionRecord.setSubmitTimeShow(instant.atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
        }
        Page<TransactionRecord> page = new Page<TransactionRecord>();
        page.setResult(transactionRecords);
        page.setTotalCount(list.size());
        return page;
    }

    @Override
    public String saveTransactionRecord(InvitationApi invitationApi) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //save inviteRecord.
        InviteRecord inviteRecord = new InviteRecord();
        InviteRecord record=null;
        if (StringUtils.isNotBlank(invitationApi.getInviteCode())) {
            record = inviteRecordService.queryInviteRecordByInviteCode(invitationApi.getInviteCode());
            if(record==null)return "";
        }
        LocalDateTime now = LocalDateTime.now();
        BeanUtils.copyProperties(inviteRecord, invitationApi);
        inviteRecord.setCrtTime(now);
        //判断时间是否在活动范围内
        List<Activity> activityList = activityService.selectByTime(now);
        if(activityList.size()==0){
            logger.info("The current time is not in the activity");return null;}
        inviteRecord = this.saveInviteRecord(inviteRecord,activityList.get(0).getInviteeNum());
        if (null == inviteRecord) {
            return "";
        }
        //get inviteRecord id.
        int inviteRecordId = inviteRecord.getId();
        //Generate invitation code.
        String   inviteCode = InviteCodeUtil.toSerialCode(inviteRecordId);
        //invitation.
        if (StringUtils.isNotBlank(invitationApi.getInviteCode())) {
            InviteRecord inviterecordFrom = inviteRecordService.queryByInviteCode(invitationApi.getInviteCode(), activityList.get(0));
            if (null == inviterecordFrom){
                record.setInviteSeveral(0);
                record.setRewardTtn(0);
                record.setCrtTime(now);
                InviteRecord invite = inviteRecordService.saveInvite(record);
                logger.info("invite id{}", invite.getId());
                inviterecordFrom=invite;
            }
            //插入被邀请记录
            TransactionRecord transactionRecord = new TransactionRecord();
            BeanUtils.copyProperties(transactionRecord, invitationApi);
            transactionRecord.setHandselCount(activityList.get(0).getInviteeNum());
            transactionRecord.setSubmitTime(Timestamp.valueOf(df.format(new Date())));
            transactionRecord.setInviteTrustnoteAddress(inviterecordFrom.getTrustnoteAddress());
            transactionRecord.setInviteCode(inviteCode);
            int transactionRecordResult = transactionRecordMapper.insertSelective(transactionRecord);
            if (transactionRecordResult > 0) {
                //更新邀请人记录 + 邀请人MN + 邀请数
                if (inviterecordFrom.getRewardTtn() <= 4995) {
                    inviteRecordService.updateInviteTtnAndSeveral(inviterecordFrom.getId(), activityList.get(0).getInviterNum());
                }
            }
        }
        //更新邀请人邀请码.
        inviteRecordService.updateInviteCodeByPrimaryKey(inviteRecordId, inviteCode);
        logger.info("邀请码: {}", inviteCode);
        return inviteCode;
    }

    @Override
    public List<TransactionRecord> queryAllTransactionRecord(Page<TransactionRecord> page) throws Exception {
        List<TransactionRecord> transactionRecords = transactionRecordMapper.queryAll(page);
        for (TransactionRecord transactionRecord : transactionRecords) {
            Instant instant = transactionRecord.getSubmitTime().toInstant();
            transactionRecord.setSubmitTimeShow(instant.atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
        }
        return transactionRecords;
    }

    @Override
    public List<Map<String, String>> exportTransactionRecord() throws Exception {
        List<Map<String, String>> transactionRecords = transactionRecordMapper.exportTransactionRecord();
        return transactionRecords;
    }

    /**
     * 添加邀请记录
     * hasFirst true 无赠送
     * hasFirst false 有赠送
     * @param inviteRecord
     * @return
     * @throws Exception
     */
    private InviteRecord saveInviteRecord(InviteRecord inviteRecord,Integer num) throws Exception {
        inviteRecord.setRewardTtn(num);
        return inviteRecordService.saveInvite(inviteRecord);
    }
}
