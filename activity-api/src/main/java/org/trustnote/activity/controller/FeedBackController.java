package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.FeedBack;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.FeedBackService;
import org.trustnote.activity.stereotype.Frequency;
import org.trustnote.activity.utils.CosUtil;
import org.trustnote.activity.utils.ExcelFeedBackUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Frequency(name = "feedback", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/feedback")
public class FeedBackController {
    private static final Logger logger = LogManager.getLogger(FeedBackController.class);

    @Value("${TENCENT_URL}")
    private String tencentUrl;
    @Value("${FEED_BACK}")
    private String key;
    @Resource
    private FeedBackService feedBackService;
    @Resource
    private CosUtil cosUtil;
    @Resource
    private ExcelFeedBackUtils excelFeedBackUtils;

    /**
     * 问题反馈
     *
     * @param json
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insertFeedBack(@RequestParam(value = "json") final String json, final HttpServletRequest request,
                                 @RequestParam(value = "vcode") final String vcode, final HttpServletResponse response, final HttpSession session) {
        FeedBackController.logger.info("paramers: {}", json);
        final Result result = new Result();
        final String okStatus = "200";
        if (StringUtils.isBlank(json) || StringUtils.isBlank(vcode)) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.getMsg());
            return result.getString(result);
        }
        final String icode = (String) session.getAttribute("icode");
        FeedBackController.logger.info("paramers: {}" + icode);
        if (!vcode.equals(icode)) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.getMsg());
            return result.getString(result);
        }
        try {
            final FeedBack feedBack = JSON.parseObject(json, FeedBack.class);
            final Result uploadResult = this.cosUtil.uploadFile(request, this.key, true, null, null, 5120L * 1024L);
            if (okStatus.equals(uploadResult.getCode())) {
                feedBack.setScreenshots(uploadResult.getEntity().toString());
                feedBack.setCrtTime(LocalDateTime.now());
                result.setEntity(this.feedBackService.insert(feedBack));
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            }else {
                result.setCode(uploadResult.getCode());
                result.setMsg(uploadResult.getMsg());
            }
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(FeedBackController.logger, e, response, result);
        }
        return result.getString(result);
    }


    /**
     * 删除问题反馈
     *
     * @param id
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteFeedBack(@RequestParam(value = "id") final int id,
                                 final HttpServletResponse response) {
        FeedBackController.logger.info("paramers: {}", id);
        int deleteStatus = 0;
        final Result result = new Result();
        try {
            final FeedBack feedBackOrigin = this.feedBackService.selectDetail(id);
            deleteStatus = this.feedBackService.delete(id);
            if (deleteStatus > 0) {
                this.cosUtil.deleteFile(this.key, feedBackOrigin.getScreenshots());
            }
            result.setEntity(this.feedBackService.delete(id));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(FeedBackController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 查询问题反馈
     *
     * @param page
     * @param length
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryFeedBack(@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                                @RequestParam(value = "length", required = false, defaultValue = "20") final int length,
                                final HttpServletResponse response) {
        FeedBackController.logger.info("paramers: {} {}", page, length);
        final Result result = new Result();
        if (page < 1) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        if (length < 1) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            result.setEntity(this.feedBackService.select(page, length));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(FeedBackController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 查看详情
     *
     * @param
     * @param
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryDetail(@RequestParam final int id, final HttpServletResponse response) {
        FeedBackController.logger.info("paramers: {}", id);
        final Result result = new Result();
        try {
            final FeedBack feedBack = this.feedBackService.selectDetail(id);
            if (feedBack != null && StringUtils.isNotBlank(feedBack.getScreenshots())) {
                feedBack.setScreenshots(this.tencentUrl + this.key + feedBack.getScreenshots());
            }
            result.setEntity(feedBack);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(FeedBackController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 条件查询
     *
     * @param
     * @param
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryBySelect", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryBySelect(@RequestParam final String email, final HttpServletResponse response) {
        FeedBackController.logger.info("paramers: {}", email);
        final Result result = new Result();
        try {
            result.setEntity(this.feedBackService.listBySelect(email));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(FeedBackController.logger, e, response, result);
        }
        return result.getString(result);
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity exportExcel(final HttpServletRequest request, final HttpServletResponse response) {
        String excel = "";
        final String fileName = "问题反馈.xls";
        try {
            final List<FeedBack> list = this.feedBackService.listAll();
            final List<String> header = new ArrayList<>();
            header.add("邮箱");
            header.add("问题描述");
            header.add("提交时间");
            header.add("截图");
            excel = this.excelFeedBackUtils.exportExcel(fileName, header, list, 1, request, response);
        } catch (final Exception e) {
            FeedBackController.logger.error("exception {}", e);
        }
        return new ResponseEntity(excel, HttpStatus.OK);
    }


}
