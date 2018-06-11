package org.trustnote.activity.common.jsonrpc;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
public class JsonRpc {
    private String id;
    private String jsonrpc;
    private String method;
    private String[] params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
