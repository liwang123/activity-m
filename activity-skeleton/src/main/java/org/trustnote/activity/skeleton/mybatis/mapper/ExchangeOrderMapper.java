package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.ExchangeOrderExample;
import org.trustnote.activity.common.pojo.ExchangeOrder;

import java.util.List;

public interface ExchangeOrderMapper {
    long countByExample(ExchangeOrderExample example);

    int deleteByExample(ExchangeOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExchangeOrder record);

    int insertSelective(ExchangeOrder record);

    List<ExchangeOrder> selectByExample(ExchangeOrderExample example);

    ExchangeOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExchangeOrder record, @Param("example") ExchangeOrderExample example);

    int updateByExample(@Param("record") ExchangeOrder record, @Param("example") ExchangeOrderExample example);

    int updateByPrimaryKeySelective(ExchangeOrder record);

    int updateByPrimaryKey(ExchangeOrder record);

    List<ExchangeOrder> selectByPage(@Param("offset") int offset, @Param("length") int length, @Param("status") int status, @Param("condition") String condition);


    int countByOrder(@Param("status") int status, @Param("condition") String condition);
}