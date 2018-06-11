package org.trustnote.activity.common.enume;

/**
 * @author zhuxl
 */

public enum ImageTypeEnum {
    JPG("FFD8FF"),
    PNG("89504E47"),
    BMP("424D");

    public String getValue() {
        return this.value;
    }

    private final String value;

    ImageTypeEnum(final String value) {
        this.value = value;
    }

    public static ImageTypeEnum getItem(final String value) {
        for (final ImageTypeEnum imageTypeEnum : ImageTypeEnum.values()) {
            if (imageTypeEnum.getValue().equals(value)) {
                return imageTypeEnum;
            }
        }
        return null;
    }
}
