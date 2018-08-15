package org.trustnote.activity.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.trustnote.activity.common.api.SysMenuApi;
import org.trustnote.activity.common.api.UserApii;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.SysRoleMenu;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.SysMenuService;
import org.trustnote.activity.service.iface.SysRoleMenuService;
import org.trustnote.activity.service.iface.UserService;
import org.trustnote.activity.stereotype.Frequency;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "menu", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/menu")
public class SysMenuController {
    private static final Logger logger = LogManager.getLogger(SysMenuController.class);

    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getAllMenuByRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryMenu(@RequestParam(value = "roleId") final Integer roleId, final HttpServletResponse response) {
        final Result result = new Result();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserApii userApii = this.userService.findUserByPhone(authentication.getName());
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            final List<SysRoleMenu> sysRoleMenus = this.sysRoleMenuService.querySysRoleMenuByRoleId(roleId);
            if (CollectionUtils.isNotEmpty(sysRoleMenus)) {
                result.setEntity(this.sysMenuService.queryAllSysMenuTree(roleId, userApii.getId()));
            } else {
                result.setEntity(this.sysMenuService.queryAllSysMenuTreeNonRole());
            }
        } catch (final Exception e) {
            return universalExceptionReturn(SysMenuController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "authorization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveRoleMenu(@RequestBody final String menuApi, @RequestParam(value = "roleId") final Integer roleId, final HttpServletResponse response) {
        final Result result = new Result();
        if (StringUtils.isBlank(menuApi)) {
            return ResultUtil.universalBlankReturn(response, result);
        }
        try {
            final SysMenuApi sysMenuApi = JSON.parseObject(menuApi, SysMenuApi.class);
            final int saveStatus = this.sysRoleMenuService.saveRoleMenu(sysMenuApi, roleId);
            if (saveStatus == 1) {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg(ResultEnum.OK.getMsg());
            } else {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            }
            result.setEntity(saveStatus);
        } catch (final Exception e) {
            return universalExceptionReturn(SysMenuController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "getCurrentMenu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getMenuByRole(final HttpServletResponse response) {
        final Result result = new Result();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserApii userApii = this.userService.findUserByPhone(authentication.getName());
        try {
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
            result.setEntity(this.sysMenuService.findMenuByUserId(userApii.getId()));
        } catch (final Exception e) {
            return universalExceptionReturn(SysMenuController.logger, e, response, result);
        }
        return result.getString(result);
    }


}
