package org.trustnote.activity.common.pojo;

import java.time.LocalDateTime;
import java.util.Date;

public class Activity {
    private Integer id;

    private String title;

    private LocalDateTime starttime;

    private LocalDateTime endtime;

    private String label;

    private Integer inviterNum;

    private Integer inviteeNum;

    private String enlink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public LocalDateTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getInviterNum() {
        return inviterNum;
    }

    public void setInviterNum(Integer inviterNum) {
        this.inviterNum = inviterNum;
    }

    public Integer getInviteeNum() {
        return inviteeNum;
    }

    public void setInviteeNum(Integer inviteeNum) {
        this.inviteeNum = inviteeNum;
    }

    public String getEnlink() {
        return enlink;
    }

    public void setEnlink(String enlink) {
        this.enlink = enlink == null ? null : enlink.trim();
    }
}