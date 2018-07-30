package org.trustnote.activity.common.enume;

/**
 * @author zhuxl
 */

public enum LockUpSearchEnum {
    WALLET_ADDRESS(1), DEVICE_ADDRESS(2), SHARED_ADDRESS(3);
    private final Integer value;

    LockUpSearchEnum(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
