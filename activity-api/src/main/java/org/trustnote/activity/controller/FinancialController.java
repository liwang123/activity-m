package org.trustnote.activity.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Financial;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.FinancialService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "financial", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/financial")
public class FinancialController {
    private static final Logger logger = LogManager.getLogger(FinancialController.class);

    @Resource
    private FinancialService financialService;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getFinancial(@RequestParam final int id, final HttpServletResponse response) {
        FinancialController.logger.info("parameter {}", id);
        final Result result = new Result();
        try {
            final Financial financial = this.financialService.queryOneFinancial(id);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(financial);
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/home", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getFinancials(final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setEntity(this.financialService.queryFinancial());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     @ResponseBody
     @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     public String updateFinancial(@RequestParam final int id,
     @RequestParam final float rate,
     final HttpServletResponse response) {
     final Result result = new Result();
     try {
     result.setCode(ResultEnum.OK.getCode());
     result.setMsg(ResultEnum.OK.getMsg());
     result.setEntity(this.financialService.updateFinancial(id, rate));
     } catch (final Exception e) {
     return universalExceptionReturn(FinancialController.logger, e, response, result);
     }
     return result.getString(result);
     }*/
}
