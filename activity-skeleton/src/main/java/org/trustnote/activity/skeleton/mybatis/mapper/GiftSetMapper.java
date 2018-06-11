package org.trustnote.activity.skeleton.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.GiftSetExample;
import org.trustnote.activity.common.pojo.GiftSet;

public interface GiftSetMapper {
    long countByExample(GiftSetExample example);

    int deleteByExample(GiftSetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GiftSet record);

    int insertSelective(GiftSet record);

    List<GiftSet> selectByExample(GiftSetExample example);

    GiftSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GiftSet record, @Param("example") GiftSetExample example);

    int updateByExample(@Param("record") GiftSet record, @Param("example") GiftSetExample example);

    int updateByPrimaryKeySelective(GiftSet record);

    int updateByPrimaryKey(GiftSet record);
}