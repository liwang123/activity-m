package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.Questionnaire;
import org.trustnote.activity.common.utils.ExcelInviteUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.CoinService;
import org.trustnote.activity.service.iface.GiftSetService;
import org.trustnote.activity.service.iface.QuestionnaireService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
//@Frequency(name = "coin", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/coin")
public class CoinController {
    private static final Logger logger = LogManager.getLogger(CoinController.class);
    @Resource
    private CoinService coinService;

    @Resource
    private GiftSetService giftSetService;

    @Resource
    private QuestionnaireService questionnaireService;
    /**
     * 查询余额
     * @param address
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryCoin(@RequestParam(value = "address") final String address,
                            final HttpServletResponse response) {
        CoinController.logger.info("paramers: {}", address);
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            result.setEntity(this.coinService.getbalance(address));
        } catch (final JsonRpcClientException e) {
            CoinController.logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(CoinController.logger, e, response, result);
        } catch (final Throwable throwable) {
            return ResultUtil.universalThrowableReturn(CoinController.logger, throwable, response, result);
        }
        return result.getString(result);
    }

    /**
     * 发币
     * @param params
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String sendToMultiAddress(@RequestBody final String params,
                                     @RequestParam(value = "code") final String code,
                                     final HttpServletResponse response) {
        CoinController.logger.info("parameters: params: {} code: {}", params, code);
        final Result result = new Result();
        if (StringUtils.isBlank(params)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final JSONArray array = JSON.parseArray(params);
            final String sendResult = this.coinService.sendToMultiAddress(array, code);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(sendResult);
            return result.getString(result);
        } catch (final JsonRpcClientException e) {
            CoinController.logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            result.setEntity("地址错误.");
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(CoinController.logger, e, response, result);
        } catch (final Throwable throwable) {
            return ResultUtil.universalThrowableReturn(CoinController.logger, throwable, response, result);
        }
    }

    /**
     * 导入execl
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String uploadFileHandler(@RequestParam("file") final MultipartFile file) {
        CoinController.logger.info("parameters: {}", file);
        final Result result = new Result();
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                final String rootPath = System.getProperty("user.home");
                final File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()){ dir.mkdirs();}
                // 写文件到服务器
                final File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                CoinController.logger.info("path {}" + dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                final List list = ExcelInviteUtils.readExcel(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                final Map<String, String> map = this.coinService.AnalyData(list);
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(map);
                return result.getString(result);
            } catch (final Exception e) {
                return "You failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " +  file.getOriginalFilename() + " because the file was empty.";
        }
    }
    /**
     * 查询余额
     * @param address
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryByAdress",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryByAdress(@RequestParam(value = "address") final String address,
                                final HttpServletResponse response) {
        CoinController.logger.info("paramers: {}", address);
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            final Questionnaire questionnaire = this.questionnaireService.selectBySelective(0, address);
            if(questionnaire!=null){
                if(questionnaire.getJob()!=null){
                    result.setCode(ResultEnum.MISSION_FAIL.getCode());
                    result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                    return result.getString(result);
                }
            }
            this.coinService.getbalanceAll(address);
            result.setEntity("ok");
        } catch (final JsonRpcClientException e) {
            CoinController.logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(CoinController.logger, e, response, result);
        } catch (final Throwable throwable) {
            return ResultUtil.universalThrowableReturn(CoinController.logger, throwable, response, result);
        }
        return result.getString(result);
    }
    /**
     * token发币测试
     * @param address
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendTotoken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String sendTotoken(@RequestParam(value = "address") final String address, final HttpServletResponse response, final HttpSession session) {
        CoinController.logger.info("parameters: {}", address);

        final Result result = new Result();
        if (StringUtils.isBlank(address)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        final String sendResult = this.giftSetService.sendToken(address);
        System.out.println(sendResult);
        if ("null".equals(sendResult)) {
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            result.setEntity(sendResult);
            return result.getString(result);
        }
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        result.setEntity(sendResult);
        return result.getString(result);
    }

    /**
     * Token查询余额
     * @param address
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryTokenBalance",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryTokenBalance(@RequestParam(value = "address") final String address,
                                    final HttpServletResponse response) {
        CoinController.logger.info("paramers: {}", address);
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            result.setEntity(this.giftSetService.getTokenbalance(address));
        } catch (final JsonRpcClientException e) {
            CoinController.logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(CoinController.logger, e, response, result);
        } catch (final Throwable throwable) {
            return ResultUtil.universalThrowableReturn(CoinController.logger, throwable, response, result);
        }
        return result.getString(result);
    }


    /**
     * excel导入　持仓收益
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFileSmart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String uploadFileSmart(@RequestParam("file") final MultipartFile file) {
        CoinController.logger.info("parameters: {}", file);
        final Result result = new Result();
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                final String rootPath = System.getProperty("user.home");
                final File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 写文件到服务器
                final File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                CoinController.logger.info("path {}" + dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                final List list = ExcelInviteUtils.readExcel(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                final Map<String, String> map = this.coinService.AnalyDataFinancial(list);
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(map);
                return result.getString(result);
            } catch (final Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
        }
    }

    /**
     * 查询所有余额
     *
     * @param code
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryMainBalance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryCurrentMainBalance(@RequestParam(value = "code") final String code,
                                          final HttpServletResponse response) {
        CoinController.logger.info("paramers: {}", code);
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            result.setEntity(this.coinService.getMainBalance(code));
        } catch (final JsonRpcClientException e) {
            CoinController.logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(CoinController.logger, e, response, result);
        } catch (final Throwable throwable) {
            return ResultUtil.universalThrowableReturn(CoinController.logger, throwable, response, result);
        }
        return result.getString(result);
    }

}
