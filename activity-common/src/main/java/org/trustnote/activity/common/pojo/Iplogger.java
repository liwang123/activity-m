package org.trustnote.activity.common.pojo;

import java.time.LocalDateTime;
import java.util.Date;

public class Iplogger {
    private Integer id;

    private String ip;

    private LocalDateTime time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Iplogger(String ip, LocalDateTime time) {
        this.ip = ip;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;

    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}