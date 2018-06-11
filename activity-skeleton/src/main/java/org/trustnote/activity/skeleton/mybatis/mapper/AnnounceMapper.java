package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.AnnounceExample;
import org.trustnote.activity.common.pojo.Announce;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;
import java.util.Map;


public interface AnnounceMapper {
    long countByExample(AnnounceExample example);

    int deleteByExample(AnnounceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Announce record);

    int insertSelective(Announce record);

    List<Announce> selectByExample(AnnounceExample example);

    List<Announce> selectByExampleAndPage(AnnounceExample example, Page<Announce> page);

    Announce selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Announce record, @Param("example") AnnounceExample example);

    int updateByExample(@Param("record") Announce record, @Param("example") AnnounceExample example);

    int updateByPrimaryKeySelective(Announce record);

    int updateByPrimaryKey(Announce record);

    int changeViewed(Map<String, Object> map);
}