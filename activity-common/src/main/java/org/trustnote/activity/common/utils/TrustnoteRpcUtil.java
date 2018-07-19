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

    private static JsonRpcHttpClient client1 = null;

    private static JsonRpcHttpClient client2 = null;

    /**
     * 普通client
     *
     * @param url
     * @return
     */
    public static JsonRpcHttpClient getClient(final String url) {
        if (TrustnoteRpcUtil.client1 == null) {
            try {
                TrustnoteRpcUtil.client1 = new JsonRpcHttpClient(new URL(url));
            } catch (final MalformedURLException e) {
                TrustnoteRpcUtil.logger.error("error: {}", e);
            }
        }
        return TrustnoteRpcUtil.client1;
    }

    /**
     * 持仓收益client
     *
     * @param url
     * @return
     */
    public static JsonRpcHttpClient getHeadless2Client(final String url) {
        if (TrustnoteRpcUtil.client2 == null) {
            try {
                TrustnoteRpcUtil.client2 = new JsonRpcHttpClient(new URL(url));
            } catch (final MalformedURLException e) {
                TrustnoteRpcUtil.logger.error("error: {}", e);
            }
        }
        return TrustnoteRpcUtil.client2;
    }
}
