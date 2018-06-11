package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.MediaExample;
import org.trustnote.activity.common.pojo.Media;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

public interface MediaMapper {
    long countByExample(MediaExample example);

    int deleteByExample(MediaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Media record);

    int insertSelective(Media record);

    List<Media> selectByExample(MediaExample example, Page<Media> page);

    Media selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Media record, @Param("example") MediaExample example);

    int updateByExample(@Param("record") Media record, @Param("example") MediaExample example);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    List<Media> selectByPage(@Param("offset") int offset, @Param("length") int length, @Param("status") int status);

    int count(@Param("status") int status);

    int queryMaxQueue(@Param("status") int status);
}