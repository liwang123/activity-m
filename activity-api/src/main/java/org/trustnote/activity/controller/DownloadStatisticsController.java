package org.trustnote.activity.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.DownloadType;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.*;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.ChannelService;
import org.trustnote.activity.service.iface.DownloadChannelService;
import org.trustnote.activity.service.iface.DownloadDayService;
import org.trustnote.activity.service.iface.DownloadStatisticsService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Frequency(name = "download", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/download")
public class DownloadStatisticsController {
    private static final Logger logger = LogManager.getLogger(DownloadStatisticsController.class);
    @Resource
    private DownloadStatisticsService downloadStatisticsService;

    @Resource
    private DownloadDayService downloadDayService;

    @Resource
    private ChannelService channelService;

    @Resource
    private DownloadChannelService downloadChannelService;

    /**
     * 统计下载总计
     * @param type
     * @param code
     * @param response
     */
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public void record(@RequestParam String type,String code,HttpServletResponse response) {
        logger.info("parameter {}", type);
        if (StringUtils.isNotBlank(type)){
            DownloadType downloadType = DownloadType.getItem(type);
            if (downloadType != null) {
                DownloadStatistics downloadStatistics = new DownloadStatistics();
                if (DownloadType.Android == downloadType) downloadStatistics.setAndroidSum(1);
                else if (DownloadType.IOS == downloadType) downloadStatistics.setIosSum(1);
                else if (DownloadType.Windows == downloadType) downloadStatistics.setWindowsSum(1);
                else if (DownloadType.Mac == downloadType) downloadStatistics.setMacSum(1);
                else if (DownloadType.Linux == downloadType) downloadStatistics.setLinuxSum(1);
                else if (DownloadType.Github == downloadType) downloadStatistics.setGithubSum(1);

                try {
                    downloadStatisticsService.recordDownload(downloadStatistics);
                    DownloadDay downloadDay = new DownloadDay();
                    LocalDate time = LocalDate.now();
                    downloadDay.setType(type);
                    downloadDay.setDownloadTime(time);
                    if(downloadDayService.queryDownload(type,time)==0){
                        downloadDay.setSum(1);
                        downloadDayService.insertDownloadDay(downloadDay);
                    }else {
                        downloadDayService.updateDownloadDay(downloadDay);
                    }
                    //渠道下载
                    if(StringUtils.isNotEmpty(code)&&StringUtils.isNotBlank(code)){
                        Channel channel = channelService.selectByCode(code);
                        if (channel==null){ logger.info("code "+code+"NOT EXIST");return;}
                        DownloadChannel downloadChannel = new DownloadChannel();
                        downloadChannel.setDownloadTime(time);
                        downloadChannel.setType(type);
                        downloadChannel.setChannelId(channel.getId());
                        if(downloadChannelService.queryDownload(type,time,channel.getId())==0){
                            downloadChannel.setSum(1);
                            downloadChannelService.insert(downloadChannel);
                        }else {
                            downloadChannelService.update(downloadChannel);
                        }

                    }

                    logger.info("record "+downloadType.getValue()+" success.");
                } catch (Exception e) {
                    logger.error("exception: {}", e);
                }
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * 查询下载
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryDownloadStatistics(HttpServletResponse response) {
        Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(downloadStatisticsService.queryDownloadStatistics());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 按天查询
     * @param response
     * @param page
     * @param length
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryByDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryByDay(HttpServletResponse response,int page,int length) {
        Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(downloadDayService.queryByDay(page,length));
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 渠道查询
     * @param response
     * @param page
     * @param length
     * @param channelId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryByChannel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryByChannel(HttpServletResponse response,int page,int length,@RequestParam(value="channelId",defaultValue = "0")int channelId
                                                                ,@RequestParam(value = "channelName",defaultValue ="null")String channelName) {
        Result result = new Result();
        logger.info("paramets{} channelName{}", channelId, channelName);
        try {
            Page pg = downloadChannelService.queryByChannel(page, length, channelId, channelName);
            if(pg!=null) {
                if (pg.getTotalCount() == -1) {
                    logger.info("channel is not exist");
                    result.setCode(ResultEnum.MISSION_FAIL.getCode());
                    result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                    result.setEntity("channel is not exist");
                    return result.getString(result);
                }
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(pg);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 渠道查询总计
     * @param response
     * @param channelId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryByChannelTotal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryByChannelTotal(HttpServletResponse response,@RequestParam(value="channelId",defaultValue = "0")int channelId
            ,@RequestParam(value = "channelName",defaultValue = "null")String channelName) {
        logger.info("paramets{} channelName{}", channelId, channelName);
        Result result = new Result();
        try {
            DayPoJo dayPoJo = downloadChannelService.queryByChannelTotal(channelId, channelName);
            if(dayPoJo.getSum()==-1){
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                result.setEntity("channel is not exist");
                logger.info("channel is not exist");
                return result.getString(result);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(dayPoJo);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

}
