package org.trustnote.activity.common.intercpter;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 〈okHttp重试拦截器〉
 *
 * @author WangYu
 * @create 2018/5/25
 * @since 1.0.0
 */
public class OkhttpIntercepter implements Interceptor {


    /**
     * 最大重试次数
     */
    public int maxRetry;
    /**
     * 假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
     */
    private int retryNum = 0;

    public OkhttpIntercepter(final int maxRetry) {
        this.maxRetry = maxRetry;
    }


    @Override
    public Response intercept(final Chain chain) throws IOException {

        final Request request = chain.request();
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && this.retryNum < this.maxRetry) {
            this.retryNum++;
            response = chain.proceed(request);
        }
        return response;
    }
}
