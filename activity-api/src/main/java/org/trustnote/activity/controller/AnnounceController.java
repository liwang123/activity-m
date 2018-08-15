package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Announce;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.AnnounceService;
import org.trustnote.activity.service.iface.UserService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhuxl 18-1-29
 * @since v0.3
 */
@Frequency(name = "announce", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/ann")
public class AnnounceController {
    private static final Logger logger = LogManager.getLogger(AnnounceController.class);
    @Resource
    private AnnounceService announceService;
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryAnn(@RequestParam(value = "limit", required = false, defaultValue = "10") final Integer limit,
                           @RequestParam(value = "offset", required = false, defaultValue = "0") final Integer offset,
                           final HttpServletResponse response) {
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        int pageNo = 0;
        if (offset == 0) {
            pageNo = 1;
        } else {
            pageNo = (int) (offset / limit) + 1;
        }

        final Page<Announce> page = new Page<>(pageNo, limit);

        boolean hasMore = false;
        try {
            final List<Announce> announces = this.announceService.queryAnnounceByPage(page);
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(announces);
            result.setTotalCount(page.getTotalCount());
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(AnnounceController.logger, e, response, result);
        }

        result.setHasMore(hasMore);
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String showAnn(@RequestParam(value = "limit", required = false, defaultValue = "10") final Integer limit,
                          @RequestParam(value = "offset", required = false, defaultValue = "0") final Integer offset,
                          final HttpServletResponse response) {
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        int pageNo = 0;
        if (offset == 0) {
            pageNo = 1;
        } else {
            pageNo = (int) (offset / limit) + 1;
        }

        final Page<Announce> page = new Page<>(pageNo, limit);

        boolean hasMore = false;
        try {
            final List<Announce> announces = this.announceService.queryAnnounceAndTopByPage(page);
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(announces);
            result.setTotalCount(page.getTotalCount());
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(AnnounceController.logger, e, response, result);
        }

        result.setHasMore(hasMore);
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveAnn(@RequestBody final String ann, final HttpServletResponse response, final HttpSession session) {
        AnnounceController.logger.info("parameters: {}", ann);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Result result = new Result();
        if (StringUtils.isBlank(ann)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final Announce announce = JSON.parseObject(ann, Announce.class);
            final String username = authentication.getName();
            if (StringUtils.isNotBlank(username)) {
                final User user = this.userService.queryUser(username);
                if (null != user) {
                    announce.setLastBy(user.getId());
                }
            }
            final int saveResult = this.announceService.saveAnnounce(announce);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(saveResult);
            return result.getString(result);
        } catch (final JSONException e) {
            return ResultUtil.universalJSONExceptionReturn(AnnounceController.logger, e, response, result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(AnnounceController.logger, e, response, result);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateAnn(@RequestBody final String ann, final HttpServletResponse response, final HttpSession session) {
        AnnounceController.logger.info("parameters {}", ann);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Result result = new Result();
        if (StringUtils.isBlank(ann)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final Announce announce = JSON.parseObject(ann, Announce.class);
            if (announce == null || announce.getId() == 0) {
                return ResultUtil.universalBlankReturn(response, result);
            }
            final String username = authentication.getName();
            if (StringUtils.isNotBlank(username)) {
                final User user = this.userService.queryUser(username);
                if (null != user) {
                    announce.setLastBy(user.getId());
                }
            }

            final int updateResult = this.announceService.updateAnnounce(announce);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(updateResult);
            return result.getString(result);
        } catch (final JSONException e) {
            return ResultUtil.universalJSONExceptionReturn(AnnounceController.logger, e, response, result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(AnnounceController.logger, e, response, result);
        }
    }

    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String delAnn(@RequestParam(value = "id") final Integer id, final HttpServletResponse response) {
        AnnounceController.logger.info("parameters {}", id);
        final Result result = new Result();
        if (null == id) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final int delResult = this.announceService.delAnnounce(id);
            if (delResult > 0) {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            } else {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            }
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(AnnounceController.logger, e, response, result);
        }
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public void changeViewed(@RequestParam(value = "id") final Integer id, @RequestParam(value = "type") final Integer type, final HttpServletResponse response) {
        AnnounceController.logger.info("parameters {} {}", id, type);
        try {
            final int changeResult = this.announceService.changeViewed(id, type);
            AnnounceController.logger.info("read 结果: {}", changeResult);
        } catch (final Exception e) {
            AnnounceController.logger.error("error: {}", e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "changeAv", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String changeAvailable(@RequestParam(value = "id") final Integer id, @RequestParam(value = "status") final Integer status, final HttpServletResponse response) {
        AnnounceController.logger.info("parameters {} {}", id, status);
        final Result result = new Result();
        if (null == id || null == status) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final int changeResult = this.announceService.changeAvailable(id, status);
            if (changeResult < 1) {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            } else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            }
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(AnnounceController.logger, e, response, result);
        }
    }

    @ResponseBody
    @RequestMapping(value = "showOne", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAnn(@RequestParam(value = "id") final Integer id, final HttpServletResponse response) {
        AnnounceController.logger.info("parameters {}", id);
        final Result result = new Result();
        if (null == id) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final Announce announce = this.announceService.getAnnounceById(id);
            if (null == announce) {
                result.setCode(ResultEnum.NOT_FOUND.getCode());
                result.setMsg(ResultEnum.NOT_FOUND.getMsg());
            } else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(announce);
            }
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(AnnounceController.logger, e, response, result);
        }
    }

}
