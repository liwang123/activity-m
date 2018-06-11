package org.trustnote.activity.skeleton.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.IploggerExample;
import org.trustnote.activity.common.pojo.Iplogger;

public interface IploggerMapper {
    long countByExample(IploggerExample example);

    int deleteByExample(IploggerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Iplogger record);

    int insertSelective(Iplogger record);

    List<Iplogger> selectByExample(IploggerExample example);

    Iplogger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Iplogger record, @Param("example") IploggerExample example);

    int updateByExample(@Param("record") Iplogger record, @Param("example") IploggerExample example);

    int updateByPrimaryKeySelective(Iplogger record);

    int updateByPrimaryKey(Iplogger record);
}