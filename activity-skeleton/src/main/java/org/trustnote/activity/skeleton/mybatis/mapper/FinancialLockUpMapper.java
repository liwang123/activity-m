package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.FinancialLockUpExample;
import org.trustnote.activity.common.pojo.FinancialLockUp;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl
 */
public interface FinancialLockUpMapper {
    long countByExample(FinancialLockUpExample example);

    int deleteByExample(FinancialLockUpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinancialLockUp record);

    int insertSelective(FinancialLockUp record);

    List<FinancialLockUp> selectByExamplePage(Page<FinancialLockUp> page, FinancialLockUpExample example);

    List<FinancialLockUp> selectByExample(FinancialLockUpExample example);

    FinancialLockUp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FinancialLockUp record, @Param("example") FinancialLockUpExample example);

    int updateByExample(@Param("record") FinancialLockUp record, @Param("example") FinancialLockUpExample example);

    int updateByPrimaryKeySelective(FinancialLockUp record);

    int updateByPrimaryKey(FinancialLockUp record);
}