package org.trustnote.activity.common.pojo;

import java.time.LocalDateTime;

public class InviteRecord {
    private Integer id;

    private String trustnoteAddress;

    private Integer rewardTtn;

    private Integer inviteSeveral;

    private String mobilePhone;

    private String inviteCode;

    private LocalDateTime crtTime;

    public LocalDateTime getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(LocalDateTime crtTime) {
        this.crtTime = crtTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrustnoteAddress() {
        return trustnoteAddress;
    }

    public void setTrustnoteAddress(String trustnoteAddress) {
        this.trustnoteAddress = trustnoteAddress;
    }

    public Integer getRewardTtn() {
        return rewardTtn;
    }

    public void setRewardTtn(Integer rewardTtn) {
        this.rewardTtn = rewardTtn;
    }

    public Integer getInviteSeveral() {
        return inviteSeveral;
    }

    public void setInviteSeveral(Integer inviteSeveral) {
        this.inviteSeveral = inviteSeveral;
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

    @Override
    public String toString() {
        return "InviteRecord{" +
                "id=" + id +
                ", trustnoteAddress='" + trustnoteAddress + '\'' +
                ", rewardTtn=" + rewardTtn +
                ", inviteSeveral=" + inviteSeveral +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", crtTime=" + crtTime +
                '}';
    }
}