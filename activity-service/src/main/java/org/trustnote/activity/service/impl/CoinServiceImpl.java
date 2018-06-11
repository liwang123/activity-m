package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.jsonrpc.JsonRpcResult;
import org.trustnote.activity.common.jsonrpc.JsonRpcTotal;
import org.trustnote.activity.common.utils.RegexUtil;
import org.trustnote.activity.common.utils.TrustnoteRpcUtil;
import org.trustnote.activity.service.iface.CoinService;

import java.util.*;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class CoinServiceImpl implements CoinService {
    private static final Logger logger = LogManager.getLogger(CoinServiceImpl.class);
    @Value("${url}")
    private String url;

    @Override
    public JsonRpcResult getbalance(String address) throws Throwable {
        JsonRpcHttpClient client = TrustnoteRpcUtil.getClient(url);
        logger.info("client-url: {}", client.getServiceUrl());
        String[] params = new String[] {address};
        Map<String, String> headers = new HashMap<>();
        headers.put("id", "1");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        JsonRpcResult jsonRpcResult = client.invoke("getbalance", params, JsonRpcResult.class);
        logger.info("jsonRpcResult: {}", JSON.toJSONString(jsonRpcResult));
        return jsonRpcResult;
    }

    @Override
    public JsonRpcTotal getbalanceAll(String address) throws Throwable {
        JsonRpcHttpClient client = TrustnoteRpcUtil.getClient(url);
        logger.info("client-url: {}", client.getServiceUrl());
        String[] params = new String[] {address};
        Map<String, String> headers = new HashMap<>();
        headers.put("id", "2");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        JsonRpcTotal jsonRpcResult = client.invoke("getbalanceAll", params, JsonRpcTotal.class);
        logger.info("jsonRpcResult: {}", JSON.toJSONString(jsonRpcResult));
        return jsonRpcResult;
    }

    @Override
    public String sendToMultiAddress(JSONArray array) throws Throwable {
        JsonRpcHttpClient client = TrustnoteRpcUtil.getClient(url);
        logger.info("client-url: {}", client.getServiceUrl());
//        String[] params = new String[] {json};
        Map<String, String> headers = new HashMap<>();
        headers.put("id", "1");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        client.setConnectionTimeoutMillis(3000);
        String jsonRpcResult = client.invoke("sendtomultiaddress", array, String.class);
        logger.info("jsonRpcResult: {}", jsonRpcResult);
        return jsonRpcResult;
    }

    @Override
    public Map<String, String> AnalyData(List list) {
        Map<String, String> map = new LinkedHashMap<>();
        logger.info("map{}"+list.size());
        for(int i=0;i<list.size();i++){
            String[] str = (String[])list.get(i);
            String trim = str[0];
            if(StringUtils.isNotBlank(trim)){
            if(trim.length()>32){trim=str[0].substring(0,32);}
            boolean flag = RegexUtil.checkTTTAdress(trim);
            double num = Double.parseDouble(str[3]);
            if(num>0&&flag==true&&num<=5000) {map.put(trim,str[3]);}
            }
        }
        return map;
    }

}
