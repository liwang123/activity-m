package org.trustnote.activity.common.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialBenefits {
    private Integer id;

    private Integer financialId;

    private String productName;

    private Date panicStartTime;

    private Date panicEndTime;

    private Date interestStartTime;

    private Date interestEndTime;

    private Date unlockTime;

    private BigDecimal panicTotalLimit;

    private BigDecimal minAmount;

    private BigDecimal purchaseLimit;

    private BigDecimal remainLimit;

    private Integer financialStatus;

    private Date crtTime;

    private Date uptTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Integer financialId) {
        this.financialId = financialId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Date getPanicStartTime() {
        return panicStartTime;
    }

    public void setPanicStartTime(Date panicStartTime) {
        this.panicStartTime = panicStartTime;
    }

    public Date getPanicEndTime() {
        return panicEndTime;
    }

    public void setPanicEndTime(Date panicEndTime) {
        this.panicEndTime = panicEndTime;
    }

    public Date getInterestStartTime() {
        return interestStartTime;
    }

    public void setInterestStartTime(Date interestStartTime) {
        this.interestStartTime = interestStartTime;
    }

    public Date getInterestEndTime() {
        return interestEndTime;
    }

    public void setInterestEndTime(Date interestEndTime) {
        this.interestEndTime = interestEndTime;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    public BigDecimal getPanicTotalLimit() {
        return panicTotalLimit;
    }

    public void setPanicTotalLimit(BigDecimal panicTotalLimit) {
        this.panicTotalLimit = panicTotalLimit;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getPurchaseLimit() {
        return purchaseLimit;
    }

    public void setPurchaseLimit(BigDecimal purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    public BigDecimal getRemainLimit() {
        return remainLimit;
    }

    public void setRemainLimit(BigDecimal remainLimit) {
        this.remainLimit = remainLimit;
    }

    public Integer getFinancialStatus() {
        return financialStatus;
    }

    public void setFinancialStatus(Integer financialStatus) {
        this.financialStatus = financialStatus;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getUptTime() {
        return uptTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }
}