package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.Questionnaire;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuxl 18-1-29
 * @since v0.3
 */
public interface QuestionnaireService {
    Questionnaire insert(Questionnaire questionnaire);

    Questionnaire update(Questionnaire questionnaire);

    Questionnaire selectBySelective(int id,String address);

    Questionnaire selectByCode(String code);

    Page selectAll(int page,int length);

    List<Questionnaire> selectAll(int type);


}
