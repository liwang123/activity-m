package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.SysMenuApi;
import org.trustnote.activity.common.exception.ZxlException;
import org.trustnote.activity.common.pojo.SysRoleMenu;

import java.util.List;

/**
 * @author zhuxl
 */
public interface SysRoleMenuService {
    int saveRoleMenu(SysMenuApi sysMenuApi, int roleId) throws ZxlException;

    int deleteSysRoleMenuByRoleId(Integer roleId);

    List<SysRoleMenu> querySysRoleMenuByRoleId(Integer roleId);
}
