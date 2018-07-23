package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trustnote.activity.common.constant.Globa;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.common.pojo.UserApi;
import org.trustnote.activity.common.pojo.UserErrorLogin;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.UserService;
import org.trustnote.activity.stereotype.Frequency;
import org.trustnote.activity.utils.CreateImageCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuxl 17-12-26
 * @since v0.3
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private static final Map<String, UserErrorLogin> LOG_MAP = new HashMap<>();
    @Resource
    private UserService userService;


    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String logout(final HttpSession session) {
        final Result result = new Result();
        session.removeAttribute(Globa.USER_SESSION_KEY);
        UserController.logger.info("logout sessionId" + session.getId());
        result.setCode(ResultEnum.OK.getCode());
        return result.getString(result);
    }

    @Frequency(name = "user-login", limit = 300, time = 60)
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(@RequestBody final String login, final HttpServletResponse response, final HttpSession session) {
        UserController.logger.info("parameter {}", login);
        final Result result = new Result();
        if (StringUtils.isBlank(login)) {
            return ResultUtil.universalBlankReturn(response, result);
        }

        try {
            final UserApi loginu = JSON.parseObject(login, UserApi.class);
            if (StringUtils.isBlank(loginu.getUsername())) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The username is mandatory."));
                return result.getString(result);
            }

            if (StringUtils.isBlank(loginu.getPassword())) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The password is mandatory."));
                return result.getString(result);
            }
//            if (StringUtils.isBlank(loginu.getCode())) {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                result.setCode(ResultEnum.BAD_REQUEST.getCode());
//                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The code is mandatory."));
//                return result.getString(result);
//            }
//            final String scode = (String) session.getAttribute("code");
//            if (StringUtils.isBlank(scode)) {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                result.setCode(ResultEnum.BAD_REQUEST.getCode());
//                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg(""));
//                return result.getString(result);
//            }
//            if (!loginu.getCode().toLowerCase().equals(scode.toLowerCase())) {
//                UserController.logger.info("scode.toLowerCase(){}" + scode.toLowerCase() + "code" + loginu.getCode().toLowerCase());
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                result.setCode(ResultEnum.BAD_REQUEST.getCode());
//                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("Incorrect verification code."));
//                return result.getString(result);
//            }
            final User userDao = new User();
            BeanUtils.copyProperties(userDao, loginu);
            //查询是否存在登陆错误信息
            UserErrorLogin userErrorLogin = UserController.LOG_MAP.get(loginu.getUsername());
            User user = null;
            final Calendar nowDate = Calendar.getInstance();
            final String dataStr = String.valueOf(nowDate.get(Calendar.YEAR)) + String.valueOf(nowDate.get(Calendar.MONTH) + 1) + String.valueOf(nowDate.get(Calendar.DAY_OF_MONTH));
            if (userErrorLogin == null) {
                user = this.userService.login(userDao);
            } else {
                final String loginTime = userErrorLogin.getLoginTime();
                final int loginNumber = userErrorLogin.getErrorNumber();
                if (!dataStr.equals(loginTime)) {
                    UserController.LOG_MAP.remove(loginu.getUsername());
                    user = this.userService.login(userDao);
                }else {
                    if (loginNumber < 3) {
                        user = this.userService.login(userDao);
                    }else {
                        user = null;
                    }
                }
            }
            if (user == null) {
                if (null == userErrorLogin) {
                    userErrorLogin = new UserErrorLogin();
                    userErrorLogin.setErrorNumber(1);
                    userErrorLogin.setLoginTime(dataStr);
                    UserController.LOG_MAP.put(loginu.getUsername(), userErrorLogin);
                }else {
                    final int loginNumber = userErrorLogin.getErrorNumber();
                    userErrorLogin.setErrorNumber(loginNumber + 1);
                    UserController.LOG_MAP.put(loginu.getUsername(), userErrorLogin);
                }
                userErrorLogin = UserController.LOG_MAP.get(loginu.getUsername());
                if (userErrorLogin.getErrorNumber() < 4) {
                    UserController.logger.warn("login fail reason {}", login);
                    result.setCode(ResultEnum.UNAUTHORIZD.getCode());
                    result.setMsg(ResultEnum.UNAUTHORIZD.appendMsg("UserName Or PassWord Is Error."));
                }else {
                    UserController.logger.warn("login fail reason {}", login);
                    result.setCode(ResultEnum.UNAUTHORIZD.getCode());
                    result.setMsg(ResultEnum.UNAUTHORIZD.appendMsg("The login frequency is limited, please continue tomorrow."));
                }
            }
            if (user != null) {
                UserController.LOG_MAP.remove(loginu.getUsername());
                session.setAttribute(Globa.USER_SESSION_KEY, user.getUsername());
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(user.getUsername());
            }
            return result.getString(result);
        } catch (final JSONException e) {
            return ResultUtil.universalJSONExceptionReturn(UserController.logger, e, response, result);
        } catch (final Exception e) {
            return ResultUtil.universalExceptionReturn(UserController.logger, e, response, result);
        }

    }

    @Frequency(name = "user-verification", limit = 300, time = 60)
    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    public void getValidate(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {
        response.setContentType("image/png");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        final CreateImageCode code = new CreateImageCode(100, 30, 4, 0);
        UserController.logger.info("code {}", code.getCode());
        session.setAttribute("code", code.getCode());
        UserController.logger.info("sessionId {}", session.getId());
        try {
            code.write(response.getOutputStream());
        } catch (final IOException e) {
            UserController.logger.error("exception {}", e);
        }
    }


    @RequestMapping(value = "/rejson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String resp(final HttpServletRequest request) {
        return this.respSame(request);
    }

    @RequestMapping(value = "/rejson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String respPost(final HttpServletRequest request) {
        return this.respSame(request);
    }

    private String respSame(final HttpServletRequest request) {
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        if (request.getAttribute("code") != null) {
            result.setCode(request.getAttribute("code").toString());
        }
        if (request.getAttribute("msg") != null) {
            result.setMsg(request.getAttribute("msg").toString());
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }
}
