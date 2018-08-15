package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.SysUserRole;

public interface SysUserRoleService {
    SysUserRole querySysUserRoleByUserId(int userId);

    int removeRelation(int userId);
}
