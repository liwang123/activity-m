package org.trustnote.activity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.api.UserApii;
import org.trustnote.activity.common.pojo.SysMenu;
import org.trustnote.activity.service.iface.SysMenuService;
import org.trustnote.activity.service.iface.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl
 */
@Service
public class ZxlUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;
    @Resource
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        final UserApii user = this.userService.findUserByPhone(s);
        if (user != null) {
            final List<SysMenu> sysMenus = this.sysMenuService.findMenuByUserId(user.getId());
            final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (final SysMenu sysMenu : sysMenus) {
                if (sysMenu != null && sysMenu.getMenuUrl() != null) {
                    final GrantedAuthority grantedAuthority = new ZxlGrantedAuthority(sysMenu.getMenuUrl());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getPhone(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
