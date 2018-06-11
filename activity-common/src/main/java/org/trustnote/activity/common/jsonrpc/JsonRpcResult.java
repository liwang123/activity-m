package org.trustnote.activity.common.jsonrpc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
public class JsonRpcResult implements Serializable {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private JsonRpcResultBase base;

    public JsonRpcResultBase getBase() {
        return base;
    }

    public void setBase(JsonRpcResultBase base) {
        this.base = base;
    }
}