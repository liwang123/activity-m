package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.SysRoleExample;
import org.trustnote.activity.common.pojo.SysRole;
import org.trustnote.activity.service.iface.SysRoleService;
import org.trustnote.activity.skeleton.mybatis.mapper.SysRoleMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> queryRoles() {
        final SysRoleExample example = new SysRoleExample();
        final SysRoleExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("crt_time DESC");
        return this.sysRoleMapper.selectByExample(example);
    }

    @Override
    public List<SysRole> queryAvailableRoles() {
        final SysRoleExample example = new SysRoleExample();
        final SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(0);
        example.setOrderByClause("upt_time");
        return this.sysRoleMapper.selectByExample(example);
    }

    @Override
    public int saveRole(final SysRole sysRole) {
        final SysRole hasSysRole = this.queryRoleByRoleName(sysRole.getRoleName());
        if (hasSysRole != null) {
            return -1;
        }
        return this.sysRoleMapper.insertSelective(sysRole);
    }

    @Override
    public int modifyRole(final SysRole sysRole) {
        final SysRole hasSysRole = this.queryRoleByRoleNameNotContainsOneself(sysRole.getRoleName(), sysRole.getId());
        if (hasSysRole != null) {
            return -1;
        }
        return this.sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public SysRole queryRoleByRoleName(final String roleName) {
        final SysRoleExample example = new SysRoleExample();
        final SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        final List<SysRole> sysRoles = this.sysRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysRoles)) {
            return sysRoles.get(0);
        }
        return null;
    }

    @Override
    public SysRole queryRoleByRoleNameNotContainsOneself(final String roleName, final int id) {
        final SysRoleExample example = new SysRoleExample();
        final SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        criteria.andIdNotEqualTo(id);
        final List<SysRole> sysRoles = this.sysRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysRoles)) {
            return sysRoles.get(0);
        }
        return null;
    }

    @Override
    public int modifyState(final int id, final int state) {
        final SysRole sysRole = SysRole.builder()
                .id(id)
                .state(state)
                .build();
        return this.sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }
}
