package org.trustnote.activity.common.enume;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
public enum DownloadType {
    Android("android"), IOS("ios"), Windows("windows"), Mac("mac"), Linux("linux"), Github("github");
    private final String value;

    DownloadType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static DownloadType getItem(final String value) {
        for (final DownloadType type : DownloadType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getSupportedValue() {
        final StringBuffer ret = new StringBuffer();
        for (final DownloadType type : DownloadType.values()) {
            ret.append(type.getValue()).append(",");
        }
        return ret.substring(0, ret.length() - 1);
    }
}
