package org.trustnote.activity.common.pojo;

import java.time.LocalDateTime;

public class Announce {
    private Integer id;

    private String titleCn;

    private String titleEn;

    private Integer viewedCn;

    private Integer viewedEn;

    private LocalDateTime releaseTime;

    private Integer placedTop;

    private Integer available;

    private LocalDateTime lastModifed;

    private Integer lastBy;

    private String contentCn;

    private String contentEn;

    private String externalLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleCn() {
        return titleCn;
    }

    public void setTitleCn(String titleCn) {
        this.titleCn = titleCn == null ? null : titleCn.trim();
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn == null ? null : titleEn.trim();
    }

    public Integer getViewedCn() {
        return viewedCn;
    }

    public void setViewedCn(Integer viewedCn) {
        this.viewedCn = viewedCn;
    }

    public Integer getViewedEn() {
        return viewedEn;
    }

    public void setViewedEn(Integer viewedEn) {
        this.viewedEn = viewedEn;
    }

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getPlacedTop() {
        return placedTop;
    }

    public void setPlacedTop(Integer placedTop) {
        this.placedTop = placedTop;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public LocalDateTime getLastModifed() {
        return lastModifed;
    }

    public void setLastModifed(LocalDateTime lastModifed) {
        this.lastModifed = lastModifed;
    }

    public Integer getLastBy() {
        return lastBy;
    }

    public void setLastBy(Integer lastBy) {
        this.lastBy = lastBy;
    }

    public String getContentCn() {
        return contentCn;
    }

    public void setContentCn(String contentCn) {
        this.contentCn = contentCn == null ? null : contentCn.trim();
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn == null ? null : contentEn.trim();
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }
}