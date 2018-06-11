package org.trustnote.activity.common.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialLockUp {
    private Integer id;

    private String sharedAddress;

    private String deviceAddress;

    private Integer financialBenefitsId;

    private Integer lockUpAmount;

    private BigDecimal incomeAmount;

    private Date crtTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSharedAddress() {
        return sharedAddress;
    }

    public void setSharedAddress(String sharedAddress) {
        this.sharedAddress = sharedAddress == null ? null : sharedAddress.trim();
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress == null ? null : deviceAddress.trim();
    }

    public Integer getFinancialBenefitsId() {
        return financialBenefitsId;
    }

    public void setFinancialBenefitsId(Integer financialBenefitsId) {
        this.financialBenefitsId = financialBenefitsId;
    }

    public Integer getLockUpAmount() {
        return lockUpAmount;
    }

    public void setLockUpAmount(Integer lockUpAmount) {
        this.lockUpAmount = lockUpAmount;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}