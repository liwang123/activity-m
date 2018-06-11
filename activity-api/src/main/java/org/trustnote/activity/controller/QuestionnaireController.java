package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Questionnaire;
import org.trustnote.activity.common.utils.ExcelQuestUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.CoinService;
import org.trustnote.activity.service.iface.QuestionnaireService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "question", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/question")
public class QuestionnaireController {

    private static final Logger logger = LogManager.getLogger(QuestionnaireController.class);

    @Resource
    private QuestionnaireService questionnaireService;

    @Resource
    private CoinService coinService;


    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insert(@RequestParam(value = "address") final String address, @RequestParam(value = "scode") final String scode
            , @RequestParam(value = "balance") final String balance, @RequestParam(value = "type") final int type, final HttpServletResponse response, final HttpSession session) {
        QuestionnaireController.logger.info("address: {} scode: {}", address, scode);
        final Result result = new Result();
        if(StringUtils.isBlank(address)||StringUtils.isBlank(balance)||StringUtils.isBlank(scode)){
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.getMsg());
            return result.getString(result);
        }
        final String icode = (String) session.getAttribute("icode");
        QuestionnaireController.logger.info("paramers: {}", icode);
        if(!scode.equals(icode)){
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.getMsg());
            return result.getString(result);
        }
        try {
            final Questionnaire questionnaire1 = this.questionnaireService.selectBySelective(0, address);
            if(questionnaire1!=null){
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(questionnaire1.getCode());
                return result.getString(result);
            }
            this.coinService.getbalanceAll(address);
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setAddress(address);
            questionnaire.setBalance(balance);
            questionnaire.setType(type);
            questionnaire = this.questionnaireService.insert(questionnaire);
            if(questionnaire==null){return null;}
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(questionnaire.getCode());
            return result.getString(result);
        } catch (final Throwable throwable) {
            QuestionnaireController.logger.error("exception: {}", throwable);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(throwable.getMessage());
            return result.getString(result);
        }

    }


    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update(@RequestParam(value = "json") final String json, @RequestParam(value = "scode") final String scode,
                         final HttpServletResponse response, final HttpSession session) {
        QuestionnaireController.logger.info("json: {} uuid: {}", json, scode);
        final Result result = new Result();
        if (StringUtils.isBlank(scode)||StringUtils.isBlank(json)){
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.getMsg());
            return result.getString(result);
        }
        try {
            final Questionnaire quest = this.questionnaireService.selectByCode(scode);
            if (quest == null) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.getMsg());
                return result.getString(result);
            }
            final Questionnaire questionnaire = JSON.parseObject(json, Questionnaire.class);
            questionnaire.setCode(quest.getCode());
            questionnaire.setAddress(quest.getAddress());
            questionnaire.setId(quest.getId());
            questionnaire.setSubTime(LocalDateTime.now());
            this.questionnaireService.update(questionnaire);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            return result.getString(result);
        } catch (final Exception e) {
            QuestionnaireController.logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/queryDetail",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryDetail(@RequestParam(value = "id", defaultValue = "0") final int id, @RequestParam(value = "address", defaultValue = "null") final String address,
                              final HttpServletResponse response) {
        QuestionnaireController.logger.info("id: {} address: {}", id, address);
        final Result result = new Result();
        try {
            final Questionnaire questionnaire = this.questionnaireService.selectBySelective(id, address);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(questionnaire);
            return result.getString(result);
        } catch (final Exception e) {
            return universalExceptionReturn(QuestionnaireController.logger, e, response, result);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryAll(final HttpServletResponse response, final int page, final int length) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.questionnaireService.selectAll(page, length));
            return result.getString(result);
        } catch (final Exception e) {
            return universalExceptionReturn(QuestionnaireController.logger, e, response, result);
        }
    }

    @RequestMapping(value = "/export", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity exportExcel(final HttpServletResponse response, @RequestParam(value = "type") final int type) {
        String excel = "";
        final String fileName = "市场调查.xls";
        try {
            final List<Questionnaire> list = this.questionnaireService.selectAll(type);
            final List<String> header = new ArrayList<>();
            header.add("地址");
            header.add("持有数量");
            header.add("职业");
            header.add("学历");
            header.add("是否立即上市");
            header.add("策略");
            header.add("首发数量");
            header.add("平台");
            header.add("提交时间");
            header.add("建议");
            excel = ExcelQuestUtils.exportExcel(fileName, header, list, 1, response);
        } catch (final Exception e) {
            QuestionnaireController.logger.error("exception {}", e);
        }
        return new ResponseEntity(excel, HttpStatus.OK);
    }


}
