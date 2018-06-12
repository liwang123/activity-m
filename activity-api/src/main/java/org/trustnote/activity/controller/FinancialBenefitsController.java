package org.trustnote.activity.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.FinancialBenefits;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.FinancialBenefitsService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    public String insertFinancialBenefits(final FinancialBenefitsApi financialBenefitsApi,
                                          final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialBenefitsService.insertFinancialBenefits(financialBenefitsApi));
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateFinancialBenefits(final FinancialBenefitsApi financialBenefitsApi, final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialBenefitsService.updateFinancialBenefits(financialBenefitsApi));
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialBenefitsController.logger, e, response, result);
        }
        return result.getString(result);
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
}
