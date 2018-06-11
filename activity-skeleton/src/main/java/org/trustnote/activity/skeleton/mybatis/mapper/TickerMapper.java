package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.TickerExample;
import org.trustnote.activity.common.pojo.Ticker;

import java.util.List;

public interface TickerMapper {
    long countByExample(TickerExample example);

    int deleteByExample(TickerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Ticker record);

    int insertSelective(Ticker record);

    List<Ticker> selectByExample(TickerExample example);

    Ticker selectByPrimaryKey(Long id);

    Ticker selectByExampleReturnOne(TickerExample example);

    int updateByExampleSelective(@Param("record") Ticker record, @Param("example") TickerExample example);

    int updateByExample(@Param("record") Ticker record, @Param("example") TickerExample example);

    int updateByPrimaryKeySelective(Ticker record);

    int updateByPrimaryKey(Ticker record);
}