package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.ActivityService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
//@Frequency(name = "activity", limit = 300, time = 60)
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
    public String insertActivity(@RequestParam(value = "activity") final String activity, final HttpServletResponse response) {
        ActivityController.logger.info("parameters: {}", activity);
        final Result result = new Result();
        if (StringUtils.isBlank(activity)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        final Activity act = JSON.parseObject(activity, Activity.class);
        try {
            final int saveResult = this.activityService.insert(act);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(saveResult);
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
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
    public String updateActivity(@RequestParam(value = "activity") final String activity, final HttpServletResponse response) {
        ActivityController.logger.info("parameters: {}", activity);
        final Result result = new Result();
        if (StringUtils.isBlank(activity)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        final Activity act = JSON.parseObject(activity, Activity.class);
        try {
            final int saveResult = this.activityService.update(act);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(saveResult);
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
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
    public String queryByDay(final HttpServletResponse response, final int page, final int length) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.activityService.selectAll(page, length));
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
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
    public String queryBySelective(final HttpServletResponse response
            , final String time
            , final String label, final int page, final int length, final String condition) {
        ActivityController.logger.info("parameters: {} label{} condition{}", time, label, condition);
        final Result result = new Result();
        LocalDateTime ldt=null;
        try {
            if(!"".equals(time)){
                final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ldt = LocalDateTime.parse(time,df);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.activityService.getBySelective(ldt, label, page, length, condition));
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
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
    public String queryOder(final HttpServletResponse response, @RequestParam(value = "type") final int type) {
        final Result result = new Result();
        try {
            result.setEntity(this.activityService.getByOrder(type));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
        }
    }

    /**
     * 活动开闭
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOne", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryOne(final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.activityService.getNewest());
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
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
    public String selectOne(final HttpServletResponse response, final Integer id) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.activityService.getOne(id));
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
        }
    }


    /**
     * 查询活动
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryActivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryActivity(final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.activityService.queryActivity());
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
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
    public String queryENLink(final HttpServletResponse response, @RequestParam(value = "type") final int type) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.activityService.queryLink(type));
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(ActivityController.logger, e, response, result);
        }
    }


}
