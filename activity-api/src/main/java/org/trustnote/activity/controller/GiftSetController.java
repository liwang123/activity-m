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
import org.trustnote.activity.common.pojo.GiftSet;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.GiftSetService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuxl 18-1-30
 * @since v0.3
 */
@Frequency(name = "gift", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/gift")
public class GiftSetController {
    private static final Logger logger = LogManager.getLogger(GiftSetController.class);
    @Resource
    private GiftSetService giftSetService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String save(@RequestBody String save, HttpServletResponse response) {
        logger.info("parameter {}", save);
        Result result = new Result();
        if (StringUtils.isBlank(save)) return ResultUtil.universalBlankReturn(response, result);
        try {
            GiftSet giftSet = JSON.parseObject(save, GiftSet.class);
            if (giftSet.getGiftType() == null) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The giftType is mandatory."));
                return result.getString(result);
            }
            if (giftSet.getTtn() == null) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The ttn is mandatory."));
                return result.getString(result);
            }
            int updateResult = giftSetService.saveGiftSet(giftSet);
            if (updateResult == 0) {
                logger.info("save fail reason {}", updateResult);
                result.setCode(ResultEnum.UNAUTHORIZD.getCode());
                result.setMsg(ResultEnum.UNAUTHORIZD.appendMsg("save fail."));
                return result.getString(result);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(1);
            return result.getString(result);
        } catch (JSONException e) {
            return ResultUtil.universalJSONExceptionReturn(logger, e, response, result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger, e, response, result);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryTTN(@RequestParam int type, HttpServletResponse response) {
        logger.info("parameter {}", type);
        Result result = new Result();
        try {
            GiftSet giftSet = giftSetService.queryGiftset(type);
            if (giftSet == null) {
                logger.info("query fail reason {}", giftSet);
                result.setCode(ResultEnum.UNAUTHORIZD.getCode());
                result.setMsg(ResultEnum.UNAUTHORIZD.appendMsg("query fail."));
                return result.getString(result);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(giftSet);
            return result.getString(result);
        }catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger, e, response, result);
        }
    }
}
