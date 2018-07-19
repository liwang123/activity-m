package org.trustnote.activity.common.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 〈〉
 *
 * @author WangYu
 * @create 2018/5/15
 * @since 1.0.0
 */
public class AppEnvConsts {
    public static final String CONTEXT_PATH = "ctxPath";
    public static final String APP_NAME_ITEM = "appName";
    public static final String VERSION_ITEM = "version";
    public static final String RANDOM_ITEM = "rnd";
    public static String VERSION = "2.0";
    public static String ENV_NAME = "prod";

    public AppEnvConsts() {
    }

    public static void setVersion(String version) {
        VERSION = version;
    }

    public static void setEnvName(String envName) {
        ENV_NAME = envName;
    }

    public static boolean isProductionMode() {
        return StringUtils.equalsAnyIgnoreCase(ENV_NAME, new CharSequence[]{"prod"});
    }
}
