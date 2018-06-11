package org.trustnote.activity.utils;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhuxl 18-2-3
 * @since v0.3
 */
public class RefererFilter implements Filter {
    private Bucket createNewBucket() {
        long overdraft = 100;//桶大小
        Refill refill = Refill.smooth(100, Duration.ofSeconds(1));//每秒1000次访问
        Bandwidth limit = Bandwidth.classic(overdraft, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String requestHeader = httpServletRequest.getHeader("referer");
        if (StringUtils.isBlank(requestHeader)) {
            httpServletRequest.setAttribute("code", "400");
            httpServletRequest.setAttribute("msg", "This request is not authorized.");
            httpServletRequest.getRequestDispatcher("/user/rejson.htm").forward(httpServletRequest, httpServletResponse);
            return;
        }
        if (requestHeader.indexOf("trustnote.org") > -1
                || requestHeader.indexOf("graph2note.com") > -1
                || requestHeader.indexOf("10.10.10") > -1
                || requestHeader.indexOf("localhost") > -1) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime stop = LocalDateTime.of(2218, 2, 9, 18, 0, 0);
            String uri = httpServletRequest.getRequestURI();

            if (uri.indexOf("transaction/save.htm") > -1 || uri.indexOf("sms") > -1) {
                if (now.isAfter(stop)) {
                    httpServletRequest.setAttribute("code", "400");
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

        HttpSession session = httpServletRequest.getSession(true);
        Bucket bucket = (Bucket) session.getAttribute("throttler-" + "8hgiuyytrrr");
        if (bucket == null) {
            bucket = createNewBucket();
            session.setAttribute("throttler-" + "8hgiuyytrrr", bucket);
        }
        // tryConsume returns false immediately if no tokens available with the bucket
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // limit is exceeded
            httpServletResponse.setContentType("text/plain");
            httpServletResponse.setStatus(429);
            httpServletResponse.getWriter().append("Too many requests");
        }
    }

    @Override
    public void destroy() {

    }
}
