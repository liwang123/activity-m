package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.MediaActionEnum;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Media;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.MediaService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;
import org.trustnote.activity.utils.CosUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Frequency(name = "media", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/media")
public class MediaController {
    private static final Logger logger = LogManager.getLogger(MediaController.class);
    private final int MEDIA_COUNT = 5;

    @Value("${TENCENT_URL}")
    private String tencentUrl;
    @Value("${MEDIA_KEY}")
    private String key;
    @Resource
    private MediaService mediaService;
    @Resource
    private CosUtil cosUtil;

    /**
     * 添加媒体动态
     *
     * @param json
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insertMedia(@RequestParam(value = "json") final String json,
                              final HttpServletResponse response, final HttpServletRequest request, final HttpSession session) {
        MediaController.logger.info("paramers: {}", json);
        final Result result = new Result();
        final String okStatus = "200";
        if (StringUtils.isBlank(json)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final Media media = JSON.parseObject(json, Media.class);
            final Result validation = this.validateJson(media);
            if (validation != null) {
                return result.getString(validation);
            }
            final Result uploadResult = this.cosUtil.uploadFile(request, this.key, true, 385, 266, 200L * 1024L);
            if (okStatus.equals(uploadResult.getCode())) {
                media.setImageUrl(uploadResult.getEntity().toString());
                final LocalDateTime now = LocalDateTime.now();
                media.setCrtTime(now);
                media.setUptTime(now);
                result.setEntity(this.mediaService.insert(media));
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            }else {
                if (uploadResult.getCode() == null) {
                    result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
                    result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 请上传图片");
                } else {
                    result.setCode(uploadResult.getCode());
                    result.setMsg(uploadResult.getMsg());
                }
            }
        } catch (final Exception e) {
            return universalExceptionReturn(MediaController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 修改媒体动态
     *
     * @param json
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateMedia(@RequestParam(value = "json") final String json, final HttpServletRequest request,
                              final HttpServletResponse response) {
        MediaController.logger.info("paramers: {}", json);
        final Result result = new Result();
        final String okStatus = "200";
        final int updateStatus;
        if (StringUtils.isBlank(json)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final Media media = JSON.parseObject(json, Media.class);
            final Result validation = this.validateJson(media);
            if (validation != null) {
                return result.getString(validation);
            }
            final Media mediaOrigin = this.mediaService.selectDetail(media.getId());
            media.setUptTime(LocalDateTime.now());
            final Result uploadResult = this.cosUtil.uploadFile(request, this.key, true, 385, 266, 200L * 1024L);
            if (okStatus.equals(uploadResult.getCode())) {
                media.setImageUrl(uploadResult.getEntity().toString());
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                updateStatus = this.mediaService.update(media);
                if (updateStatus > 0) {
                    this.cosUtil.deleteFile(this.key, mediaOrigin.getImageUrl());
                }
                result.setEntity(updateStatus);
            }else {
                result.setCode(uploadResult.getCode());
                result.setMsg(uploadResult.getMsg());
            }
        } catch (final JSONException je) {
            return ResultUtil.universalJSONExceptionReturn(MediaController.logger, je, response, result);
        } catch (final Exception e) {
            return universalExceptionReturn(MediaController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 删除媒体动态
     *
     * @param id
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteMedia(@RequestParam(value = "id") final int id,
                              final HttpServletResponse response) {
        MediaController.logger.info("paramers: {}", id);
        int deleteStatus = 0;
        final Result result = new Result();
        try {
            final Media mediaOrigin = this.mediaService.selectDetail(id);
            deleteStatus = this.mediaService.delete(id);
            if (deleteStatus > 0) {
                this.cosUtil.deleteFile(this.key, mediaOrigin.getImageUrl());
            }
            result.setEntity(deleteStatus);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(MediaController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 设置上线/下线
     *
     * @param id
     * @param action
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "releaseMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String releaseMedia(@RequestParam(value = "id") final int id,
                               @RequestParam(value = "action") final String action,
                               final HttpServletResponse response) {
        MediaController.logger.info("paramers: {} {}", id, action);
        final Result result = new Result();
        final MediaActionEnum mediaActionEnum = MediaActionEnum.getItem(action);
        if (mediaActionEnum == null) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg(ResultEnum.BAD_REQUEST.getMsg());
            return result.getString(result);
        }
        try {
            if (mediaActionEnum.getCode() == 1) {
                final int count = this.mediaService.count(1);
                if (this.MEDIA_COUNT <= count) {
                    result.setCode(ResultEnum.MISSION_FAIL.getCode());
                    result.setMsg(ResultEnum.MISSION_FAIL.getMsg() + ":Up to 5 records.");
                    return result.getString(result);
                }
            }
            result.setEntity(this.mediaService.updateStatus(id, MediaActionEnum.getItem(action).getCode()));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(MediaController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 设置排序
     *
     * @param id
     * @param rank
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "rankMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String orderMedia(@RequestParam(value = "id") final int id,
                             @RequestParam(value = "rank") final int rank,
                             final HttpServletResponse response) {
        MediaController.logger.info("parameter: {} {}", id, rank);
        final Result result = new Result();
        try {
            result.setEntity(this.mediaService.updateQueue(id, rank));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(MediaController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 新闻动态查询
     * @param page
     * @param length
     * @param status
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryMedia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryMedia(@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                             @RequestParam(value = "length", required = false, defaultValue = "5") final int length,
                             @RequestParam(value = "status", required = false, defaultValue = "1") final int status,
                             final HttpServletResponse response) {
        MediaController.logger.info("paramers: {} {} {}", page, length, status);
        final Result result = new Result();
        if (page < 1) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        if (length < 1) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final Page<Media> pageMedia = this.mediaService.select(page, length, status);
            result.setEntity(pageMedia.getResult());
            result.setTotalCount(pageMedia.getTotalCount());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(MediaController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 查看详情
     *
     * @param id
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryDetail(final int id, final HttpServletResponse response) {
        MediaController.logger.info("paramers: {}", id);
        final Result result = new Result();
        try {
            final Media media = this.mediaService.selectDetail(id);
            media.setImageUrl(this.tencentUrl + this.key + media.getImageUrl());
            result.setEntity(media);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(MediaController.logger, e, response, result);
        }
        return result.getString(result);
    }

    private Result validateJson(final Media media) {
        final int titleLength = 40;
        final int descriptionLength = 100;
        final Result result = new Result();
        if (StringUtils.isBlank(media.getCnTitle())) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 中文标题必填");
            return result;
        }
        if (media.getCnTitle().length() > titleLength) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 中文标题不能超过40个字符");
            return result;
        }
        if (StringUtils.isBlank(media.getEnTitle())) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 英文标题必填");
            return result;
        }
        if (media.getEnTitle().length() > titleLength) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 英文标题不能超过40个字符");
            return result;
        }
        if (StringUtils.isBlank(media.getCnLink())) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 中文链接必填");
            return result;
        }
        if (StringUtils.isBlank(media.getEnLink())) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 英文链接必填");
            return result;
        }
        final String url = "((http|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
        final boolean isCnMatch = Pattern.matches(url, media.getCnLink());
        final boolean isEnMatch = Pattern.matches(url, media.getEnLink());
        if (!isCnMatch) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 中文链接格式不正确");
            return result;
        }
        if (!isEnMatch) {
            result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
            result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 英文链接格式不正确");
            return result;
        }
        if (StringUtils.isNotBlank(media.getCnDescription())) {
            if (media.getCnDescription().length() > descriptionLength) {
                result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
                result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 中文描述不能超过100个字符");
                return result;
            }
        }
        if (StringUtils.isNotBlank(media.getEnDescription())) {
            if (media.getEnDescription().length() > descriptionLength) {
                result.setCode(ResultEnum.PRECONDITION_FAILED.getCode());
                result.setMsg(ResultEnum.PRECONDITION_FAILED.getMsg() + ": 英文描述不能超过100个字符");
                return result;
            }
        }
        return null;
    }
}
