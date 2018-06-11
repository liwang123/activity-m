package org.trustnote.activity.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.common.utils.Sending;
import org.trustnote.activity.common.utils.SendingPool;
import org.trustnote.activity.common.utils.Utils;
import org.trustnote.activity.stereotype.Frequency;
import org.trustnote.activity.utils.CreateImageCode;
import org.trustnote.activity.utils.ImageUtils.Captcha;
import org.trustnote.activity.utils.ImageUtils.GifCaptcha;
import org.trustnote.activity.utils.SmsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuxl 18-1-30
 * @since v0.3
 */
@Frequency(name = "sms", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/sms")
public class SmsController {
    private static final Logger logger = LogManager.getLogger(SmsController.class);


    @ResponseBody
    @RequestMapping(value = "/send", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String sendSms(@RequestParam("mobile") String mobile,@RequestParam String code, HttpSession session, HttpServletResponse response) {
        logger.info("parameters {} {}", mobile, code);
        Result result = new Result();
        if (StringUtils.isBlank(mobile)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        if (StringUtils.isBlank(code)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        logger.info("sessionId {}", session.getId());
        logger.info("icode {}", session.getAttribute("icode"));
        if (!code.equals(session.getAttribute("icode"))) {
            result.setCode(ResultEnum.UNAUTHORIZD.getCode());
            result.setMsg(ResultEnum.UNAUTHORIZD.getMsg());
            return result.getString(result);
        }
        String ss=String.valueOf((int)((Math.random()*9+1)*100000));
        logger.info("短信验证码 {}", ss);
        try {
            if(Utils.checkEmail(mobile)){
                SendingPool pool = SendingPool.getInstance();
                pool.addThread(new Sending(mobile, "TrustNote email verification code", createEmail(ss).toString()));
                result.setCode(ResultEnum.OK.getCode());
                result.setCode(ResultEnum.OK.getMsg());
                session.setAttribute("scode", ss);
                session.setAttribute("scode-phone", mobile);
                session.setMaxInactiveInterval(600);
                return result.getString(result);
            }
            Map<String,String> params=new HashMap<>();
            params.put("code",ss);
            SendSmsResponse sendSmsResponse = SmsUtils.sendSms(mobile,"SMS_123672496",params);
            logger.info("send_sms_code: {} send_sms_message: {}", sendSmsResponse.getCode(), sendSmsResponse.getMessage());
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                result.setCode(ResultEnum.OK.getCode());
                result.setCode(ResultEnum.OK.getMsg());
                session.setAttribute("scode", ss);
                session.setAttribute("scode-phone", mobile);
                session.setMaxInactiveInterval(600);
            }else {
                logger.info("send_sms_code faild"+sendSmsResponse.getCode());
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            }
            return result.getString(result);
        } catch (ClientException e) {
            return ResultUtil.universalExceptionReturn(logger , e, response, result);
        }
    }

    private static StringBuilder createEmail(String ss) {
        StringBuilder emailContent = new StringBuilder("<html> <head> <meta charset=\"UTF-8\"> <title></title> <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /> <style type=\"text/css\"> p{ margin: 0 ; padding: 0; line-height: 30px; margin-top: 10px; } </style> </head> <body> <div style=\"font-size: 18px;\"> <p>Dear TTT user!</p> <p><span style=\"color: #0095ff;\">"+ss+"</span> is your verification code.</p> <p>the code is valid for 10 minutes, please don't request new code before it expires!</p> <p style=\"text-align: right;\">TrustNote Foundation</p> </div> </body>");
        return emailContent;
    }

    @RequestMapping(value="getGifCode",method=RequestMethod.GET)
    public void getGifCode(HttpServletResponse response,HttpSession session){
        try {
        response.setContentType("image/png");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        CreateImageCode code = new CreateImageCode(100, 30, 4, 0);
        session.setAttribute("icode", code.getCode());
        logger.info("sessionId {}", session.getId());
        String ii = session.getAttribute("icode").toString();
            logger.info("session icode {}", ii);
           code.write(response.getOutputStream());
        } catch (Exception e) {
        }
    }
}