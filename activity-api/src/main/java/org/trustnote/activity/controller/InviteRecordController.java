package org.trustnote.activity.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.InviteRecord;
import org.trustnote.activity.common.utils.ExcelInviteUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.ActivityService;
import org.trustnote.activity.service.iface.InviteRecordService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;
import org.trustnote.activity.utils.ImageUtils.Captcha;
import org.trustnote.activity.utils.ImageUtils.GifCaptcha;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Frequency(name = "invite", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/invite")
public class InviteRecordController {
    private static final Logger logger = LogManager.getLogger(InviteRecordController.class);
    @Resource
    private InviteRecordService inviteRecordService;

    @Resource
    private ActivityService activityService;
    //暂时弃用
    @ResponseBody
    @RequestMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryInviteRecord(@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                    @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                    @RequestParam(value = "inviteCode", required = false) String inviteCode,
                                    HttpServletResponse response) {
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        int pageNo = 0;
        if (offset == 0) {
            pageNo = 1;
        } else {
            pageNo = (int) (offset / limit) + 1;
        }
        Page<InviteRecord> page = new Page<>(pageNo, limit);

        boolean hasMore = false;

        try {
            List<InviteRecord> inviteRecords = inviteRecordService.queryInviteRecord(page, inviteCode);
            logger.info("totalCount: {}", page.getTotalCount());
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(inviteRecords);
            result.setTotalCount(page.getTotalCount());
        } catch (Exception e) {
            logger.error("exception {}", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
            result.setMsg(ResultEnum.INTERNAL_SERVER_ERROR.appendMsg("Internal Server Error."));
        }
        result.setHasMore(hasMore);
        return result.getString(result);
    }

    /**
     * 查询邀请记录
     * @param inviteCode
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/hasInvited", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryInvite(@RequestParam(value = "inviteCode") String inviteCode, HttpServletResponse response) {
        logger.info("parameters {}", inviteCode);
        Result result = new Result();
        if (StringUtils.isBlank(inviteCode)) return ResultUtil.universalBlankReturn(response, result);
        try {
            LocalDateTime now = LocalDateTime.now();
            List<Activity> activityList = activityService.selectByTime(now);
            if(activityList.size()==0){
                    activityList = activityService.getByDEscTime(now);
                }
            InviteRecord inviteRecord = inviteRecordService.queryByInviteCode(inviteCode,activityList.get(0));
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            int several = 0;
            int rewardTtn = 0;
            if (null != inviteRecord && null != inviteRecord.getInviteSeveral()) {
                several = inviteRecord.getInviteSeveral();
            }
            if (null != inviteRecord && null != inviteRecord.getRewardTtn()) {
                rewardTtn = inviteRecord.getRewardTtn();
            }

            Map<String, Object> map = new HashMap<>();
            map.put("inviteSeveral", several);
            map.put("rewardTtn", rewardTtn);
            result.setEntity(map);
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger, e, response, result);
        }
    }

    /**
     * 根据手机号查询参与活动
     * @param mobile
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryCode(@RequestParam(value = "mobile") String mobile, HttpServletResponse response) {
        logger.info("parameters {}", mobile);
        Result result = new Result();
        if (StringUtils.isBlank(mobile)) return ResultUtil.universalBlankReturn(response, result);
        try {
            List<InviteRecord> inviteRecords = inviteRecordService.queryByMobile(mobile);
            if (CollectionUtils.isEmpty(inviteRecords)) {
                result.setCode(ResultEnum.NOT_FOUND.getCode());
                result.setMsg(ResultEnum.NOT_FOUND.getMsg());
                result.setEntity(null);
            }else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(inviteRecords.get(0).getInviteCode());
            }
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger, e, response, result);
        }
    }

    /**
     * 邀请页面验证码
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public void getValidate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");
        /**
         * gif格式动画验证码
         * 宽，高，位数。
         */
        try {
        Captcha captcha = new GifCaptcha(146,33,4);
        //输出
        captcha.out(response.getOutputStream());
        //存入Session
        session.setAttribute("icode",captcha.text().toLowerCase());
        logger.info("icode {}", captcha.text().toLowerCase());
        logger.info("sessionId {}", session.getId());
        String ii = session.getAttribute("icode").toString();
        logger.info("session icode {}", ii);
        } catch (IOException e) {
            logger.error("exception {}", e);
        }
    }

    /**
     * 手机邮箱检查
     * @param mobile
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mobileCheck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String mobilePhoneValication(@RequestParam(value = "mobile") String mobile, HttpServletResponse response) {
        logger.info("parameter {}", mobile);
        Result result = new Result();
        if (StringUtils.isBlank(mobile)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
             List<InviteRecord> inviteRecords = inviteRecordService.queryInviteRecordByMobile(mobile);
            if (CollectionUtils.isEmpty(inviteRecords)) {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            }else {
                result.setCode(ResultEnum.CONFLICT.getCode());
                result.setMsg(ResultEnum.CONFLICT.getMsg());
            }
            return result.getString(result);
        }catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 地址检查
     * @param trustnoteAddress
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/trustnoteAddressCheck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String trustnoteAddressValication(@RequestParam(value = "trustnoteAddress") String trustnoteAddress, HttpServletResponse response) {
        logger.info("parameter {}", trustnoteAddress);
        Result result = new Result();
        if (StringUtils.isBlank(trustnoteAddress)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            InviteRecord inviteRecord = inviteRecordService.queryInviteRecordByTrustnoteAddress(trustnoteAddress);
            if (null == inviteRecord) {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            }else {
                result.setCode(ResultEnum.CONFLICT.getCode());
                result.setMsg(ResultEnum.CONFLICT.getMsg());
            }
            return result.getString(result);
        }catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    /**
     * 邀请人导出
     * @param response
     * @param label
     * @param condition
     * @param type
     * @return
     */
    @RequestMapping(value = "/export", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity exportExcel(HttpServletResponse  response
            ,String label,String condition,int type) {
        String excel = "";
        String fileName=null;
        try {
            List<InviteRecord> lists=inviteRecordService.exportInviteRecordBySelective(label, condition,type);
            List<String> header=new ArrayList<>();
            header.add("邀请人TrustNote钱包地址");
            header.add("手机号/邮箱");
            header.add("邀请码");
            header.add("获得TTT数量");
            header.add("邀请数");
            header.add("提交时间");
            if(type==1){fileName="邀请人列表.xls";}
            if(type==2){fileName="邀请人排序列表.xls";}
            if(type==3){fileName="邀请人海外列表.xls";}
            if(type==4){fileName="邀请人海外排序列表.xls";}
            excel = ExcelInviteUtils.exportExcel(fileName, header, lists, type, response);
        } catch (Exception e) {
            logger.error("exception {}", e);
        }
        return new ResponseEntity(excel, HttpStatus.OK);
    }
}
