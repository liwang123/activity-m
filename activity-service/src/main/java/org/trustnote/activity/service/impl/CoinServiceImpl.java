package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.jsonrpc.JsonRpcResult;
import org.trustnote.activity.common.jsonrpc.JsonRpcResultBase;
import org.trustnote.activity.common.jsonrpc.JsonRpcTotal;
import org.trustnote.activity.common.utils.RegexUtil;
import org.trustnote.activity.common.utils.TrustnoteRpcUtil;
import org.trustnote.activity.service.iface.CoinService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class CoinServiceImpl implements CoinService {
    private static final Logger logger = LogManager.getLogger(CoinServiceImpl.class);
    private static final String SMART_URL = "smart";
    @Value("${url}")
    private String url;
    @Value("${financial_url}")
    private String financialUrl;

    /**
     * 根据address获取余额
     *
     * @param address
     * @return
     * @throws Throwable
     */
    @Override
    public JsonRpcResult getbalance(final String address) throws Throwable {
        final JsonRpcHttpClient client = TrustnoteRpcUtil.getClient(this.url);
        CoinServiceImpl.logger.info("client-url: {}", client.getServiceUrl());
        final String[] params = new String[]{address};
        final Map<String, String> headers = new HashMap<>(2);
        headers.put("id", "1");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        final JsonRpcResult jsonRpcResult = client.invoke("getbalance", params, JsonRpcResult.class);
        CoinServiceImpl.logger.info("jsonRpcResult: {}", JSON.toJSONString(jsonRpcResult));
        return jsonRpcResult;
    }

    /**
     * 根据address获取余额　不限本headless地址
     *
     * @param address
     * @return
     * @throws Throwable
     */
    @Override
    public JsonRpcTotal getbalanceAll(final String address) throws Throwable {
        final JsonRpcHttpClient client = TrustnoteRpcUtil.getClient(this.url);
        CoinServiceImpl.logger.info("client-url: {}", client.getServiceUrl());
        final String[] params = new String[]{address};
        final Map<String, String> headers = new HashMap<>(2);
        headers.put("id", "2");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        final JsonRpcTotal jsonRpcResult = client.invoke("getbalanceAll", params, JsonRpcTotal.class);
        CoinServiceImpl.logger.info("jsonRpcResult: {}", JSON.toJSONString(jsonRpcResult));
        return jsonRpcResult;
    }

    /**
     * 获取所有余额
     *
     * @param code
     * @return
     * @throws Throwable
     */
    @Override
    public JsonRpcResult getMainBalance(final String code) throws Throwable {
        String clientUrl = this.url;
        if (CoinServiceImpl.SMART_URL.equals(code)) {
            clientUrl = this.financialUrl;
        }
        final JsonRpcHttpClient client = TrustnoteRpcUtil.getHeadless2Client(clientUrl);
        CoinServiceImpl.logger.info("client-url: {}", client.getServiceUrl());
        final String[] params = new String[]{""};
        final Map<String, String> headers = new HashMap<>(2);
        headers.put("id", "1");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        final JSONObject jsonObject = client.invoke("getmainbalance", params, JSONObject.class);
        final JsonRpcResult jsonRpcResult = new JsonRpcResult();

        final Object jsonObjectBase = jsonObject.get("base");
        final JsonRpcResultBase jsonRpcResultBase = JSON.parseObject(JSON.toJSONString(jsonObjectBase), JsonRpcResultBase.class);
        jsonRpcResult.setBase(jsonRpcResultBase);
        CoinServiceImpl.logger.info("jsonRpcResult: {}", JSON.toJSONString(jsonRpcResult));
        return jsonRpcResult;
    }

    /**
     * 发币
     *
     * @param array
     * @param code
     * @return
     * @throws Throwable
     */
    @Override
    public String sendToMultiAddress(final JSONArray array, final String code) throws Throwable {
        JsonRpcHttpClient client = TrustnoteRpcUtil.getClient(this.url);
        if (CoinServiceImpl.SMART_URL.equals(code)) {
            client = TrustnoteRpcUtil.getHeadless2Client(this.financialUrl);
        }

        CoinServiceImpl.logger.info("client-url: {}", client.getServiceUrl());
        final Map<String, String> headers = new HashMap<>(2);
        headers.put("id", "1");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        client.setConnectionTimeoutMillis(3000);
        final String jsonRpcResult = client.invoke("sendtomultiaddress", array, String.class);
        CoinServiceImpl.logger.info("jsonRpcResult: {}", jsonRpcResult);
        return jsonRpcResult;
    }

    @Override
    public Map<String, String> AnalyData(final List list) {
        final Map<String, String> map = new LinkedHashMap<>();
        CoinServiceImpl.logger.info("map{}" + list.size());
        for (int i = 0; i < list.size(); i++) {
            final String[] str = (String[]) list.get(i);
            String trim = str[0];
            if (StringUtils.isNotBlank(trim)) {
                if (trim.length() > 32) {
                    trim = str[0].substring(0, 32);
                }
                final boolean flag = RegexUtil.checkTTTAdress(trim);
                final double num = Double.parseDouble(str[3]);
                if (num > 0 && flag == true) {
                    map.put(trim, str[3]);
                }
            }
        }
        return map;
    }

    /**
     * 读取excel的数据封装数据返给前端
     *
     * @param list
     * @return
     */
    @Override
    public Map<String, String> AnalyDataFinancial(final List list) {
        final Map<String, String> map = new LinkedHashMap<>();
        CoinServiceImpl.logger.info("map{}" + list.size());
        for (int i = 0; i < list.size(); i++) {
            final String[] excelLine = (String[]) list.get(i);
            final String smartAddress = excelLine[0];
            if (StringUtils.isNotBlank(smartAddress)) {
                map.put(smartAddress, excelLine[3]);
            }
        }
        return map;
    }

}
