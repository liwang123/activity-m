package org.trustnote.activity.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.enume.ResultEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuxl
 */
@Service
public class ZxlAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AuthenticationException e) throws IOException, ServletException {
        httpServletRequest.setAttribute("code", ResultEnum.NOTLOGIN.getCode());
        httpServletRequest.setAttribute("msg", ResultEnum.NOTLOGIN.getMsg());
        httpServletRequest.getRequestDispatcher("/user/rejson.htm").forward(httpServletRequest, httpServletResponse);
    }
}
