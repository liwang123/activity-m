package org.trustnote.activity.common.enume;

/**
 * @author zhuxl
 * CreateTime: 17/12/26
 */
public enum StatesEnum {
    NON_PAYMENT(1, "未支付"),
    NOT_ENOUGH(2, "余额不足"),
    TO_PAYMENT(3, "待发币"),
    COMPLETE(4, "已完成"),
    NOT_CONFIRM(5, "待确认"),
    TRANSFER_FAILE(6, "转账失败"),
    PUSH_FAILE(7, "推送消息失败"),
    REQUEST_OK(200, "请求成功"),
    CAN(2001, "可以"),
    REQUEST_ERROR(3001, "该订单不支持转账"),
    RATE_CONNECT_ERROR(3002, "getRate connect timeout"),
    CHECK_BALANCE_ERROR(3003, "checkBalance connect timeout"),
    NOT_ENOUGH_MONEY(3004, "NOT ENOUGH MONEY"),
    PAY_ADDRESS_FAILE(3005, "payToAddress connect timeout"),
    NOT_CAN(5001, "不可以");

    private int code;
    protected String msg;

    private StatesEnum(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
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
}
