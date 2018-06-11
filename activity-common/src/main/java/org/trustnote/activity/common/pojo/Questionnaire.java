package org.trustnote.activity.common.pojo;

import java.time.LocalDateTime;
import java.util.Date;

public class Questionnaire {
    private Integer id;

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", job='" + job + '\'' +
                ", education=" + education +
                ", immediately=" + immediately +
                ", tactics=" + tactics +
                ", amount=" + amount +
                ", platform='" + platform + '\'' +
                ", subTime=" + subTime +
                ", address='" + address + '\'' +
                ", code='" + code + '\'' +
                ", suggest='" + suggest + '\'' +
                '}';
    }

    private String job;

    private Integer education;

    private Boolean immediately;

    private String tactics;

    private String amount;

    private String platform;

    private LocalDateTime subTime;

    private String address;

    private String code;

    private String suggest;

    private String balance;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Boolean getImmediately() {
        return immediately;
    }

    public void setImmediately(Boolean immediately) {
        this.immediately = immediately;
    }

    public String getTactics() {
        return tactics;
    }

    public void setTactics(String tactics) {
        this.tactics = tactics;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public LocalDateTime getSubTime() {
        return subTime;
    }

    public void setSubTime(LocalDateTime subTime) {
        this.subTime = subTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
    }
}