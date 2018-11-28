package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.GenerateAddressExample;
import org.trustnote.activity.common.pojo.GenerateAddress;

import java.util.List;

public interface GenerateAddressMapper {
    long countByExample(GenerateAddressExample example);

    int deleteByExample(GenerateAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateAddress record);

    int insertSelective(GenerateAddress record);

    List<GenerateAddress> selectByExample(GenerateAddressExample example);

    GenerateAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateAddress record, @Param("example") GenerateAddressExample example);

    int updateByExample(@Param("record") GenerateAddress record, @Param("example") GenerateAddressExample example);

    int updateByPrimaryKeySelective(GenerateAddress record);

    int updateByPrimaryKey(GenerateAddress record);
}