package org.trustnote.activity.skeleton.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.CheckAccountExample;
import org.trustnote.activity.common.pojo.CheckAccount;

public interface CheckAccountMapper {
    long countByExample(CheckAccountExample example);

    int deleteByExample(CheckAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CheckAccount record);

    int insertSelective(CheckAccount record);

    List<CheckAccount> selectByExample(CheckAccountExample example);

    CheckAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CheckAccount record, @Param("example") CheckAccountExample example);

    int updateByExample(@Param("record") CheckAccount record, @Param("example") CheckAccountExample example);

    int updateByPrimaryKeySelective(CheckAccount record);

    int updateByPrimaryKey(CheckAccount record);
}