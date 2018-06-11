package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.FinancialExample;
import org.trustnote.activity.common.pojo.Financial;

import java.util.List;

/**
 * @author zhuxl
 */
public interface FinancialMapper {
    long countByExample(FinancialExample example);

    int deleteByExample(FinancialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Financial record);

    int insertSelective(Financial record);

    List<Financial> selectByExample(FinancialExample example);

    Financial selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Financial record, @Param("example") FinancialExample example);

    int updateByExample(@Param("record") Financial record, @Param("example") FinancialExample example);

    int updateByPrimaryKeySelective(Financial record);

    int updateByPrimaryKey(Financial record);
}