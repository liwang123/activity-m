package org.trustnote.activity.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.trustnote.activity.stereotype.Frequency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author zhuxl
 */
public class FrequencyHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(FrequencyHandlerInterceptor.class);
    private static final int MAX_BASE_STATION_SIZE = 100000;
    private static Map<String, FrequencyStruct> BASE_STATION = new HashMap<String, FrequencyStruct>(MAX_BASE_STATION_SIZE);
    private static final float SCALE = 0.75F;
    private static final int MAX_CLEANUP_COUNT = 3;
    private static final int CLEANUP_INTERVAL = 1000;
    private Object syncRoot = new Object();
    private int cleanupCount = 0;
    private static final String IP_UNKNOWN = "unknown";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Frequency methodFrequency = ((HandlerMethod) handler).getMethodAnnotation(Frequency.class);
        Frequency classFrequency = ((HandlerMethod) handler).getBean().getClass().getAnnotation(Frequency.class);

        boolean going = true;
        if (classFrequency != null) {
            going = handleFrequency(request, response, classFrequency);
        }

        if (going && methodFrequency != null) {
            going = handleFrequency(request, response, methodFrequency);
        }
        return going;
    }

    private boolean handleFrequency(HttpServletRequest request, HttpServletResponse response, Frequency frequency) throws ServletException, IOException {

        boolean going = true;
        if (frequency == null) {
            return going;
        }

        String name = frequency.name();
        int limit = frequency.limit();
        int time = frequency.time();
        if (time == 0 || limit == 0) {
            going = false;
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return going;
        }

        long currentTimeMilles = System.currentTimeMillis() / 1000;

        String ip = getRemoteIp(request);
        String key = ip + "_" + name;
        FrequencyStruct frequencyStruct = BASE_STATION.get(key);

        if (frequencyStruct == null) {

            frequencyStruct = new FrequencyStruct();
            frequencyStruct.uniqueKey = name;
            frequencyStruct.start = frequencyStruct.end = currentTimeMilles;
            frequencyStruct.limit = limit;
            frequencyStruct.time = time;
            frequencyStruct.accessPoints.add(currentTimeMilles);

            synchronized (syncRoot) {
                BASE_STATION.put(key, frequencyStruct);
            }
            if (BASE_STATION.size() > MAX_BASE_STATION_SIZE * SCALE) {
                cleanup(currentTimeMilles);
            }
        } else {

            frequencyStruct.end = currentTimeMilles;
            frequencyStruct.accessPoints.add(currentTimeMilles);
        }

        //时间是否有效
        if (frequencyStruct.end - frequencyStruct.start >= time) {

            if (logger.isDebugEnabled()) {
                logger.debug("frequency struct be out of date, struct will be reset., struct: {}", frequencyStruct.toString());
            }
            frequencyStruct.reset(currentTimeMilles);
        } else {

            int count = frequencyStruct.accessPoints.size();
            if (count > limit) {
                if (logger.isDebugEnabled()) {
                    logger.debug("key: {} too frequency. count: {}, limit: {}.", key, count, limit);
                }
                going = false;
                request.setAttribute("code", "400");
                request.setAttribute("msg", "The request is too frequent. Please try again later");
                request.getRequestDispatcher("/user/rejson.htm").forward(request, response);
            }
        }
        return going;
    }

    private void cleanup(long currentTimeMilles) {

        synchronized (syncRoot) {

            Iterator<String> it = BASE_STATION.keySet().iterator();
            while (it.hasNext()) {

                String key = it.next();
                FrequencyStruct struct = BASE_STATION.get(key);
                if ((currentTimeMilles - struct.end) > struct.time) {
                    it.remove();
                }
            }

            if ((MAX_BASE_STATION_SIZE - BASE_STATION.size()) > CLEANUP_INTERVAL) {
                cleanupCount = 0;
            } else {
                cleanupCount++;
            }

            if (cleanupCount > MAX_CLEANUP_COUNT) {
                randomCleanup(MAX_CLEANUP_COUNT);
            }
        }
    }

    /**
     * 随机淘汰count个key
     *
     * @param count
     */
    private void randomCleanup(int count) {
        //防止调用错误
        if (BASE_STATION.size() < MAX_BASE_STATION_SIZE * SCALE) {
            return;
        }

        Iterator<String> it = BASE_STATION.keySet().iterator();
        Random random = new Random();
        int tempCount = 0;

        while (it.hasNext()) {
            if (random.nextBoolean()) {
                it.remove();
                tempCount++;
                if (tempCount >= count) {
                    break;
                }
            }
        }
    }

    private String getRemoteIp(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;

    }
}
