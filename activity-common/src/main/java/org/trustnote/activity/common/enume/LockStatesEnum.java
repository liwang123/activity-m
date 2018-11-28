package org.trustnote.activity.common.enume;

/**
 * @author zhuxl
 * CreateTime: 17/12/26
 */
public enum LockStatesEnum {
    PENDING_LOCK(1, "待锁仓"),
    LOCKING(2, "已锁仓"),
    UNLOCK(3, "已解锁"),
    PAYED(4, "已发收益"),
    NOT_PAYED(5, "未发收益");

    private int code;
    protected String msg;

    private LockStatesEnum(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String msg(final int code) {
        return null;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public String appendMsg(final String msg) {
        return this.getMsg() + ":" + msg;
    }

    public static String getName(final int code) {
        for (final LockStatesEnum c : LockStatesEnum.values()) {
            if (c.getCode() == code) {
                return c.getMsg();
            }
        }
        return null;
    }

}
