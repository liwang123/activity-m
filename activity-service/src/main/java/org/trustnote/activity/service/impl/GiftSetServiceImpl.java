package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSON;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.GiftSetExample;
import org.trustnote.activity.common.example.SendAddressExample;
import org.trustnote.activity.common.jsonrpc.JsonRpcTotal;
import org.trustnote.activity.common.pojo.GiftSet;
import org.trustnote.activity.common.pojo.SendAddress;
import org.trustnote.activity.common.utils.OkHttpUtils;
import org.trustnote.activity.common.utils.TrustnoteTestRpcUtil;
import org.trustnote.activity.service.iface.GiftSetService;
import org.trustnote.activity.skeleton.mybatis.mapper.GiftSetMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.SendAddressMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Autowired
    private SendAddressMapper sendAddressMapper;

    private final String testUrl = "http://18.182.3.182:6553";
    @Override
    public int saveGiftSet(final GiftSet giftSet) throws Exception {
        final GiftSetExample example = new GiftSetExample();
        final GiftSetExample.Criteria criteria = example.createCriteria();
        criteria.andGiftTypeEqualTo(giftSet.getGiftType());
        final GiftSet record = new GiftSet();
        record.setTtn(giftSet.getTtn());
        return this.giftSetMapper.updateByExampleSelective(record, example);
    }

    @Override
    public GiftSet queryGiftset(final int type) throws Exception {
        final GiftSetExample example = new GiftSetExample();
        final GiftSetExample.Criteria criteria = example.createCriteria();
        criteria.andGiftTypeEqualTo(type);
        final List<GiftSet> giftSets = this.giftSetMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(giftSets)) {
            return null;
        }
        return giftSets.get(0);
    }


    @Override
    public String sendToken(final String address) {
        final SendAddressExample sendAddressExample = new SendAddressExample();
        sendAddressExample.createCriteria()
                .andAddressEqualTo(address);
        final List<SendAddress> sendAddresses = this.sendAddressMapper.selectByExample(sendAddressExample);
        if (sendAddresses.size() != 0) {
            return "null";
        }
        final SendAddress build = SendAddress.builder()
                .address(address)
                .build();
        this.sendAddressMapper.insertSelective(build);
        final String url = "http://119.28.133.253:6552";
        final Map<String, Object> map = new HashMap<>();
        final List<Object> list = new ArrayList<>();
        list.add(address);
        list.add(1000000);
        map.put("jsonrpc", "2.0");
        map.put("id", 1);
        map.put("method", "sendtoaddress");
        map.put("params", list);
        final String result = OkHttpUtils.rpcRequestBodyPost(url, map);
        return result;
    }

    @Override
    public JsonRpcTotal getTokenbalance(final String address) throws Throwable {
        final JsonRpcHttpClient client = TrustnoteTestRpcUtil.getClient(this.testUrl);
        GiftSetServiceImpl.logger.info("client-url: {}", client.getServiceUrl());
        final String[] params = new String[]{address};
        final Map<String, String> headers = new HashMap<>();
        headers.put("id", "2");
        headers.put("jsonrpc", "2.0");
        client.setHeaders(headers);
        final JsonRpcTotal jsonRpcResult = client.invoke("getbalanceAll", params, JsonRpcTotal.class);
        GiftSetServiceImpl.logger.info("jsonRpcResult: {}", JSON.toJSONString(jsonRpcResult));
        return jsonRpcResult;
    }
}
