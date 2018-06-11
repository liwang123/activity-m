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
import org.trustnote.activity.common.pojo.Channel;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.ChannelService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Frequency(name = "channel", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/channel")
public class ChannelController {
    private static final Logger logger = LogManager.getLogger(ChannelController.class);

    @Resource
    private ChannelService channelService;

    /**
     * 添加渠道
     * @param channel
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insertChannel(@RequestParam(value = "channel") String channel, HttpServletResponse response) {
        logger.info("parameters: {}", channel);
        Result result = new Result();
        if (StringUtils.isBlank(channel)) return ResultUtil.universalBlankReturn(response, result);
        Channel cha = JSON.parseObject(channel, Channel.class);
        try {
            int saveResult = channelService.insert(cha);
            result.setMsg(ResultEnum.OK.getMsg());
            result.setCode(ResultEnum.OK.getCode());
            result.setEntity(saveResult);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 更新渠道
     * @param channel
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateChannel(@RequestParam(value = "channel") String channel, HttpServletResponse response) {
        logger.info("parameters: {}", channel);
        Result result = new Result();
        if (StringUtils.isBlank(channel)) return ResultUtil.universalBlankReturn(response, result);
        Channel cha = JSON.parseObject(channel, Channel.class);
        try {
            int saveResult = channelService.update(cha);
            result.setMsg(ResultEnum.OK.getMsg());
            result.setCode(ResultEnum.OK.getCode());
            result.setEntity(saveResult);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 查询所有渠道
     * @param response
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryAll(HttpServletResponse response,int page,int length) {
        Result result = new Result();
        try {
            result.setEntity(channelService.selectAll(page,length));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 渠道下拉列表
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryAll(HttpServletResponse response) {
        Result result = new Result();
        try {
            result.setEntity(channelService.queryAll());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 查询官方渠道码
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOne", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryOne(HttpServletResponse response) {
        Result result = new Result();
        try {
            result.setEntity(channelService.queryOne());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 检查渠道名称相同
     * @param response
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryByName", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryByName(HttpServletResponse response,String name) {
        Result result = new Result();
        try {
            result.setEntity(channelService.queryByName(name));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }




}
