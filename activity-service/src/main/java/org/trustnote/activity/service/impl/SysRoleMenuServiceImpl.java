package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.trustnote.activity.common.api.SysMenuApi;
import org.trustnote.activity.common.example.SysRoleMenuExample;
import org.trustnote.activity.common.exception.ZxlException;
import org.trustnote.activity.common.pojo.SysRoleMenu;
import org.trustnote.activity.service.iface.SysRoleMenuService;
import org.trustnote.activity.skeleton.mybatis.mapper.SysRoleMenuMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    private static final Logger logger = LogManager.getLogger(SysRoleMenuServiceImpl.class);

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ZxlException.class)
    @Override
    public int saveRoleMenu(final SysMenuApi sysMenuApi, final int roleId) throws ZxlException {
        final List<SysRoleMenu> sysRoleMenus = this.querySysRoleMenuByRoleId(roleId);
        boolean isDel = false;
        if (CollectionUtils.isNotEmpty(sysRoleMenus)) {
            final int delStatus = this.deleteSysRoleMenuByRoleId(roleId);
            if (delStatus > 0) {
                isDel = true;
            }
        } else {
            isDel = true;
        }
        if (isDel) {
            //解析根节点
            final SysRoleMenu sysRoleMenu = SysRoleMenu.builder()
                    .menuId(sysMenuApi.getMenuId())
                    .roleId(roleId)
                    .build();
            if (sysMenuApi.getChecked()) {
                sysRoleMenu.setChecked(true);
            } else {
                sysRoleMenu.setChecked(false);
            }
            final int saveStatus = this.sysRoleMenuMapper.insertSelective(sysRoleMenu);
            if (saveStatus > 0) {
                this.saveChildren(sysMenuApi.getChildren(), roleId);
            } else {
                throw new ZxlException();
            }
        }

        return 1;
    }

    /**
     * 递归处理子节点
     *
     * @param childrens
     * @param roleId
     */
    private int saveChildren(final List<SysMenuApi> childrens, final int roleId) throws ZxlException {
        //解析子节点
        childrens.forEach(sysMenuApi -> {
            final SysRoleMenu sysRoleMenu = SysRoleMenu.builder()
                    .menuId(sysMenuApi.getMenuId())
                    .roleId(roleId)
                    .build();
            if (sysMenuApi.getChecked()) {
                sysRoleMenu.setChecked(true);
            } else {
                sysRoleMenu.setChecked(false);
            }
            final int saveStatus = this.sysRoleMenuMapper.insertSelective(sysRoleMenu);
            if (saveStatus > 0) {
                this.saveChildren(sysMenuApi.getChildren(), roleId);
            } else {
                throw new ZxlException();
            }
        });
        return 1;
    }

    /**
     * 根据roleId删除角色菜单关联关系
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteSysRoleMenuByRoleId(final Integer roleId) {
        final SysRoleMenuExample example = new SysRoleMenuExample();
        final SysRoleMenuExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return this.sysRoleMenuMapper.deleteByExample(example);
    }

    /**
     * 根据roleId 获取角色菜单信息
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysRoleMenu> querySysRoleMenuByRoleId(final Integer roleId) {
        final SysRoleMenuExample example = new SysRoleMenuExample();
        final SysRoleMenuExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return this.sysRoleMenuMapper.selectByExample(example);
    }
}
