package org.trustnote.activity.common.enume;

/**
 * @author zhuxl
 */

public enum MediaActionEnum {
    ONLINE(1, "online"),
    OFFLINE(2, "offline");
    int code;

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    String action;


    MediaActionEnum(final int code, final String action) {
        this.code = code;
        this.action = action;
    }

    public static MediaActionEnum getItem(final String action) {
        for (final MediaActionEnum mediaActionEnum : MediaActionEnum.values()) {
            if (mediaActionEnum.action.equals(action)) {
                return mediaActionEnum;
            }
        }
        return null;
    }
}
