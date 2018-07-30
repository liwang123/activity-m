package org.trustnote.activity.common.utils;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.trustnote.activity.common.intercpter.OkhttpIntercepter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class OkHttpUtils {

    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(OkHttpUtils.httpLoggingInterceptor)
            .addInterceptor(new OkhttpIntercepter(3))
            .build();

    private static String executeCall(final Request request) {
        Response response = null;
        try {
            response = OkHttpUtils.OK_HTTP_CLIENT
                    .newCall(request)
                    .execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (final IOException e) {
            return null;
        }
        return null;
    }

    public static String get(final String url, final Map<String, ? extends Object> paramMap, final HttpServletRequest httpServletRequest) {
        final HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) {
            throw new RuntimeException("invalid url");
        }
        final HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
        if (paramMap != null && !paramMap.isEmpty()) {
            for (final Map.Entry<String, ?> entry : paramMap.entrySet()) {
                httpUrlBuilder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        String userAgent = "";
        if (httpServletRequest != null) {
            userAgent = StringUtils.defaultString(httpServletRequest.getHeader("User-Agent"), "");
        }

        final Request request = new Request.Builder()
                .header("referer", "https://trustnote.org")
                .header("User-Agent", userAgent)
                .url(httpUrlBuilder.build().url())
                .get()
                .build();
        return OkHttpUtils.executeCall(request);
    }

    public static String post(final String url, final Map<String, ? extends Object> paramMap,
                              final HttpServletRequest httpServletRequest) {

        final FormBody.Builder formBuilder = new FormBody.Builder();

        for (final Map.Entry<String, ? extends Object> entry : paramMap.entrySet()) {
            formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        String userAgent = "";
        if (httpServletRequest != null) {
            userAgent = StringUtils.defaultString(httpServletRequest.getHeader("User-Agent"), "");
        }

        final Request request = new Request.Builder()
                .header("referer", "https://trustnote.org")
                .header("User-Agent", userAgent)
                .url(url)
                .post(formBuilder.build())
                .build();
        return OkHttpUtils.executeCall(request);
    }

    public static String rpcRequestBodyPost(final String url, final Map<String, ? extends Object> paramMap) {
        final RequestBody body = RequestBody.create(OkHttpUtils.JSON_TYPE, JSON.toJSONString(paramMap));
        final Request request = new Request.Builder()
                .header("referer", "https://trustnote.org")
                .url(url)
                .post(body)
                .build();
        return OkHttpUtils.executeCall(request);
    }

    public static String get(final String url, final Map<String, ?> paramMap) {
        int retryNum = 1;
        String result = null;
        while (retryNum <= 3) {
            if (result == null) {
                result = OkHttpUtils.get(url, paramMap, null);
            } else {
                return result;
            }
            retryNum++;
        }
        return result;
    }

    public static String post(final String url, final Map<String, ?> paramMap) {
        int retryNum = 1;
        String result = null;
        while (retryNum <= 3) {
            if (result == null) {
                result = OkHttpUtils.post(url, paramMap, null);
            } else {
                return result;
            }
            retryNum++;
        }
        return result;
    }


}
