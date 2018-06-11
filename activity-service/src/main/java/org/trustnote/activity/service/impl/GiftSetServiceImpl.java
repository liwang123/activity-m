package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.GiftSetExample;
import org.trustnote.activity.common.jsonrpc.JsonRpcTotal;
import org.trustnote.activity.common.pojo.GiftSet;
import org.trustnote.activity.common.utils.TrustnoteRpcUtil;
import org.trustnote.activity.common.utils.TrustnoteTestRpcUtil;
import org.trustnote.activity.service.iface.GiftSetService;
import org.trustnote.activity.skeleton.mybatis.mapper.GiftSetMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 18-1-30
 * @since v0.3
 */
@Service
public class GiftSetServiceImpl implements GiftSetService {
    private static final Logger logger = LogManager.getLogger(GiftSetServiceImpl.class);
    @Resource
    private GiftSetMapper giftSetMapper;

    private String testUrl= "http://18.182.3.182:6553";
    @Override
    public int saveGiftSet(GiftSet giftSet) throws Exception {
        GiftSetExample example = new GiftSetExample();
        GiftSetExample.Criteria criteria = example.createCriteria();
        criteria.andGiftTypeEqualTo(giftSet.getGiftType());
        GiftSet record = new GiftSet();
        record.setTtn(giftSet.getTtn());
        return giftSetMapper.updateByExampleSelective(record, example);
    }

    @Override
    public GiftSet queryGiftset(int type) throws Exception {
        GiftSetExample example = new GiftSetExample();
        GiftSetExample.Criteria criteria = example.createCriteria();
        criteria.andGiftTypeEqualTo(type);
        List<GiftSet> giftSets = giftSetMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(giftSets)) return null;
        return giftSets.get(0);
    }


    @Override
    public String sendToken(JSONArray array) throws Throwable {
        JsonRpcHttpClient client = TrustnoteTestRpcUtil.getClient(testUrl);
        logger.info("client-url: {}", client.getServiceUrl());
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
    public JsonRpcTotal getTokenbalance(String address) throws Throwable {
        JsonRpcHttpClient client = TrustnoteTestRpcUtil.getClient(testUrl);
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
}
