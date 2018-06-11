package org.trustnote.activity.skeleton.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.ExchangeRateExample;
import org.trustnote.activity.common.pojo.ExchangeRate;

public interface ExchangeRateMapper {
    long countByExample(ExchangeRateExample example);

    int deleteByExample(ExchangeRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeRate record);

    int insertSelective(ExchangeRate record);

    List<ExchangeRate> selectByExample(ExchangeRateExample example);

    ExchangeRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExchangeRate record, @Param("example") ExchangeRateExample example);

    int updateByExample(@Param("record") ExchangeRate record, @Param("example") ExchangeRateExample example);

    int updateByPrimaryKeySelective(ExchangeRate record);

    int updateByPrimaryKey(ExchangeRate record);
}