package org.trustnote.activity.common.pojo;

import java.time.LocalDateTime;

public class Media {
    private Integer id;

    private String cnTitle;

    private String enTitle;

    private String cnLink;

    private String enLink;

    private String cnDescription;

    private String enDescription;

    private String imageUrl;

    private Integer queue;

    private Integer status;

    private Integer type;

    private LocalDateTime crtTime;

    private LocalDateTime uptTime;

    private Integer crtBy;

    private Integer uptBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnTitle() {
        return cnTitle;
    }

    public void setCnTitle(String cnTitle) {
        this.cnTitle = cnTitle == null ? null : cnTitle.trim();
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle == null ? null : enTitle.trim();
    }

    public String getCnLink() {
        return cnLink;
    }

    public void setCnLink(String cnLink) {
        this.cnLink = cnLink == null ? null : cnLink.trim();
    }

    public String getEnLink() {
        return enLink;
    }

    public void setEnLink(String enLink) {
        this.enLink = enLink == null ? null : enLink.trim();
    }

    public String getCnDescription() {
        return cnDescription;
    }

    public void setCnDescription(String cnDescription) {
        this.cnDescription = cnDescription == null ? null : cnDescription.trim();
    }

    public String getEnDescription() {
        return enDescription;
    }

    public void setEnDescription(String enDescription) {
        this.enDescription = enDescription == null ? null : enDescription.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(LocalDateTime crtTime) {
        this.crtTime = crtTime;
    }

    public LocalDateTime getUptTime() {
        return uptTime;
    }

    public void setUptTime(LocalDateTime uptTime) {
        this.uptTime = uptTime;
    }

    public Integer getCrtBy() {
        return crtBy;
    }

    public void setCrtBy(Integer crtBy) {
        this.crtBy = crtBy;
    }

    public Integer getUptBy() {
        return uptBy;
    }

    public void setUptBy(Integer uptBy) {
        this.uptBy = uptBy;
    }
}