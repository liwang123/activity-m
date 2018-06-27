package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
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
import org.trustnote.activity.common.api.InvitationApi;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.InviteRecord;
import org.trustnote.activity.common.pojo.TransactionRecord;
import org.trustnote.activity.common.utils.ExcelInviteUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.ActivityService;
import org.trustnote.activity.service.iface.InviteRecordService;
import org.trustnote.activity.service.iface.TransactionRecordService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;
import org.trustnote.activity.utils.CreateImageCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl 17-12-28
 * @since v0.3
 */
@Frequency(name = "transaction", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/transaction")
public class TransactionRecordController {
    private static final Logger logger = LogManager.getLogger(TransactionRecordController.class);

    @Resource
    private TransactionRecordService transactionRecordService;
    @Resource
    private InviteRecordService inviteRecordService;

    @Resource
    private ActivityService activityService;


    /**
     * 查询被邀请人
     * @param limit
     * @param offset
     * @param inviteTrustnoteAddress
     * @param condition
     * @param label
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryInviteRecord(@RequestParam(value = "limit", required = false, defaultValue = "10") final Integer limit,
                                    @RequestParam(value = "offset", required = false, defaultValue = "0") final Integer offset,
                                    @RequestParam(value = "inviteTrustnoteAddress") final String inviteTrustnoteAddress,
                                    @RequestParam(value = "condition", required = false) final String condition,
                                    final String label,
                                    final HttpServletResponse response) {
        TransactionRecordController.logger.error("inviteTrustnoteAddress {} condition: {}", inviteTrustnoteAddress, condition);
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            result.setEntity(this.transactionRecordService.queryTransactionRecord(inviteTrustnoteAddress, condition, label, offset, limit));
        } catch (final Exception e) {
            TransactionRecordController.logger.error("exception {}", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
            result.setMsg(ResultEnum.INTERNAL_SERVER_ERROR.appendMsg("Internal Server Error."));
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryTransactionRecord(@RequestParam(value = "limit", required = false, defaultValue = "10") final Integer limit,
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
        final Page<TransactionRecord> page = new Page<>(pageNo, limit);

        boolean hasMore = false;

        try {
            final List<TransactionRecord> inviteRecords = this.transactionRecordService.queryAllTransactionRecord(page);
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(inviteRecords);
            result.setTotalCount(page.getTotalCount());
        } catch (final Exception e) {
            TransactionRecordController.logger.error("exception {}", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.getCode());
            result.setMsg(ResultEnum.INTERNAL_SERVER_ERROR.appendMsg("Internal Server Error."));
        }
        result.setHasMore(hasMore);
        return result.getString(result);
    }

    /**
     * 邀请活动添加
     * @param transaction
     * @param icode
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveTransactionRecord(final String transaction, final String icode, final HttpServletResponse response, final HttpSession session) {
        TransactionRecordController.logger.info("parameter {}", transaction);
        final Result result = new Result();
        if (StringUtils.isBlank(transaction)) {
            return ResultUtil.universalBlankReturn(response, result);
        }

        try {
            final InvitationApi invitationApi = JSON.parseObject(transaction, InvitationApi.class);
            if (StringUtils.isBlank(invitationApi.getMobilePhone()) || StringUtils.isBlank(invitationApi.getSmsCode())
                    || StringUtils.isBlank(invitationApi.getTrustnoteAddress()) || StringUtils.isBlank(icode) ) {
                return ResultUtil.universalBlankReturn(response, result);
            }
            final String vcode = (String) session.getAttribute("icode");
            final String scode = (String) session.getAttribute("scode");
            final String sessionPhone = (String) session.getAttribute("scode-phone");
            if (!invitationApi.getSmsCode().equals(scode)) {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                return result.getString(result);
            }
            if (invitationApi.getSmsCode().equals(scode) && sessionPhone.equals(invitationApi.getMobilePhone())) {
                TransactionRecordController.logger.info("scode: {} getCode: {}", scode, invitationApi.getSmsCode());
                TransactionRecordController.logger.info("scode-phone: {}, getMobile: {}", sessionPhone, invitationApi.getMobilePhone());
            }else {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                return result.getString(result);
            }

            if(!icode.equals(vcode)&&StringUtils.isNotEmpty(vcode)){
                TransactionRecordController.logger.info("icode**** {}, vcode: {}", icode, vcode);
                final CreateImageCode code = new CreateImageCode(100, 30, 4, 0);
                session.setAttribute("icode", code.getCode());
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                final String ii = session.getAttribute("icode").toString();
                TransactionRecordController.logger.info("*****ii", ii);
                return result.getString(result);
            }
            final LocalDateTime now = LocalDateTime.now();
            final List<Activity> activityList = this.activityService.selectByTime(now);
            if(activityList.size()==0){
                TransactionRecordController.logger.info("The current time is not in the activity");
                return null;
            }

            final List<InviteRecord> inviteRecords = this.inviteRecordService.queryInviteRecordByMobile(invitationApi.getMobilePhone());
            if (CollectionUtils.isNotEmpty(inviteRecords)) {
                result.setCode(ResultEnum.CONFLICT.getCode());
                result.setMsg(ResultEnum.CONFLICT.getMsg());
                return result.getString(result);
            }
            final InviteRecord inviteRecord = this.inviteRecordService.queryInviteRecordByTrustnoteAddress(invitationApi.getTrustnoteAddress());
            if (null != inviteRecord) {
                result.setCode(ResultEnum.CONFLICT.getCode());
                result.setMsg(ResultEnum.CONFLICT.getMsg());
                return result.getString(result);
            }

            final String inviteCode = this.transactionRecordService.saveTransactionRecord(invitationApi);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(inviteCode);
            return result.getString(result);
        } catch (final JSONException e) {
            return ResultUtil.universalJSONExceptionReturn(TransactionRecordController.logger, e, response, result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(TransactionRecordController.logger, e, response, result);
        }
    }

    /**
     * 发送TTT下载模板接口
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/export", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity exportExcel(final HttpServletResponse response) {
        String excel = "";
        try {
            final ArrayList<InviteRecord> list = new ArrayList<>();
            final List<String> header = new ArrayList<>();
            header.add("邀请人TrustNote钱包地址");
            header.add("手机号/邮箱");
            header.add("邀请码");
            header.add("获得TTT数量");
            header.add("邀请数");
            header.add("提交时间");
            excel = ExcelInviteUtils.exportExcel("邀请人列表.xls", header, list, 1, response);
        } catch (final Exception e) {
            TransactionRecordController.logger.error("exception {}", e);
        }
        return new ResponseEntity(excel, HttpStatus.OK);
    }
}