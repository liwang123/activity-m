package org.trustnote.activity.common.enume;

/**
 * @author zhuxl
 */
public enum PositionTypeEnum {
    NOT(0, ""),
    YES(1, "已发收益");

    public String getValue() {
        return this.value;
    }

    public int getCode() {
        return this.code;
    }

    private final String value;
    private final int code;

    PositionTypeEnum(final int code, final String value) {
        this.code = code;
        this.value = value;
    }

    public static PositionTypeEnum getItem(final int code) {
        for (final PositionTypeEnum imageTypeEnum : PositionTypeEnum.values()) {
            if (imageTypeEnum.getCode() == code) {
                return imageTypeEnum;
            }
        }
        return null;
    }
}
