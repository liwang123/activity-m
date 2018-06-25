package org.trustnote.activity.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.common.utils.DateTimeUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.FinancialBenefitsService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "financialbenefits", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/financial-benefits")
public class FinancialBenefitsController {
    private static final Logger logger = LogManager.getLogger(FinancialBenefitsController.class);

    @Resource
    private FinancialBenefitsService financialBenefitsService;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String query(@RequestParam(value = "limit") final int limit,
                        @RequestParam(value = "offset") final int offset,
                        final HttpServletResponse response) {
        FinancialBenefitsController.logger.info("paramers: {} {}", limit, offset);
        final Result result = new Result();
        final int pageNo;
        if (offset == 0) {
            pageNo = 1;
        } else {
            pageNo = offset / limit + 1;
        }
        final Page<FinancialBenefits> page = new Page<>(pageNo, limit);
        boolean hasMore = false;

        try {
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(this.financialBenefitsService.queryFinancialBenefits(page));
            result.setTotalCount(page.getTotalCount());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        result.setHasMore(hasMore);
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insertFinancialBenefits(@Valid final FinancialBenefitsApi financialBenefitsApi,
                                          final Errors errors,
                                          final HttpServletResponse response) {
        final Result result = new Result();
        final Result errorsResult = this.errors(errors);
        if (errorsResult != null) {
            return result.getString(errorsResult);
        }
        final Result validation = this.specialValidation(financialBenefitsApi);
        if (validation != null) {
            return result.getString(validation);
        }
        try {
            final int insertStatus = this.financialBenefitsService.insertFinancialBenefits(financialBenefitsApi);
            if (insertStatus == -1) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg("与其他套餐时间冲突");
            } else if (insertStatus == 0) {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            } else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            }
            result.setEntity(insertStatus);
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateFinancialBenefits(@Valid final FinancialBenefitsApi financialBenefitsApi,
                                          final Errors errors,
                                          final HttpServletResponse response) {
        final Result result = new Result();
        final Result errorsResult = this.errors(errors);
        if (errorsResult != null) {
            return result.getString(errorsResult);
        }
        final Result validation = this.specialValidation(financialBenefitsApi);
        if (validation != null) {
            return result.getString(validation);
        }
        try {
            int updateStatus = 0;
            final FinancialBenefits financialBenefits = this.financialBenefitsService.queryOneFinancialBenefits(financialBenefitsApi.getId());
            if (financialBenefits != null) {
                if (financialBenefits.getPanicStartTime().compareTo(LocalDateTime.now()) != 1) {
                    result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                    result.setCode(ResultEnum.MISSION_FAIL.getCode());
                } else {
                    updateStatus = this.financialBenefitsService.updateFinancialBenefits(financialBenefitsApi);
                    if (updateStatus == -1) {
                        result.setCode(ResultEnum.BAD_REQUEST.getCode());
                        result.setMsg("与其他套餐时间冲突");
                    } else if (updateStatus == 0) {
                        result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                        result.setCode(ResultEnum.MISSION_FAIL.getCode());
                    } else {
                        result.setCode(ResultEnum.OK.getCode());
                        result.setMsg(ResultEnum.OK.getMsg());
                    }
                }
            } else {
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
            }
            result.setEntity(updateStatus);
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 根据套餐ＩＤ获取当前理财产品
     *
     * @param errors
     * @return
     */
    private Result errors(final Errors errors) {
        final Result result = new Result();
        if (errors.hasErrors()) {
            final List<ObjectError> errorList = errors.getAllErrors();
            for (final ObjectError e : errorList) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(e.getDefaultMessage());
                return result;
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "push", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String pushFinancialBenefits(@RequestParam(value = "financialId") final int financialId, final HttpServletResponse response) {
        FinancialBenefitsController.logger.info("parameter: {}", financialId);
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialBenefitsService.queryFinancialBenefitsByFinancialId(financialId));
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 根据产品ＩＤ获取当前理财产品
     *
     * @param financialBenefitsId
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "push_benefitid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String pushFinancialBeneftits(@RequestParam(value = "financialBenefitsId") final int financialBenefitsId, final HttpServletResponse response) {
        FinancialBenefitsController.logger.info("parameter: {}", financialBenefitsId);
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialBenefitsService.queryFinancialBenefitsById(financialBenefitsId));
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 后台查询单个理财产品
     *
     * @param id
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getFinancial", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryOneFinancialBenefits(@RequestParam(value = "id") final int id, final HttpServletResponse response) {
        FinancialBenefitsController.logger.info("parameter: {}", id);
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialBenefitsService.queryFinancialBenefitsByIdExcludeNextInfo(id));
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "finish", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String finishBenefits(@RequestParam(value = "id") final int id,
                                 final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialBenefitsService.updateFinancialStatus(id));
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 特殊校验
     *
     * @param financialBenefitsApi
     * @return
     */
    private Result specialValidation(final FinancialBenefitsApi financialBenefitsApi) {
        final Result result = new Result();
        final long now = DateTimeUtils.localDateTimeParseLong(LocalDateTime.now());
        if (financialBenefitsApi.getPanicStartTime() <= now) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("抢购开始时间不能小于当前时间");
            return result;
        }
        if (financialBenefitsApi.getPanicEndTime() <= now) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("抢购结束时间不能小于当前时间");
            return result;
        }
        if (financialBenefitsApi.getInterestStartTime() <= now) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("计息开始时间不能小于当前时间");
            return result;
        }
        if (financialBenefitsApi.getInterestEndTime() <= now) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("计息结束时间不能小于当前时间");
            return result;
        }
        if (financialBenefitsApi.getUnlockTime() <= now) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("解锁时间不能小于当前时间");
            return result;
        }
        if (financialBenefitsApi.getUnlockTime() <= financialBenefitsApi.getInterestEndTime()) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("解锁时间不能小于等于计息结束时间");
            return result;
        }
        if (financialBenefitsApi.getPanicEndTime() <= financialBenefitsApi.getPanicStartTime()) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("抢购结束时间不能小于等于抢购开始时间");
            return result;
        }
        if (financialBenefitsApi.getInterestEndTime() <= financialBenefitsApi.getInterestStartTime()) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("计息结束时间不能小于等于计息开始时间");
            return result;
        }
        if (financialBenefitsApi.getInterestStartTime() <= financialBenefitsApi.getPanicEndTime()) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("计息开始时间不能小于抢购结束时间");
            return result;
        }
        if (financialBenefitsApi.getFinancialRate() > 1) {
            result.setCode(ResultEnum.BAD_REQUEST.getCode());
            result.setMsg("请输入正确的年化收益率");
            return result;
        }
        if (financialBenefitsApi.getFinancialId() == 1) {
            if (financialBenefitsApi.getPanicTotalLimit() == null) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg("请输入抢购总额度");
                return result;
            }
            if (financialBenefitsApi.getPurchaseLimit() == null) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg("请输入限额");
                return result;
            }
        }
        return null;
    }
}
