package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.FinancialBenefitsExample;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl
 */
public interface FinancialBenefitsMapper {
    long countByExample(FinancialBenefitsExample example);

    int deleteByExample(FinancialBenefitsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinancialBenefits record);

    int insertSelective(FinancialBenefits record);

    List<FinancialBenefits> selectByExampleAndPage(Page<FinancialBenefits> page, FinancialBenefitsExample example);

    List<FinancialBenefits> selectByExample(FinancialBenefitsExample example);

    FinancialBenefits selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FinancialBenefits record, @Param("example") FinancialBenefitsExample example);

    int updateByExample(@Param("record") FinancialBenefits record, @Param("example") FinancialBenefitsExample example);

    int updateByPrimaryKeySelective(FinancialBenefits record);

    int updateByPrimaryKey(FinancialBenefits record);

}