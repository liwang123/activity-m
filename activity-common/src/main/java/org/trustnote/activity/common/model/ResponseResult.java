package org.trustnote.activity.common.model;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.trustnote.activity.common.utils.Sending;
import org.trustnote.activity.common.utils.SendingPool;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 〈返回模板类〉
 *
 * @author WangYu
 * @create 2018/5/15
 * @since 1.0.0
 */
public class ResponseResult implements Serializable {

    private int code = 0;
    private String msg = "";
    private String detailMsg = "";
    private Object data;

    public ResponseResult() {
    }

    private ResponseResult(final int code, final String msg, final String detailMsg, final Object data) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
        this.data = data;
    }

    public static ResponseResult success() {
        return ResponseResult.success("");
    }

    public static ResponseResult success(final Object data) {
        return ResponseResult.build(0, "Success", "", data);
    }

    public static ResponseResult failure(final int code, final String msg) {

        return ResponseResult.failure(code, msg, "");
    }

    public static ResponseResult failure(final ErrorCode errorCode) {
        return ResponseResult.failure(errorCode, "");
    }

    public static ResponseResult failure(final ErrorCode errorCode, final Throwable ex) {
        return ResponseResult.failure(errorCode, AppEnvConsts.isProductionMode() ? "" : ExceptionUtils.getStackTrace(ex));
    }

    public static ResponseResult failure(final ErrorCode errorCode, final String detailMsg) {
        return ResponseResult.failure(errorCode.getCode(), errorCode.getMessage(), detailMsg);
    }

    public static ResponseResult failure(final ErrorCode errorCode, final Object data) {
        return ResponseResult.build(errorCode.getCode(), errorCode.getMessage(), "", data);
    }

    public static ResponseResult failure(final String msg) {
        final List<String> list = Arrays.asList("13333611437@qq.com", "jing.zhang@thingtrust.com");
        list.stream().forEach(email -> {
            final SendingPool pool = SendingPool.getInstance();
            pool.addThread(new Sending(email, "TrustNote email", "有订单有异常" + msg));
        });
        return ResponseResult.failure(-1, msg, (String) "");
    }

    public static ResponseResult failure(final String msg, final String detailMsg) {
        return ResponseResult.failure(-1, msg, (String) detailMsg);
    }

    public static ResponseResult failure(final int code, final String msg, final String detailMsg) {
        return ResponseResult.build(code, msg, detailMsg, "");
    }

    public static ResponseResult failure(final int code, final String msg, final Throwable ex) {
        return ResponseResult.build(code, msg, AppEnvConsts.isProductionMode() ? "" : ExceptionUtils.getStackTrace(ex), new Object());
    }

    public static ResponseResult build(final int code, final String msg, final String detailMsg, final Object data) {
        return new ResponseResult(code, msg, detailMsg, data);
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

    public String getDetailMsg() {
        return this.detailMsg;
    }

    public void setDetailMsg(final String detailMsg) {
        this.detailMsg = detailMsg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseResult)) {
            return false;
        } else {
            final ResponseResult other = (ResponseResult) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                label49: {
                    final Object this$msg = this.getMsg();
                    final Object other$msg = other.getMsg();
                    if (this$msg == null) {
                        if (other$msg == null) {
                            break label49;
                        }
                    } else if (this$msg.equals(other$msg)) {
                        break label49;
                    }

                    return false;
                }

                final Object this$detailMsg = this.getDetailMsg();
                final Object other$detailMsg = other.getDetailMsg();
                if (this$detailMsg == null) {
                    if (other$detailMsg != null) {
                        return false;
                    }
                } else if (!this$detailMsg.equals(other$detailMsg)) {
                    return false;
                }

                final Object this$data = this.getData();
                final Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseResult;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getCode();
        final Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        final Object $detailMsg = this.getDetailMsg();
        result = result * 59 + ($detailMsg == null ? 43 : $detailMsg.hashCode());
        final Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ResponseResult(code=" + this.getCode() + ", msg=" + this.getMsg() + ", detailMsg=" + this.getDetailMsg() + ", data=" + this.getData() + ")";
    }
}
