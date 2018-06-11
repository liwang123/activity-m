package org.trustnote.activity.skeleton.mybatis.mapper;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.ActivityExample;
import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.InviteRecord;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> selectByTime(@Param("time") LocalDateTime time);

    //查询最新活动人数
    List<Activity> selectByDEscTime(@Param("time") LocalDateTime time);
}