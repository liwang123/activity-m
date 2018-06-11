package org.trustnote.activity.skeleton.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.trustnote.activity.common.example.QuestionnaireExample;
import org.trustnote.activity.common.pojo.Questionnaire;

public interface QuestionnaireMapper {
    long countByExample(QuestionnaireExample example);

    int deleteByExample(QuestionnaireExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Questionnaire record);

    int insertSelective(Questionnaire record);

    List<Questionnaire> selectByExampleWithBLOBs(QuestionnaireExample example);

    List<Questionnaire> selectByExample(QuestionnaireExample example);

    Questionnaire selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Questionnaire record, @Param("example") QuestionnaireExample example);

    int updateByExampleWithBLOBs(@Param("record") Questionnaire record, @Param("example") QuestionnaireExample example);

    int updateByExample(@Param("record") Questionnaire record, @Param("example") QuestionnaireExample example);

    int updateByPrimaryKeySelective(Questionnaire record);

    int updateByPrimaryKeyWithBLOBs(Questionnaire record);

    int updateByPrimaryKey(Questionnaire record);
    List<Questionnaire> selectByPage(@Param("offset") int offset, @Param("length") int length);

    @Select("SELECT COUNT(*) from questionnaire where job is not null")
    int count();
}