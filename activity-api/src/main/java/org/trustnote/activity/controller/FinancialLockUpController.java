package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.FinancialLockUp;
import org.trustnote.activity.common.utils.ExcelUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.FinancialLockUpService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;
import static org.trustnote.activity.controller.ResultUtil.universalJSONExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "financiallockup", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/financial-lockup")
public class FinancialLockUpController {
    private static final Logger logger = LogManager.getLogger(FinancialLockUpController.class);

    @Resource
    private FinancialLockUpService financialLockUpService;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryFinancialLockUp(@RequestParam(value = "limit") final int limit,
                                       @RequestParam(value = "offset") final int offset,
                                       @RequestParam(value = "benefitsId") final int benefitsId,
                                       final HttpServletResponse response) {
        FinancialLockUpController.logger.info("parameter: {} {} {}", limit, offset, benefitsId);
        final Result result = new Result();
        final int pageNo;
        if (offset == 0) {
            pageNo = 1;
        } else {
            pageNo = offset / limit + 1;
        }
        final Page<FinancialLockUp> page = new Page<>(pageNo, limit);
        boolean hasMore = false;

        try {
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(this.financialLockUpService.queryFinancialLockUp(page, benefitsId));
            result.setTotalCount(page.getTotalCount());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialLockUpController.logger, e, response, result);
        }
        result.setHasMore(hasMore);
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveFincialLockUp(@RequestBody final String json,
                                    final HttpServletResponse response) {
        FinancialLockUpController.logger.info("parameter: {}", json);
        final Result result = new Result();
        if (StringUtils.isBlank(json)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final FinancialLockUp financialLockUp = JSON.parseObject(json, FinancialLockUp.class);
            if (StringUtils.isBlank(financialLockUp.getSharedAddress())) {
                return ResultUtil.universalBlankReturn(response, result);
            }
            if (StringUtils.isBlank(financialLockUp.getDeviceAddress())) {
                return ResultUtil.universalBlankReturn(response, result);
            }
            if (null == financialLockUp.getFinancialBenefitsId()) {
                return ResultUtil.universalBlankReturn(response, result);
            }
            final FinancialLockUp checkLockUp = this.financialLockUpService.queryLockUp(financialLockUp);
            int operationStatus = 0;
            if (checkLockUp == null) {
                operationStatus = this.financialLockUpService.saveFinancialLockUp(financialLockUp);
            }
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(operationStatus);
        } catch (final JSONException e) {
            return universalJSONExceptionReturn(FinancialLockUpController.logger, e, response, result);
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialLockUpController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryFincialLockUpByDeviceAddress(@RequestParam(value = "deviceAddress") final String deviceAddress,
                                                    final HttpServletResponse response) {
        FinancialLockUpController.logger.info("parameter: {}", deviceAddress);
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialLockUpService.queryFincialLockUpByDeviceAddress(deviceAddress));
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialLockUpController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @RequestMapping(value = "/export", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity export(@RequestParam(value = "benefitsId") final int benefitsId,
                                 final HttpServletResponse response) {
        final String export;
        try {
            final List<String> header = new ArrayList<>();
            header.add("合约地址");
            header.add("锁仓金额(MN)");
            header.add("收益金额(MN)");
            header.add("状态");
            header.add("操作时间");
            final List<Map<String, String>> contents = this.financialLockUpService.export(benefitsId);
            export = ExcelUtils.exportExcel("合约信息.xls", header, contents, 3, response);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(export, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/participate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String participate(final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialLockUpService.participate());
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialLockUpController.logger, e, response, result);
        }
        return result.getString(result);
    }
}
