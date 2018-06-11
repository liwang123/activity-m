package org.trustnote.activity.common.pojo;

import java.time.LocalDateTime;
import java.util.Date;

public class Channel {
    private Integer id;

    private String name;

    private String code;

    private LocalDateTime crtTime;

    private LocalDateTime uptTime;

    private Integer crtBy;

    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}