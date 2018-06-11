package org.trustnote.activity.service.iface;

import com.alibaba.fastjson.JSONArray;
import org.trustnote.activity.common.jsonrpc.JsonRpcTotal;
import org.trustnote.activity.common.pojo.GiftSet;

/**
 * @author zhuxl 18-1-30
 * @since v0.3
 */
public interface GiftSetService {
    int saveGiftSet(GiftSet giftSet) throws Exception;
    GiftSet queryGiftset(int type) throws Exception;

    //token发布测试
    String sendToken(JSONArray array) throws Throwable;
    JsonRpcTotal getTokenbalance(String address) throws Throwable;
}
