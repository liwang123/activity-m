package org.trustnote.activity.service.iface;

import com.alibaba.fastjson.JSONArray;
import org.trustnote.activity.common.jsonrpc.JsonRpcResult;
import org.trustnote.activity.common.jsonrpc.JsonRpcTotal;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
public interface CoinService {
    JsonRpcResult getbalance(String address) throws Throwable;

    JsonRpcTotal getbalanceAll(String address) throws Throwable;

    JsonRpcResult getMainBalance(String code) throws Throwable;

    String sendToMultiAddress(JSONArray array, String code) throws Throwable;

    Map<String,String> AnalyData(List list);

    Map<String, String> AnalyDataFinancial(List list);
}
