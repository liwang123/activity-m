package org.trustnote.activity.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.QuestionnaireExample;
import org.trustnote.activity.common.pojo.Questionnaire;
import org.trustnote.activity.service.iface.QuestionnaireService;
import org.trustnote.activity.skeleton.mybatis.mapper.QuestionnaireMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private static final Logger logger = LogManager.getLogger(QuestionnaireServiceImpl.class);

    @Resource
    private QuestionnaireMapper questionnaireMapper;
    @Override
    public Questionnaire insert(Questionnaire questionnaire) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        questionnaire.setCode(uuid);
        int i = questionnaireMapper.insertSelective(questionnaire);
        if(i==0){ return null; }
        return questionnaire;
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) {
        int i = questionnaireMapper.updateByPrimaryKeySelective(questionnaire);
        if(i==0){return null;}
        return questionnaire;
    }

    @Override
    public Questionnaire selectBySelective(int id,String address) {
        if(id!=0){
            return questionnaireMapper.selectByPrimaryKey(id);
        }
        QuestionnaireExample e = new QuestionnaireExample();
        e.or().andAddressEqualTo(address);
        List<Questionnaire> list = questionnaireMapper.selectByExample(e);
        if(list.size()!=0) {
            logger.info("selectBySelective{}", list.get(0).toString());
            return list.get(0);
        }
        return null;
    }

    @Override
    public Questionnaire selectByCode(String code) {
        QuestionnaireExample e = new QuestionnaireExample();
        e.or().andCodeEqualTo(code);
        List<Questionnaire> list = questionnaireMapper.selectByExample(e);
        if(list.size()!=0){return list.get(0);}
        return null;
    }

    @Override
    public Page selectAll(int page, int length) {
        Page<Questionnaire> pg = new Page<Questionnaire>();
        List<Questionnaire> list = questionnaireMapper.selectByPage((page - 1) * length, length);
        pg.setResult(list);
        pg.setTotalCount(questionnaireMapper.count());
        return pg;
    }

    @Override
    public List<Questionnaire> selectAll(int type) {
        QuestionnaireExample e = new QuestionnaireExample();
        e.setOrderByClause("id");
        e.or().andJobIsNotNull();
        e.or().andTypeEqualTo(type);
        List<Questionnaire> list = questionnaireMapper.selectByExample(e);
        return list;
    }
}
