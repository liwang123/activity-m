package org.trustnote.activity.utils;

import org.trustnote.activity.common.utils.IPUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhuxl 18-2-3
 * @since v0.3
 */
public class IPFilter implements Filter {

    /** 默认限制时间（单位：ms） */
    private static final long LIMITED_TIME_MILLIS = 60 * 1000;

    /** 用户连续访问最高阀值，超过该值则认定为恶意操作的IP，进行限制 */
    private static final int LIMIT_NUMBER = 20;

    /** 用户访问最小安全时间，在该时间内如果访问次数大于阀值，则记录为恶意IP，否则视为正常访问 */
    private static final int MIN_SAFE_TIME = 5000;

    private FilterConfig config;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }
    /**
     * @Description 核心处理代码
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ServletContext context = config.getServletContext();
        // 获取限制IP存储器：存储被限制的IP信息
        ConcurrentHashMap<String, Long> limitedIpMap = (ConcurrentHashMap<String, Long>) context
                .getAttribute("limitedIpMap");
        // 获取用户IP
        String ip = IPUtil.getIp(request);
        // 判断是否是被限制的IP，如果是则跳到异常页面
        if(ip.contains("121.69.104.218")||ip.contains("13.231.226.243")){
            chain.doFilter(request, response);
        }
        if (isLimitedIP(limitedIpMap, ip)) {
            request.setAttribute("code", "400");
            request.setAttribute("msg", "This request is  illegal.");
            request.getRequestDispatcher("/user/rejson.htm").forward(request, response);
            return;
        }
        // 获取IP存储器
        ConcurrentHashMap<String, Long[]> ipMap = (ConcurrentHashMap<String, Long[]>) context.getAttribute("ipMap");

        // 判断存储器中是否存在当前IP，如果没有则为初次访问，初始化该ip
        // 如果存在当前ip，则验证当前ip的访问次数
        // 如果大于限制阀值，判断达到阀值的时间，如果不大于[用户访问最小安全时间]则视为恶意访问，跳转到异常页面
        if (ipMap.containsKey(ip)) {
            Long[] ipInfo = ipMap.get(ip);
            ipInfo[0] = ipInfo[0] + 1;
            if (ipInfo[0] > LIMIT_NUMBER) {
                Long ipAccessTime = ipInfo[1];
                Long currentTimeMillis = System.currentTimeMillis();
                // 限制时间内
                if (currentTimeMillis - ipAccessTime <= MIN_SAFE_TIME) {
                    limitedIpMap.put(ip, currentTimeMillis + LIMITED_TIME_MILLIS);
                    request.setAttribute("code", "400");
                    request.setAttribute("msg", "This request is illegal");
                    request.getRequestDispatcher("/user/rejson.htm").forward(request, response);
                    return;

                } else {
                    initIpVisitsNumber(ipMap, ip);
                }
            }
        } else {
            initIpVisitsNumber(ipMap, ip);
        }
        context.setAttribute("ipMap", ipMap);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * @Description 跳转页面
     * @author zhangyd
     * @date 2016年8月17日 下午5:58:43
     * @param request
     * @param response
     * @param remainingTime
     *            剩余限制时间
     * @throws ServletException
     * @throws IOException
     */
    private void forward(HttpServletRequest request, HttpServletResponse response, long remainingTime)
            throws ServletException, IOException {
        request.setAttribute("remainingTime", remainingTime);
        request.getRequestDispatcher("/error/overLimitIP").forward(request, response);
    }

    /**
     * @Description 是否是被限制的IP
     * @author zhangyd
     * @date 2016年8月8日 下午5:39:17
     * @param limitedIpMap
     * @param ip
     * @return true : 被限制 | false : 正常
     */
    private boolean isLimitedIP(ConcurrentHashMap<String, Long> limitedIpMap, String ip) {
        if (limitedIpMap == null || limitedIpMap.isEmpty() || ip == null) {
            // 没有被限制
            return false;
        }
        return limitedIpMap.containsKey(ip);
    }

    /**
     * 初始化用户访问次数和访问时间
     *
     * @author zhangyd
     * @date 2016年7月29日 上午10:01:39
     * @param ipMap
     * @param ip
     */
    private void initIpVisitsNumber(ConcurrentHashMap<String, Long[]> ipMap, String ip) {
        Long[] ipInfo = new Long[2];
        ipInfo[0] = 0L;// 访问次数
        ipInfo[1] = System.currentTimeMillis();// 初次访问时间
        ipMap.put(ip, ipInfo);
    }
}
