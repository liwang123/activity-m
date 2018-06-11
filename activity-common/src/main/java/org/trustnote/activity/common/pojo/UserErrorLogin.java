package org.trustnote.activity.common.pojo;

/**
 * @author zhuxl 18-2-3
 * @since v0.3
 */
public class UserErrorLogin {
    private String loginTime;
    private int errorNumber;

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }
}
