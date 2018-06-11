package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.ActivityExample;
import org.trustnote.activity.common.example.InviteRecordExample;
import org.trustnote.activity.common.example.TransactionRecordExample;
import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.InviteRecord;
import org.trustnote.activity.common.pojo.TransactionRecord;
import org.trustnote.activity.common.utils.InviteCodeUtil;
import org.trustnote.activity.common.utils.Utils;
import org.trustnote.activity.service.iface.ActivityService;
import org.trustnote.activity.service.iface.InviteRecordService;
import org.trustnote.activity.skeleton.mybatis.mapper.ActivityMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.InviteRecordMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.TransactionRecordMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Service
public class InviteRecordServiceImpl implements InviteRecordService {
    @Resource
    private InviteRecordMapper inviteRecordMapper;

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private TransactionRecordMapper transactionRecordMapper;


    @Override
    public int saveInviteRecord(InviteRecord inviteRecord) throws Exception {
        return inviteRecordMapper.insertSelective(inviteRecord);
    }

    @Override
    public InviteRecord saveInvite(InviteRecord inviteRecord) throws Exception {
        int saveResult = this.saveInviteRecord(inviteRecord);
        if (saveResult == 0) return null;
        return inviteRecord;
    }

    @Override
    public int updateInviteTtnAndSeveral(Integer id, Integer rewardTtn) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("rewardTtn", rewardTtn);
        return inviteRecordMapper.updateRewardTtnAndSeveral(map);
    }

    @Override
    public List<InviteRecord> queryInviteRecord(Page<InviteRecord> page, String inviteCode) {
        InviteRecordExample example = new InviteRecordExample();
        if (StringUtils.isNotBlank(inviteCode)) {
            InviteRecordExample.Criteria criteria = example.createCriteria();
            criteria.andInviteCode(inviteCode);
        }
        return inviteRecordMapper.queryInviteRecordPages(page, example);
    }

    @Override
    public List<Map<String,String>> exportInviteRecord() {
        return inviteRecordMapper.exportInviteRecord();
    }

    @Override
    public List<InviteRecord> queryInviteRecordByMobile(String mobile) throws Exception {
        InviteRecordExample example = new InviteRecordExample();
        InviteRecordExample.Criteria criteria = example.createCriteria();
        criteria.andMobilePhoneEqualTo(mobile);
        List<InviteRecord> inviteRecords = inviteRecordMapper.selectByExample(example);
 //       List<InviteRecord> inviteRecords = inviteRecordMapper.selectByMobile(activity.getStarttime(), activity.getEndtime(), mobile, null);
        if (CollectionUtils.isEmpty(inviteRecords)) return null;
        return inviteRecords;
    }

    @Override
    public InviteRecord selectByPrimaryKey(int id) throws Exception {
        return inviteRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateInviteCodeByPrimaryKey(int id, String inviteCode) throws Exception {
        InviteRecord inviteRecord = new InviteRecord();
        inviteRecord.setId(id);
        inviteRecord.setInviteCode(inviteCode);
        return inviteRecordMapper.updateByPrimaryKeySelective(inviteRecord);
    }

    @Override
    public InviteRecord queryInviteRecordByInviteCode(String inviteCode) throws Exception {
        int id = InviteCodeUtil.codeToId(inviteCode);
        return inviteRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<InviteRecord> queryByMobile(String mobile) throws Exception {
        InviteRecordExample example = new InviteRecordExample();
        InviteRecordExample.Criteria criteria = example.createCriteria();
        criteria.andMobilePhoneEqualTo(mobile);
        List<InviteRecord> inviteRecords = inviteRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(inviteRecords)) return null;
        return inviteRecords;
    }

    @Override
    public InviteRecord queryByInviteCode(String inviteCode, Activity activity) {
        List<InviteRecord> recordList = inviteRecordMapper.selectByInviteCode(activity.getStarttime(), activity.getEndtime(), inviteCode);
        if(recordList.size()==0){return null;}
        return recordList.get(0);
    }

    @Override
    public List<InviteRecord> queryByCopyMobile(String mobile) throws Exception {
        InviteRecordExample example = new InviteRecordExample();
        InviteRecordExample.Criteria criteria = example.createCriteria();
        criteria.andMobilePhoneEqualTo(mobile);
        List<InviteRecord> inviteRecords = inviteRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(inviteRecords)) return null;
        LocalDateTime now = LocalDateTime.now();
        List<Activity> activityList = activityService.selectByTime(now);
        if(activityList.size()==0){
                activityList = activityService.getByDEscTime(now);
                List<InviteRecord> records = inviteRecordMapper.selectByMobile(activityList.get(0).getStarttime(), activityList.get(0).getEndtime(), mobile, null);
                if(records.size()==0){
                    List<InviteRecord> inviteRecords1 = inviteRecordMapper.selectByExample(example);
                    ArrayList<InviteRecord> list = new ArrayList<>();
                    InviteRecord inviteRecord = inviteRecords1.get(0);
                    inviteRecord.setRewardTtn(0);
                    inviteRecord.setInviteSeveral(0);
                    list.add(inviteRecord);
                    records=list;
                }
                return records;
        }
        List<InviteRecord> records = inviteRecordMapper.selectByMobile(activityList.get(0).getStarttime(), activityList.get(0).getEndtime(), mobile, null);
        if(records.size()==0) {
            InviteRecord inviteRecord = new InviteRecord();
            inviteRecord.setCrtTime(LocalDateTime.now());
            inviteRecord.setInviteCode(inviteRecords.get(0).getInviteCode());
            inviteRecord.setMobilePhone(mobile);
            inviteRecord.setTrustnoteAddress(inviteRecords.get(0).getTrustnoteAddress());
            inviteRecord.setInviteSeveral(0);
            inviteRecord.setRewardTtn(0);
            inviteRecordMapper.insert(inviteRecord);
        }
        return inviteRecords;
    }

    @Override
    public List<InviteRecord> exportInviteRecordBySelective(String label, String condition,int type) {
        LocalDateTime starttime=null;
        LocalDateTime endtime=null;
        if(!"TxgF9gEHHyKLxSdF".equals(label)){
            ActivityExample e = new ActivityExample();
            e.or().andLabelEqualTo(label);
            List<Activity> list = activityMapper.selectByExample(e);
            starttime = list.get(0).getStarttime();
            endtime = list.get(0).getEndtime();}
        if(condition.length()!=8&&condition.length()!=11&&condition.length()!=32&&condition.length()!=0&&Utils.checkEmail(condition)==false){
            return null;
        }
        boolean flag=false;
        if(condition.contains("@")){
            if(Utils.checkEmail(condition)){
                flag=true;
            }
        }
        //国内导出
        List<InviteRecord> inviteRecordList = inviteRecordMapper.selectByselective(starttime, endtime, condition,condition.length(),label,0,100000000,flag,type);
        if(inviteRecordList.size()==0){return null;}
        //国内排序导出
        if(type==2||type==4){
            List<InviteRecord> inviteRecordArrayList = new ArrayList<>();
            for (InviteRecord inviteRecord:
                 inviteRecordList) {
                inviteRecord.setId(-1);
                inviteRecordArrayList.add(inviteRecord);
                List<TransactionRecord> list = transactionRecordMapper.queryTransactionByaddress("",label,inviteRecord.getTrustnoteAddress(),starttime,endtime,0,1000000000);
                if(list.size()!=0){
                    for (TransactionRecord transactionRecord:
                         list) {
                        List<InviteRecord> list1 = inviteRecordMapper.selectByaddress(transactionRecord.getTrustnoteAddress(),starttime,endtime,label);
                        if(list1.size()!=0){
                            for (InviteRecord inviteRecord1:
                            list1) {
                                inviteRecordArrayList.add(inviteRecord1);
                            }
                        }
                    }
                }
            }
            return inviteRecordArrayList;
        }
        return inviteRecordList;
    }

    @Override
    public InviteRecord queryInviteRecordByTrustnoteAddress(String trustnoteAddress) throws Exception {
        InviteRecordExample example = new InviteRecordExample();
        InviteRecordExample.Criteria criteria = example.createCriteria();
        criteria.andTrustnoteAddressEqualTo(trustnoteAddress);
        List<InviteRecord> inviteRecords = inviteRecordMapper.selectByExample(example);
        //List<InviteRecord> inviteRecords = inviteRecordMapper.selectByMobile(activity.getStarttime(), activity.getEndtime(), null, trustnoteAddress);
        if (CollectionUtils.isEmpty(inviteRecords)) return null;
        return inviteRecords.get(0);
    }

}
