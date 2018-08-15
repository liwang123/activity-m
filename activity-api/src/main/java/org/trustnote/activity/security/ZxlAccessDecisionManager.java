package org.trustnote.activity.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author zhuxl
 */
@Service
public class ZxlAccessDecisionManager implements AccessDecisionManager {
    private static final String LOGOUT_PATH = "/user/logout.htm";

    @Override
    public void decide(final Authentication authentication, final Object o, final Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        final HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        String url;
        if (this.matchers(ZxlAccessDecisionManager.LOGOUT_PATH, request)) {
            return;
        }
        for (final GrantedAuthority ga : authentication.getAuthorities()) {
            if (ga instanceof ZxlGrantedAuthority) {
                final ZxlGrantedAuthority zxlGrantedAuthority = (ZxlGrantedAuthority) ga;
                url = zxlGrantedAuthority.getUrl();
                if (this.matchers(url, request)) {
                    return;
                }
            } else if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {
                if (this.matchers("/index.jsp", request)
                        || this.matchers("/user/login.htm", request)
                        || this.matchers("/user/verification.htm", request)
                        || this.matchers("/user/rejson.htm", request)
                        || this.matchers("/exchange-order/*", request)
                        || this.matchers("/download/record.htm", request)
                        || this.matchers("/media/queryMedia.htm", request)
                        || this.matchers("/feedback/insert.htm", request)
                        || this.matchers("/explorerManager/*", request)
                        || this.matchers("/channel/queryOne.htm", request)
                        || this.matchers("/exchange/conversion.htm", request)
                        || this.matchers("/ann/show.htm", request)
                        || this.matchers("/ann/read.htm", request)
                        || this.matchers("/ann/showOne.htm", request)
                        || this.matchers("/sms/*", request)
                        || this.matchers("/financial/home.htm", request)
                        || this.matchers("/financial-benefits/push*", request)
                        || this.matchers("/financial-lockup/save.htm", request)
                        || this.matchers("/financial-lockup/all.htm", request)
                        || this.matchers("/financial-lockup/participate.htm", request)
                        || this.matchers("/financial-lockup/manual.htm", request)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("");
    }

    @Override
    public boolean supports(final ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return true;
    }

    private boolean matchers(final String url, final HttpServletRequest request) {
        final AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
