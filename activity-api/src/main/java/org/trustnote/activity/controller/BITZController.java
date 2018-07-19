package org.trustnote.activity.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/expend")
public class BITZController {

    @RequestMapping(value = "/query-order", method = RequestMethod.GET)
    public String get() throws IOException {
        final String url = "https://api.bit-z.com/api_v1/ticker?coin=ttt_btc";
        final HttpGet request = new HttpGet(url);
        // 获取当前客户端对象
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final CloseableHttpResponse httpResponse = httpClient.execute(request);
        final String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        final JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        return jsonObject.toString();
    }
}
