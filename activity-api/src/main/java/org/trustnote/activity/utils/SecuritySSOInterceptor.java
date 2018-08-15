package org.trustnote.activity.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.trustnote.activity.common.enume.ResultEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuxl 18-1-8
 * @since v0.3
 */
public class SecuritySSOInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Object o) throws Exception {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() != null) {
            return true;
        }else {
            httpServletRequest.setAttribute("code", ResultEnum.NOTLOGIN.getCode());
            httpServletRequest.setAttribute("msg", ResultEnum.NOTLOGIN.getMsg());
            httpServletRequest.getRequestDispatcher("/user/rejson.htm").forward(httpServletRequest, httpServletResponse);
            return false;
        }
    }

    @Override
    public void postHandle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Object o, final ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Object o, final Exception e) throws Exception {

    }
}
