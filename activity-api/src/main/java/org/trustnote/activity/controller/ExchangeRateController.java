package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.ExchangeRate;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.ExchangeRateService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Frequency(name = "exchange", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/exchange")
public class ExchangeRateController {
    private static final Logger logger = LogManager.getLogger(ExchangeRateController.class);
    @Resource
    private ExchangeRateService exchangeRateService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String save(@RequestBody String save, HttpServletResponse response) {
        logger.info("parameter {}", save);
        Result result = new Result();
        if (StringUtils.isBlank(save)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            ExchangeRate exchangeRate = JSON.parseObject(save, ExchangeRate.class);
            if (exchangeRate.getExchangeType() == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The exchangeType is mandatory."));
                return result.getString(result);
            }
            if (exchangeRate.getRate() == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The rate is mandatory."));
                return result.getString(result);
            }
            int updateCount = exchangeRateService.saveExchangeRate(exchangeRate);
            if (updateCount == 0) {
                logger.warn("save fail reason {}", updateCount);
                result.setCode(ResultEnum.UNAUTHORIZD.getCode());
                result.setMsg(ResultEnum.UNAUTHORIZD.appendMsg("save fail."));
                return result.getString(result);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(1);
            return result.getString(result);
        }catch (JSONException e) {
            return ResultUtil.universalJSONExceptionReturn(logger, e, response, result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryRate(@RequestParam Byte type, HttpServletResponse response) {
        logger.info("parameter {}", type);
        Result result = new Result();
        try {
            ExchangeRate exchangeRate = exchangeRateService.queryExchangeRate(type);
            if (exchangeRate == null) {
                logger.warn("query fail reason {}", exchangeRate);
                result.setCode(ResultEnum.UNAUTHORIZD.getCode());
                result.setMsg(ResultEnum.UNAUTHORIZD.appendMsg("query fail."));
                return result.getString(result);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(exchangeRate);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/conversion", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String conversion(@RequestParam Byte type, @RequestParam BigDecimal purchase, @RequestParam Integer turn,HttpServletResponse response) {
        logger.info("parameter type: {} purchase: {} turn: {}", type, purchase, turn);
        Result result = new Result();

        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(exchangeRateService.conversion(type, purchase, turn));
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

}
