package org.trustnote.activity.common.api;

/**
 * @author zhuxl 18-1-30
 * @since v0.3
 */
public class InvitationApi {
    private String trustnoteAddress;
    private String mobilePhone;
    private String inviteCode;
    private String smsCode;

    public String getTrustnoteAddress() {
        return trustnoteAddress;
    }

    public void setTrustnoteAddress(String trustnoteAddress) {
        this.trustnoteAddress = trustnoteAddress;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
