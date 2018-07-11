package org.trustnote.activity.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.trustnote.activity.common.enume.ResultEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author zhuxl 18-2-3
 * @since v0.3
 */
public class RefererFilter implements Filter {

    private WebApplicationContext webApplicationContext;
    private RefererUtil refererUtil;

    @Override
    public void init(final FilterConfig filterConfig) {
        final ServletContext context = filterConfig.getServletContext();
        this.webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        this.refererUtil = this.webApplicationContext.getBean(RefererUtil.class);
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        final String requestHeader = httpServletRequest.getHeader("referer");
        if (StringUtils.isBlank(requestHeader)) {
            httpServletRequest.setAttribute("code", ResultEnum.BAD_REQUEST.getCode());
            httpServletRequest.setAttribute("msg", "This request is not authorized.");
            httpServletRequest.getRequestDispatcher("/user/rejson.htm").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if (StringUtils.isBlank(this.refererUtil.referer)) {
            httpServletRequest.setAttribute("code", ResultEnum.BAD_REQUEST.getCode());
            httpServletRequest.setAttribute("msg", "This request is not authorized.");
            httpServletRequest.getRequestDispatcher("/user/rejson.htm")
                    .forward(httpServletRequest, httpServletResponse);
            return;
        }

        if (this.refererUtil.referer.contains(requestHeader)) {
            final LocalDateTime now = LocalDateTime.now();
            final LocalDateTime stop = LocalDateTime.of(2218, 2, 9, 18, 0, 0);
            final String uri = httpServletRequest.getRequestURI();

            if (uri.indexOf("transaction/save.htm") > -1 || uri.indexOf("sms") > -1) {
                if (now.isAfter(stop)) {
                    httpServletRequest.setAttribute("code", ResultEnum.BAD_REQUEST.getCode());
                    httpServletRequest.setAttribute("msg", "This request is not authorized.");
                    httpServletRequest.getRequestDispatcher("/user/rejson.htm").forward(httpServletRequest, httpServletResponse);
                    return;
                }
            }
        }else {
            httpServletRequest.setAttribute("code", "400");
            httpServletRequest.setAttribute("msg", "This request is not authorized.");
            httpServletRequest.getRequestDispatcher("/user/rejson.htm").forward(httpServletRequest, httpServletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
