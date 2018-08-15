package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.SysRole;

import java.util.List;

/**
 * @author zhuxl
 */
public interface SysRoleService {
    List<SysRole> queryRoles();

    List<SysRole> queryAvailableRoles();

    int saveRole(SysRole sysRole);

    int modifyRole(SysRole sysRole);

    SysRole queryRoleByRoleName(String roleName);

    SysRole queryRoleByRoleNameNotContainsOneself(String roleName, int id);

    int modifyState(int id, int state);
}
