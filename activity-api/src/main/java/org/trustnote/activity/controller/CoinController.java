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
import org.trustnote.activity.common.jsonrpc.JsonRpcTotal;
import org.trustnote.activity.common.pojo.Questionnaire;
import org.trustnote.activity.common.utils.ExcelInviteUtils;
import org.trustnote.activity.common.utils.ExcelUtils;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.CoinService;
import org.trustnote.activity.service.iface.GiftSetService;
import org.trustnote.activity.service.iface.QuestionnaireService;
import org.trustnote.activity.stereotype.Frequency;

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
@Frequency(name = "coin", limit = 300, time = 60)
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
    public String queryCoin(@RequestParam(value = "address") String address,
                           HttpServletResponse response) {
        logger.info("paramers: {}", address);
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            result.setEntity(coinService.getbalance(address));
        } catch (JsonRpcClientException e) {
            logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        } catch (Throwable throwable) {
            return ResultUtil.universalThrowableReturn(logger , throwable, response, result);
        }
        return result.getString(result);
    }

    /**
     * 发币
     * @param params
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String sendToMultiAddress(@RequestBody String params, HttpServletResponse response, HttpSession session) {
        logger.info("parameters: {}", params);
        Result result = new Result();
        if (StringUtils.isBlank(params)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            JSONArray array = JSON.parseArray(params);
            String sendResult = coinService.sendToMultiAddress(array);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(sendResult);
            return result.getString(result);
        } catch (JsonRpcClientException e) {
            logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            result.setEntity("地址错误.");
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        } catch (Throwable throwable) {
            return ResultUtil.universalThrowableReturn(logger , throwable, response, result);
        }
    }

    /**
     * 导入execl
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public  String uploadFileHandler( @RequestParam("file") MultipartFile file) {
        logger.info("parameters: {}", file);
        Result result = new Result();
        if (!file.isEmpty()) {
            try {
                // 文件存放服务端的位置
                String rootPath = System.getProperty("user.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()){ dir.mkdirs();}
                // 写文件到服务器
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                logger.info("path {}"+dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                List list = ExcelInviteUtils.readExcel(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Map<String, String> map = coinService.AnalyData(list);
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(map);
                return result.getString(result);
            } catch (Exception e) {
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
    public String queryByAdress(@RequestParam(value = "address") String address,
                            HttpServletResponse response) {
        logger.info("paramers: {}", address);
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            Questionnaire questionnaire = questionnaireService.selectBySelective(0, address);
            if(questionnaire!=null){
                if(questionnaire.getJob()!=null){
                    result.setCode(ResultEnum.MISSION_FAIL.getCode());
                    result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
                    return result.getString(result);
                }
            }
            coinService.getbalanceAll(address);
            result.setEntity("ok");
        } catch (JsonRpcClientException e) {
            logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        } catch (Throwable throwable) {
            return ResultUtil.universalThrowableReturn(logger , throwable, response, result);
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
    public String sendTotoken(@RequestParam(value = "address") String address , HttpServletResponse response, HttpSession session) {
        logger.info("parameters: {}", address);

        Result result = new Result();
        if (StringUtils.isBlank(address)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        String parse="[\"EHRG6AWSQLRAH2W5KNVEAZJJ3YKBTXT2\",[{address:\""+address+"\",amount:10000000}]]";
        JSONArray array = JSON.parseArray(parse);
        try {
            coinService.getbalanceAll(address);
            String sendResult = giftSetService.sendToken(array);
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(sendResult);
            return result.getString(result);
        } catch (JsonRpcClientException e) {
            logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            result.setEntity("地址错误.");
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        } catch (Throwable throwable) {
            return ResultUtil.universalThrowableReturn(logger , throwable, response, result);
        }
    }

    /**
     * Token查询余额
     * @param address
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryTokenBalance",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryTokenBalance(@RequestParam(value = "address") String address,
                            HttpServletResponse response) {
        logger.info("paramers: {}", address);
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        try {
            result.setEntity(giftSetService.getTokenbalance(address));
        } catch (JsonRpcClientException e) {
            logger.error("exception: {}", e);
            result.setCode(ResultEnum.MISSION_FAIL.getCode());
            result.setMsg(e.getMessage());
            return result.getString(result);
        } catch (Exception e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        } catch (Throwable throwable) {
            return ResultUtil.universalThrowableReturn(logger , throwable, response, result);
        }
        return result.getString(result);
    }

}
