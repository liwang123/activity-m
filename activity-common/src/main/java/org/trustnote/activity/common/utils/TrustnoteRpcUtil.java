package org.trustnote.activity.common.utils;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
public class TrustnoteRpcUtil {
    private static final Logger logger = LogManager.getLogger(TrustnoteRpcUtil.class);

    private static JsonRpcHttpClient client = null;

    public static JsonRpcHttpClient getClient(String url) {
        if (client == null) {
            try {
                client = new JsonRpcHttpClient(new URL(url));
            } catch (MalformedURLException e) {
                logger.error("error: {}", e);
            }
        }
        return client;
    }
}
