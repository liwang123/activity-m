package org.trustnote.activity.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.trustnote.activity.common.constant.Globa;
import org.trustnote.activity.common.enume.ResultEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhuxl 18-1-8
 * @since v0.3
 */
public class SecuritySSOInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Object o) throws Exception {
        final HttpSession session = httpServletRequest.getSession();
        final String username = (String) session.getAttribute(Globa.USER_SESSION_KEY);
        if (username != null) {
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
