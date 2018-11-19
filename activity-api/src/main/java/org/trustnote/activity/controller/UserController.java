package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.trustnote.activity.common.api.UserApii;
import org.trustnote.activity.common.constant.Globa;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.exception.ZxlException;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.common.pojo.UserApi;
import org.trustnote.activity.common.pojo.UserErrorLogin;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.UserService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.utils.CreateImageCode;
import org.trustnote.activity.utils.ErrorsUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;
import static org.trustnote.activity.controller.ResultUtil.universalZxlExceptionReturn;

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
    @Autowired
    private AuthenticationManager zxlAuthenticationManager;

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String logout(final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {
        final Result result = new Result();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        UserController.logger.info("logout sessionId" + session.getId());
        result.setCode(ResultEnum.OK.getCode());
        return result.getString(result);
    }

    //    @Frequency(name = "user-login", limit = 300, time = 60)
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(@RequestBody final String login, final HttpServletResponse response, final HttpSession session) {
        UserController.logger.info("parameter {}", login);
        final Result result = new Result();
        if (StringUtils.isBlank(login)) {
            return ResultUtil.universalBlankReturn(response, result);
        }

        try {
            final UserApi loginu = JSON.parseObject(login, UserApi.class);
            if (StringUtils.isBlank(loginu.getPhone())) {
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
            if (StringUtils.isBlank(loginu.getCode())) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("The code is mandatory."));
                return result.getString(result);
            }
            final String scode = (String) session.getAttribute("code");
            if (StringUtils.isBlank(scode)) {
                response.setStatus(HttpServletResponse.SC_OK);
                result.setCode(ResultEnum.NOTLOGIN.getCode());
                result.setMsg(ResultEnum.NOTLOGIN.appendMsg(""));
                return result.getString(result);
            }
            if (!loginu.getCode().toLowerCase().equals(scode.toLowerCase())) {
                UserController.logger.info("scode.toLowerCase(){}" + scode.toLowerCase() + "code" + loginu.getCode().toLowerCase());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg(ResultEnum.BAD_REQUEST.appendMsg("Incorrect verification code."));
                return result.getString(result);
            }
            final User userDao = new User();
            BeanUtils.copyProperties(loginu, userDao);
            //查询是否存在登陆错误信息
            UserErrorLogin userErrorLogin = UserController.LOG_MAP.get(loginu.getPhone());
            User user = null;
            final Calendar nowDate = Calendar.getInstance();
            final String dataStr = String.valueOf(nowDate.get(Calendar.YEAR)) + String.valueOf(nowDate.get(Calendar.MONTH) + 1) + String.valueOf(nowDate.get(Calendar.DAY_OF_MONTH));
            if (userErrorLogin == null) {
                user = this.userService.login(userDao);
            } else {
                final String loginTime = userErrorLogin.getLoginTime();
                final int loginNumber = userErrorLogin.getErrorNumber();
                if (!dataStr.equals(loginTime)) {
                    UserController.LOG_MAP.remove(loginu.getPhone());
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
                    UserController.LOG_MAP.put(loginu.getPhone(), userErrorLogin);
                }else {
                    final int loginNumber = userErrorLogin.getErrorNumber();
                    userErrorLogin.setErrorNumber(loginNumber + 1);
                    UserController.LOG_MAP.put(loginu.getPhone(), userErrorLogin);
                }
                userErrorLogin = UserController.LOG_MAP.get(loginu.getPhone());
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
                UserController.LOG_MAP.remove(loginu.getPhone());
//                final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDao.getPhone(), userDao
//                        .getPassword());
//                final Authentication authentication = this.zxlAuthenticationManager.authenticate(authRequest);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
                session.setAttribute(Globa.USER_SESSION_KEY, loginu.getPhone());
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
                result.setEntity(user.getPhone());
                this.userService.lastLogin(userDao.getPhone());
            }
            return result.getString(result);
        } catch (final JSONException e) {
            return ResultUtil.universalJSONExceptionReturn(UserController.logger, e, response, result);
        } catch (final Exception e) {
            return universalExceptionReturn(UserController.logger, e, response, result);
        }

    }

    //    @Frequency(name = "user-verification", limit = 300, time = 60)
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

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveUser(@Valid final UserApii userApii,
                           final Errors errors,
                           final HttpServletResponse response) {
        final Result result = new Result();
        final Result errorsResult = ErrorsUtils.errors(errors);
        if (errorsResult != null) {
            return result.getString(errorsResult);
        }

        try {
            final int insertStatus = this.userService.saveUser(userApii);
            if (insertStatus == -1) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg("当前用户已存在");
            } else if (insertStatus == 0) {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            } else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg("添加用户成功！");
            }
            result.setEntity(insertStatus);
        } catch (final ZxlException e) {
            return universalZxlExceptionReturn(UserController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/queryUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryUser(@RequestParam(value = "id") final int id, final HttpServletResponse response) {
        UserController.logger.info("paramer-> id: {}", id);
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.userService.queryUserById(id));
        } catch (final Exception e) {
            return universalExceptionReturn(UserController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String modifyUser(@Valid final UserApii userApii,
                             final Errors errors,
                             final HttpServletResponse response) {
        final Result result = new Result();
        final Result errorsResult = ErrorsUtils.errors(errors);
        if (errorsResult != null) {
            return result.getString(errorsResult);
        }

        try {
            final int insertStatus = this.userService.modifyUser(userApii);
            if (insertStatus == -1) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg("当前用户已存在");
            } else if (insertStatus == 0) {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            } else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg("修改用户成功！");
            }
            result.setEntity(insertStatus);
        } catch (final ZxlException e) {
            return universalZxlExceptionReturn(UserController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryList(@RequestParam(value = "limit", required = false, defaultValue = "10") final int limit,
                            @RequestParam(value = "offset", required = false, defaultValue = "0") final int offset,
                            final HttpServletResponse response) {
        UserController.logger.info("paramer-> limit: {} offset: {}", limit, offset);
        final Result result = new Result();
        final int pageNo;
        if (offset == 0) {
            pageNo = 1;
        } else {
            pageNo = offset / limit + 1;
        }
        final Page<User> page = new Page<>(pageNo, limit);
        boolean hasMore = false;

        try {
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(this.userService.queryUsers(page));
            result.setTotalCount(page.getTotalCount());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(UserController.logger, e, response, result);
        }
        result.setHasMore(hasMore);
        return result.getString(result);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String remove(@RequestParam(value = "id") final int id,
                         final HttpServletResponse response) {
        UserController.logger.info("paramer-> id: {}", id);
        final Result result = new Result();
        try {
            final int delStatus = this.userService.removeUser(id);
            if (delStatus > 0) {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg("删除用户成功");
            } else {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg("删除用户失败");
            }
            result.setEntity(delStatus);
        } catch (final ZxlException e) {
            return universalZxlExceptionReturn(UserController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @RequestMapping(value = "/current_username", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String currentUserName(final Principal principal) {
        final Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        result.setEntity(principal.getName());
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryUser(@RequestParam(value = "phone") final String phone, final HttpServletResponse response) {
        UserController.logger.info("paramer-> phone: {}", phone);
        final Result result = new Result();
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.userService.findUserByPhone(phone));
        } catch (final Exception e) {
            return universalExceptionReturn(UserController.logger, e, response, result);
        }
        return result.getString(result);
    }
}
