package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.trustnote.activity.common.constant.Globa;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.Announce;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.ActivityService;
import org.trustnote.activity.service.iface.CoinService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Frequency(name = "activity", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
    private static final Logger logger = LogManager.getLogger(ActivityController.class);
    @Resource
    private ActivityService activityService;

    /**
     * 添加活动
     * @param activity
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insertActivity(@RequestParam(value = "activity") String activity, HttpServletResponse response) {
        logger.info("parameters: {}", activity);
        Result result = new Result();
        if (StringUtils.isBlank(activity)) return ResultUtil.universalBlankReturn(response, result);
           Activity act = JSON.parseObject(activity, Activity.class);
        try {
            int saveResult = activityService.insert(act);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(saveResult);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 修改活动
     * @param activity
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateActivity", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateActivity(@RequestParam(value = "activity") String activity, HttpServletResponse response) {
        logger.info("parameters: {}", activity);
        Result result = new Result();
        if (StringUtils.isBlank(activity)) return ResultUtil.universalBlankReturn(response, result);
        Activity act = JSON.parseObject(activity, Activity.class);
        try {
            int saveResult = activityService.update(act);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(saveResult);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 查询所有活动
     * @param response
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryByDay(HttpServletResponse response,int page,int length) {
        Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(activityService.selectAll(page,length));
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }


    /**
     * 查询活动下邀请记录
     * @param response
     * @param time
     * @param label
     * @param page
     * @param length
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryBySelective", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryBySelective(HttpServletResponse response
                                   ,String time
            ,String label, int page, int length,String condition) {
        logger.info("parameters: {} label{} condition{}", time, label, condition);
        Result result = new Result();
        LocalDateTime ldt=null;
        try {
            if(!"".equals(time)){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ldt = LocalDateTime.parse(time,df);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(activityService.getBySelective(ldt, label, page, length,condition));
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }


    /**
     * 1:国内2:海外排行榜
     * @param response
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryOder(HttpServletResponse response,@RequestParam(value = "type") int type) {
        Result result = new Result();
        try {
            result.setEntity(activityService.getByOrder(type));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 活动开闭
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOne", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryOne(HttpServletResponse response) {
        Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(activityService.getNewest());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 查询最新活动
     * @param response
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectOne", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String selectOne(HttpServletResponse response,Integer id) {
        Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(activityService.getOne(id));
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }


    /**
     * 查询活动
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryActivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryActivity(HttpServletResponse response) {
        Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(activityService.queryActivity());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**1:国内2:海外
     * 查询活动链接
     * @param response
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryLink", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryENLink(HttpServletResponse response,@RequestParam(value = "type") int type) {
        Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(activityService.queryLink(type));
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }


}
