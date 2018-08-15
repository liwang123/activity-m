package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.SysMenuApi;
import org.trustnote.activity.common.pojo.SysMenu;

import java.util.List;

/**
 * @author zhuxl
 */
public interface SysMenuService {
    List<SysMenuApi> queryAllSysMenuTree(int roleId, int userId);

    List<SysMenu> findMenuByUserId(int userId);

    List<SysMenuApi> queryAllSysMenuTreeNonRole();
}
