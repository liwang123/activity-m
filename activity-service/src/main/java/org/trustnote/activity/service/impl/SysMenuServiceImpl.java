package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.api.SysMenuApi;
import org.trustnote.activity.common.example.SysMenuExample;
import org.trustnote.activity.common.pojo.SysMenu;
import org.trustnote.activity.common.pojo.SysMenuEx;
import org.trustnote.activity.service.iface.SysMenuService;
import org.trustnote.activity.skeleton.mybatis.mapper.SysMenuMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    private static final Logger logger = LogManager.getLogger(SysMenuServiceImpl.class);

    @Resource
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenuApi> queryAllSysMenuTree(final int roleId, final int userId) {
        final List<SysMenuApi> sysMenuApis = new ArrayList<>();
        final List<SysMenuEx> sysMenuExes = this.sysMenuMapper.selectRootMenu(roleId);
        if (CollectionUtils.isNotEmpty(sysMenuExes)) {
            final SysMenuApi sysMenuApi = SysMenuApi.builder()
                    .menuId(sysMenuExes.get(0).getId())
                    .menuName(sysMenuExes.get(0).getMenuName())
                    .menuShort(sysMenuExes.get(0).getMenuShort())
                    .children(this.recursiveChildMenu(sysMenuExes.get(0).getId(), roleId))
                    .checked(sysMenuExes.get(0).getChecked())
                    .build();
            sysMenuApis.add(sysMenuApi);
        }
        return sysMenuApis;
    }

    @Override
    public List<SysMenu> findMenuByUserId(final int userId) {
        return this.sysMenuMapper.findMenuByUserId(userId);
    }

    @Override
    public List<SysMenuApi> queryAllSysMenuTreeNonRole() {
        final List<SysMenuApi> sysMenuApis = new ArrayList<>();
        final SysMenuExample example = new SysMenuExample();
        final SysMenuExample.Criteria criteria = example.createCriteria();
        criteria.andPidIsNull();

        final List<SysMenu> sysMenus = this.sysMenuMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysMenus)) {
            final SysMenuApi sysMenuApi = SysMenuApi.builder()
                    .menuId(sysMenus.get(0).getId())
                    .menuName(sysMenus.get(0).getMenuName())
                    .menuShort(sysMenus.get(0).getMenuShort())
                    .menuUrl(sysMenus.get(0).getMenuUrl())
                    .children(this.recursiveChildMenu(sysMenus.get(0).getId()))
                    .checked(false)
                    .build();
            sysMenuApis.add(sysMenuApi);
        }
        return sysMenuApis;
    }

    private List<SysMenuApi> recursiveChildMenu(final int pid) {
        final List<SysMenuApi> sysMenuApis = new ArrayList<>();
        final SysMenuExample example = new SysMenuExample();
        final SysMenuExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);

        final List<SysMenu> sysMenus = this.sysMenuMapper.selectByExample(example);
        for (final SysMenu sysMenu : sysMenus) {
            final SysMenuApi sysMenuApi = SysMenuApi.builder()
                    .menuId(sysMenu.getId())
                    .menuName(sysMenu.getMenuName())
                    .menuShort(sysMenu.getMenuShort())
                    .menuUrl(sysMenu.getMenuUrl())
                    .children(this.recursiveChildMenu(sysMenu.getId()))
                    .checked(false)
                    .build();
            sysMenuApis.add(sysMenuApi);
        }
        return sysMenuApis;
    }

    private List<SysMenuApi> recursiveChildMenu(final int pid, final int roleId) {
        final List<SysMenuApi> sysMenuApis = new ArrayList<>();

        final List<SysMenuEx> sysMenuExes = this.sysMenuMapper.selectChildMenu(pid, roleId);
        for (final SysMenuEx sysMenuEx : sysMenuExes) {
            final SysMenuApi sysMenuApi = SysMenuApi.builder()
                    .menuId(sysMenuEx.getId())
                    .menuName(sysMenuEx.getMenuName())
                    .menuShort(sysMenuEx.getMenuShort())
                    .menuUrl(sysMenuEx.getMenuUrl())
                    .children(this.recursiveChildMenu(sysMenuEx.getId(), roleId))
                    .checked(sysMenuEx.getChecked())
                    .build();
            sysMenuApis.add(sysMenuApi);
        }
        return sysMenuApis;
    }


}
