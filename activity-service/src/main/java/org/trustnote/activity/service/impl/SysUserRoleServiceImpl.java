package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.SysUserRoleExample;
import org.trustnote.activity.common.pojo.SysUserRole;
import org.trustnote.activity.service.iface.SysUserRoleService;
import org.trustnote.activity.skeleton.mybatis.mapper.SysUserRoleMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserRole querySysUserRoleByUserId(final int userId) {
        final SysUserRoleExample example = new SysUserRoleExample();
        final SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        final List<SysUserRole> sysUserRoles = this.sysUserRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysUserRoles)) {
            return sysUserRoles.get(0);
        }
        return null;
    }

    @Override
    public int removeRelation(final int userId) {
        final SysUserRoleExample example = new SysUserRoleExample();
        final SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return this.sysUserRoleMapper.deleteByExample(example);
    }
}
