package org.trustnote.activity.common.jsonrpc;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
public class JsonRpcResultBase implements Serializable {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String stable;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String pending;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String is_private;

    public String getStable() {
        return stable;
    }

    public void setStable(String stable) {
        this.stable = stable;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getIs_private() {
        return is_private;
    }

    public void setIs_private(String is_private) {
        this.is_private = is_private;
    }
}
