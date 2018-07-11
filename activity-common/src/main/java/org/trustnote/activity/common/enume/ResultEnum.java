package org.trustnote.activity.common.enume;

/**
 * @author zhuxl
 *         CreateTime: 17/12/26
 */
public enum ResultEnum {
    NOTLOGIN("1001", "未登录"),
    OK("200","请求成功"),
    CREATED("201","创建成功"),
    ACCEPTED("202","更新成功"),
    BAD_REQUEST("400","请求的地址不存在或者包含不支持的参数"),
    UNAUTHORIZD("401","未授权"),
    FORBIDDEN("403","被禁止访问"),
    NOT_FOUND("404","请求的资源不存在"),
    METHOD_NOT_ALLOWED("405","方法禁用"),
    NOT_ACCEPTABLE("406","不接受"),
    REQUEST_TIMEOUT("408","请求超时"),
    CONFLICT("409","冲突"),
    PRECONDITION_FAILED("412","未满足前提条件"),
    INTERNAL_SERVER_ERROR("500","内部错误"),
    INVALIDARGUMENT("1000","请求参数:"),
    API_DISABLED("2000","该API已被禁用"),
    JSON_PARSE_ERROR("2001","json解析错误"),
    SERVER_UPGRADING("2002","服务正在升级中...,请稍后再试"),
    DATA_ACCESS("3000","数据库错误"),
    MISSION_FAIL("4000","操作失败"),
    SystemBusy("4001","系统忙");

    private String code;
    protected String msg;

    private ResultEnum(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
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
