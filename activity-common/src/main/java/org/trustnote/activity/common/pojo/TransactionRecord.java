package org.trustnote.activity.common.pojo;

import java.util.Date;

public class TransactionRecord {
    private Long id;

    private String trustnoteAddress;

    private Integer handselCount;

    private Date submitTime;

    private String submitTimeShow;

    private String inviteTrustnoteAddress;

    private String mobilePhone;

    private String inviteCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrustnoteAddress() {
        return trustnoteAddress;
    }

    public void setTrustnoteAddress(String trustnoteAddress) {
        this.trustnoteAddress = trustnoteAddress;
    }

    public Integer getHandselCount() {
        return handselCount;
    }

    public void setHandselCount(Integer handselCount) {
        this.handselCount = handselCount;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitTimeShow() {
        return submitTimeShow;
    }

    public void setSubmitTimeShow(String submitTimeShow) {
        this.submitTimeShow = submitTimeShow;
    }

    public String getInviteTrustnoteAddress() {
        return inviteTrustnoteAddress;
    }

    public void setInviteTrustnoteAddress(String inviteTrustnoteAddress) {
        this.inviteTrustnoteAddress = inviteTrustnoteAddress;
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
}