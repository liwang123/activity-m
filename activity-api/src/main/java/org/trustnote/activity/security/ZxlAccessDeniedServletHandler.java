package org.trustnote.activity.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class ZxlAccessDeniedServletHandler implements AccessDeniedHandler {
    @Override
    public void handle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AccessDeniedException e) throws IOException, ServletException {
        httpServletRequest.setAttribute("code", ResultEnum.UNAUTHORIZD.getCode());
        httpServletRequest.setAttribute("msg", ResultEnum.UNAUTHORIZD.getMsg());
        httpServletRequest.getRequestDispatcher("/user/rejson.htm").forward(httpServletRequest, httpServletResponse);
    }
}
