package org.trustnote.activity.common.pojo;

import java.time.LocalDate;
import java.util.Date;

public class DownloadChannel {
    private Integer id;

    private String type;

    private LocalDate downloadTime;

    private Integer sum;

    private Integer channelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public LocalDate getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(LocalDate downloadTime) {
        this.downloadTime = downloadTime;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}