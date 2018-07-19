package org.trustnote.activity.task;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class BITZController {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void handleOrders() throws IOException {
        final String url = "https://api.bit-z.com/api_v1/ticker?coin=ttt_btc";
        final HttpGet request = new HttpGet(url);
        // 获取当前客户端对象
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("开始处理");
        final CloseableHttpResponse httpResponse = httpClient.execute(request);
        final String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        final JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        System.out.println("数据" + jsonObject.toJSONString());
    }


}
