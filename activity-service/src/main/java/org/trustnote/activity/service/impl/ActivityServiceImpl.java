package org.trustnote.activity.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.ActivityExample;
import org.trustnote.activity.common.pojo.*;
import org.trustnote.activity.common.utils.Utils;
import org.trustnote.activity.service.iface.ActivityService;
import org.trustnote.activity.skeleton.mybatis.mapper.ActivityMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.InviteRecordMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.TransactionRecordMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    private static final Logger logger = LogManager.getLogger(ActivityServiceImpl.class);

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private InviteRecordMapper inviteRecordMapper;

    @Resource
    private TransactionRecordMapper transactionRecordMapper;


    @Override
    public int insert(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public int update(Activity activity) {
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public Page selectAll(int index, int length) {
        ActivityExample example = new ActivityExample();
        example.setOrderByClause("id");
        List<Activity> list = activityMapper.selectByExample(example);
        List newList = new ArrayList<Activity>();
        List<Activity> subList = list.subList(length * (index - 1), ((length * index) > list.size() ? list.size() : (length * index)));
        for (Activity activity : subList
                ) {
            newList.add(activity);
        }
        Page page = new Page<>();
        page.setResult(newList);
        page.setTotalCount(list.size());
        logger.info("activity method selectAll SUCCESS");
        return page;
    }

    @Override
    public List<Activity> selectByTime(LocalDateTime time) {
        return activityMapper.selectByTime(time);
    }

    @Override
    public Page getBySelective(LocalDateTime time, String label, int index, int length, String condition) {
        Page page = new Page<>();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (!"TxgF9gEHHyKLxSdF".equals(label) && StringUtils.isNotBlank(label)) {
            ActivityExample e = new ActivityExample();
            e.or().andLabelEqualTo(label);
            List<Activity> list = activityMapper.selectByExample(e);
            if (list.size() == 0) {
                return null;
            }
            startTime = list.get(0).getStarttime();
            endTime = list.get(0).getEndtime();
        }
        int newNum = 0;
        int invitedNum = 0;
        //时间查询
        if (null != time) {
            List<InviteRecord> ls = inviteRecordMapper.selectByTime(time);
            List<TransactionRecord> lsTrans = transactionRecordMapper.selectByTransactionRecordTime(time);
            logger.info("ls {} lsTRans{}", ls, lsTrans);
            logger.info("startTime{} endTime{}", startTime, endTime);
            if (!"TxgF9gEHHyKLxSdF".equals(label) && StringUtils.isNotBlank(label)) {
                if (ls.size() != 0) {
                    for (InviteRecord inviteRecord :
                            ls) {
                        if (inviteRecord.getCrtTime().isBefore(endTime) && inviteRecord.getCrtTime().isAfter(startTime)) {
                            logger.info("inviteRecord{}", inviteRecord);
                            newNum++;
                        }
                    }
                }
                if (lsTrans.size() != 0) {
                    for (TransactionRecord transactionRecord :
                            lsTrans) {
                        LocalDateTime localDateTime = transactionRecord.getSubmitTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        if (localDateTime.isBefore(endTime) && localDateTime.isAfter(startTime)) {
                            invitedNum++;
                        }
                    }
                }
            } else {
                newNum = ls.size();
                invitedNum = lsTrans.size();
            }
            page.setTotalCount(newNum);
            page.setInviteeNum(invitedNum);
            return page;
        }
        if (condition.length() != 8 && condition.length() != 11 && condition.length() != 32 && condition.length() != 0 && Utils.checkEmail(condition) == false) {
            return null;
        }
        boolean flag = false;
        if (condition.contains("@")) {
            if (Utils.checkEmail(condition)) {
                flag = true;
            }
        }
        logger.info("startTime{} endTime{} condition{} lable{} flag{}", startTime, endTime, condition, label, flag);
        List<InviteRecord> inviteRecordList = inviteRecordMapper.selectByselective(startTime, endTime, condition, condition.length(), label, (index - 1) * length, length, flag, 5);
        List<InviteRecord> list = inviteRecordMapper.selectByselective(startTime, endTime, condition, condition.length(), label, 0, 10000000, flag, 5);
        logger.info("activity getBySelective SUCCESS");
        page.setResult(inviteRecordList);
        page.setTotalCount(list.size());
        return page;
    }

    @Override
    public List<InviteRecord> getByOrder(int type) {
        LocalDateTime now = LocalDateTime.now();
        List<Activity> activityList = this.selectByTime(now);
        if (activityList.size() == 0) {
            activityList = activityMapper.selectByDEscTime(now);
        }
        List<InviteRecord> inviteRecordList = null;
        if (activityList.size() != 0) {
            logger.info("The current time is not in the activity");
            Activity activity = activityList.get(0);
            inviteRecordList = inviteRecordMapper.selectByOrder(activity.getStarttime(), activity.getEndtime(), type);
            return inviteRecordList;
        }
        return inviteRecordList;
    }

    @Override
    public String getNewest() {
        LocalDateTime now = LocalDateTime.now();
        List<Activity> activityList = activityMapper.selectByTime(now);
        if (activityList.size() == 0) {
            logger.info("The current time is not in the activity");
            return "off";
        }
        return "on";
    }

    @Override
    public Activity getOne(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Activity> getByDEscTime(LocalDateTime time) {
        return activityMapper.selectByDEscTime(time);
    }

    @Override
    public Activity queryActivity() {
        LocalDateTime now = LocalDateTime.now();
        List<Activity> activityList = activityMapper.selectByTime(now);
        if (activityList.size() != 0) {
            return activityList.get(0);
        }
        logger.info("The current time is not in the activity");
        return null;
    }

    @Override
    public String queryLink(int type) {
        LocalDateTime now = LocalDateTime.now();
        List<Activity> activityList = activityMapper.selectByTime(now);
        if (activityList.size() == 0) {
            List<Activity> activities = activityMapper.selectByDEscTime(now);
            if (type == 1) {
                return activities.get(0).getTitle();
            }
            return activities.get(0).getEnlink();
        }
        if (type == 1) {
            return activityList.get(0).getTitle();
        }
        return activityList.get(0).getEnlink();
    }

    @Override
    public int queryByName(String name) {
        ActivityExample e = new ActivityExample();
        e.or().andLabelEqualTo(name);
        List<Activity> list = activityMapper.selectByExample(e);
        if (list.size() == 0) {
            return 0;
        }
        return 1;
    }
}
