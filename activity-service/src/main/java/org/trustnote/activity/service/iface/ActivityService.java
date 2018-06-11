package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.Announce;
import org.trustnote.activity.common.pojo.InviteRecord;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuxl 18-1-29
 * @since v0.3
 */
public interface ActivityService {
    int insert(Activity activity);

    int update(Activity activity);

    Page selectAll(int page,int length);

    List<Activity> selectByTime(LocalDateTime time);

    Page getBySelective(LocalDateTime time,String label, int page, int length,String condition);

    List<InviteRecord> getByOrder(int type);

    String getNewest();

    Activity getOne(Integer id);
    //查询当前时间之前的活动
    List<Activity> getByDEscTime(LocalDateTime time);

    Activity queryActivity();
    //查询链接
    String queryLink(int type);

    //查询重复
    int queryByName(String name);


}
