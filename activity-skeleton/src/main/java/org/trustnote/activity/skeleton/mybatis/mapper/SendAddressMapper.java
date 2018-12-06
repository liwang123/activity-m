package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.SendAddressExample;
import org.trustnote.activity.common.pojo.SendAddress;

import java.util.List;

public interface SendAddressMapper {
    long countByExample(SendAddressExample example);

    int deleteByExample(SendAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SendAddress record);

    int insertSelective(SendAddress record);

    List<SendAddress> selectByExample(SendAddressExample example);

    SendAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SendAddress record, @Param("example") SendAddressExample example);

    int updateByExample(@Param("record") SendAddress record, @Param("example") SendAddressExample example);

    int updateByPrimaryKeySelective(SendAddress record);

    int updateByPrimaryKey(SendAddress record);
}