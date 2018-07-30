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
import org.springframework.web.multipart.MultipartFile;
import org.trustnote.activity.common.api.FinancialBenefitsApi;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Financial;
import org.trustnote.activity.common.pojo.FinancialLockUp;
import org.trustnote.activity.common.utils.ExcelInviteUtils;
import org.trustnote.activity.common.utils.ExcelUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.FinancialBenefitsService;
import org.trustnote.activity.service.iface.FinancialLockUpService;
import org.trustnote.activity.service.iface.FinancialService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Resource
    private FinancialBenefitsService financialBenefitsService;
    @Resource
    private FinancialService financialService;

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryFinancialLockUp(@RequestParam(value = "limit") final int limit,
                                       @RequestParam(value = "offset") final int offset,
                                       @RequestParam(value = "benefitsId", required = false) final Integer benefitsId,
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
    @RequestMapping(value = "/queryLockUp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryFinancialLockUpBySe(@RequestParam(value = "limit") final int limit,
                                           @RequestParam(value = "offset") final int offset,
                                           @RequestParam(value = "financialId") final int financialId,
                                           @RequestParam(value = "type") final int type,
                                           @RequestParam(value = "value") final String value,
                                           final HttpServletResponse response) {
        FinancialLockUpController.logger.info("parameter: {} {} {} {} {}", limit, offset, financialId, type, value);
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
            result.setEntity(this.financialLockUpService.queryFinancialLockUp(page, FinancialBenefitsApi.builder()
                    .financialId(financialId)
                    .build(), type, value));
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
            if (StringUtils.isBlank(financialLockUp.getWalletAddress())) {
                return ResultUtil.universalBlankReturn(response, result);
            }
            if (null == financialLockUp.getFinancialBenefitsId()) {
                return ResultUtil.universalBlankReturn(response, result);
            }
            final FinancialLockUp checkLockUp = this.financialLockUpService.queryLockUp(financialLockUp);
            final FinancialBenefitsApi financialBenefitsApi = this.financialBenefitsService.queryFinancialBenefitsByIdExcludeNextInfo(financialLockUp.getFinancialBenefitsId());
            BigDecimal inComeAmount = new BigDecimal(0);
            BigDecimal tFansAmount = new BigDecimal(0);
            if (financialBenefitsApi != null && financialLockUp.getOrderAmount() != null) {
                final Financial financial = this.financialService.queryOneFinancial(financialBenefitsApi.getFinancialId());
                //理财周期
                final BigDecimal numericalv = BigDecimal.valueOf(financial.getNumericalv());
                //年化利率
                final BigDecimal rate = BigDecimal.valueOf(financialBenefitsApi.getFinancialRate()).setScale(2, BigDecimal.ROUND_DOWN);
                //计算收益
                final BigDecimal all = new BigDecimal(financialLockUp.getOrderAmount()).multiply(numericalv).multiply(rate);
                inComeAmount = all.divide(new BigDecimal(360), 1, BigDecimal.ROUND_DOWN);
                tFansAmount = inComeAmount.multiply(new BigDecimal(financialBenefitsApi.getTFans()))
                        .setScale(0, BigDecimal.ROUND_DOWN);
            }
            int operationStatus = 0;
            if (checkLockUp == null) {
                operationStatus = this.financialLockUpService.saveFinancialLockUp(financialLockUp);
            }
            final Map<String, Object> entity = new HashMap<>(3);
            entity.put("operation_status", operationStatus);
            entity.put("income_amount", inComeAmount);
            entity.put("tfans_amount", tFansAmount);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(entity);
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
            header.add("已抢购金额(MN)");
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

    @RequestMapping(value = "/exportTFs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity exportTFs(@RequestParam(value = "benefitsId") final int benefitsId,
                                    final HttpServletResponse response) {
        final String export;
        try {
            final List<String> header = new ArrayList<>();
            header.add("钱包地址");
            header.add("获得TFS数量");
            final List<Map<String, String>> contents = this.financialLockUpService.exportTFS(benefitsId);
            export = ExcelUtils.exportExcel("TFS信息.xls", header, contents, 4, response);
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

    /**
     * 手动调用接口执行计算收益
     *
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/manual", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String manual(final HttpServletResponse response) {
        final Result result = new Result();
        try {
            this.financialLockUpService.saveInComeAmount();

            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity("计算中，请查看日志...");
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialLockUpController.logger, e, response, result);
        }
        return result.getString(result);
    }

    /**
     * 手动导入钱包地址
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFileWalletAddress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String uploadFileWalletAddress(@RequestParam("file") final MultipartFile file) {
        final Result result = new Result();
        if (!file.isEmpty()) {
            try {
                final String rootPath = System.getProperty("user.home");
                final File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 写文件到服务器
                final File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                FinancialLockUpController.logger.info("path {}" + dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                final List list = ExcelInviteUtils.readExcel(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(this.financialLockUpService.readWalletAddress(list));
                return result.getString(result);
            } catch (final Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/manualTFans", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String manualTFans(final HttpServletResponse response) {
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.financialLockUpService.manualTFans());
        } catch (final Exception e) {
            return universalExceptionReturn(FinancialLockUpController.logger, e, response, result);
        }
        return result.getString(result);
    }
}
