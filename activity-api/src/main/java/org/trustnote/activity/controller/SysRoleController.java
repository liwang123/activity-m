package org.trustnote.activity.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trustnote.activity.common.enume.ResultEnum;
import org.trustnote.activity.common.pojo.SysRole;
import org.trustnote.activity.common.utils.Result;
import org.trustnote.activity.service.iface.SysRoleService;
import org.trustnote.activity.skeleton.mybatis.orm.Page;
import org.trustnote.activity.stereotype.Frequency;
import org.trustnote.activity.utils.ErrorsUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.trustnote.activity.controller.ResultUtil.universalExceptionReturn;

/**
 * @author zhuxl
 */
@Frequency(name = "role", limit = 300, time = 60)
@Controller
@RequestMapping(value = "/role")
public class SysRoleController {
    private static final Logger logger = LogManager.getLogger(SysRoleController.class);

    @Resource
    private SysRoleService sysRoleService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveRole(@Valid final SysRole sysRole,
                           final Errors errors,
                           final HttpServletResponse response) {
        final Result result = new Result();
        final Result errorsResult = ErrorsUtils.errors(errors);
        if (errorsResult != null) {
            return result.getString(errorsResult);
        }
        try {
            final int saveStatus = this.sysRoleService.saveRole(sysRole);
            if (saveStatus == -1) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg("当前角色已存在");
            } else if (saveStatus == 0) {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            } else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg("添加角色成功！");
            }
            result.setEntity(saveStatus);
        } catch (final Exception e) {
            return universalExceptionReturn(SysRoleController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String modify(@Valid final SysRole sysRole,
                         final Errors errors,
                         final HttpServletResponse response) {
        final Result result = new Result();
        final Result errorsResult = ErrorsUtils.errors(errors);
        if (errorsResult != null) {
            return result.getString(errorsResult);
        }
        try {
            final int modifyStatus = this.sysRoleService.modifyRole(sysRole);
            if (modifyStatus == -1) {
                result.setCode(ResultEnum.BAD_REQUEST.getCode());
                result.setMsg("当前角色已存在");
            } else if (modifyStatus == 0) {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            } else {
                result.setCode(ResultEnum.OK.getCode());
                result.setMsg("修改角色成功！");
            }
        } catch (final Exception e) {
            return universalExceptionReturn(SysRoleController.logger, e, response, result);
        }
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String queryList(@RequestParam(value = "limit", required = false, defaultValue = "10") final int limit,
                            @RequestParam(value = "offset", required = false, defaultValue = "0") final int offset,
                            final HttpServletResponse response) {
        SysRoleController.logger.info("paramer-> limit: {} offset: {}", limit, offset);
        final Result result = new Result();
        final int pageNo;
        if (offset == 0) {
            pageNo = 1;
        } else {
            pageNo = offset / limit + 1;
        }
        final Page<SysRole> page = new Page<>(pageNo, limit);
        boolean hasMore = false;
        try {
            if (null != page && pageNo < page.getTotalPages()) {
                hasMore = true;
            }
            result.setEntity(this.sysRoleService.queryRoles());
            result.setTotalCount(page.getTotalCount());
            result.setCode(ResultEnum.OK.getCode());
            result.setMsg(ResultEnum.OK.getMsg());
        } catch (final Exception e) {
            return universalExceptionReturn(SysRoleController.logger, e, response, result);
        }
        result.setHasMore(hasMore);
        return result.getString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/switch_state", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String changeState(@RequestParam(value = "id") final int id,
                              @RequestParam(value = "state") final int state,
                              final HttpServletResponse response) {
        SysRoleController.logger.info("paramer-> id: {} state: {}");
        final Result result = new Result();
        try {
            final int modifyStatus = this.sysRoleService.modifyState(id, state);
            if (modifyStatus > 0) {
                result.setCode(ResultEnum.OK.getCode());
                if (state == 0) {
                    result.setMsg("启用角色成功");
                } else if (state == 1) {
                    result.setMsg("禁用角色成功");
                }
            } else {
                result.setCode(ResultEnum.MISSION_FAIL.getCode());
                result.setMsg(ResultEnum.MISSION_FAIL.getMsg());
            }
            result.setEntity(modifyStatus);
        } catch (final Exception e) {
            return universalExceptionReturn(SysRoleController.logger, e, response, result);
        }
        return result.getString(result);
    }
}
