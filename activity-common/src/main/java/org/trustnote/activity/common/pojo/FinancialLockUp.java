package org.trustnote.activity.common.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhuxl
 */
public class FinancialLockUp {
    private Integer id;

    private String sharedAddress;

    private String deviceAddress;

    private Integer financialBenefitsId;

    private BigDecimal lockUpAmount;

    private BigDecimal incomeAmount;

    private LocalDateTime operationTime;

    private String lockUpStatus;

    private Integer orderAmount;

    private BigDecimal tempAmount;

    private Integer calactionStatus;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getSharedAddress() {
        return this.sharedAddress;
    }

    public void setSharedAddress(final String sharedAddress) {
        this.sharedAddress = sharedAddress == null ? null : sharedAddress.trim();
    }

    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    public void setDeviceAddress(final String deviceAddress) {
        this.deviceAddress = deviceAddress == null ? null : deviceAddress.trim();
    }

    public Integer getFinancialBenefitsId() {
        return this.financialBenefitsId;
    }

    public void setFinancialBenefitsId(final Integer financialBenefitsId) {
        this.financialBenefitsId = financialBenefitsId;
    }

    public BigDecimal getLockUpAmount() {
        return this.lockUpAmount;
    }

    public void setLockUpAmount(final BigDecimal lockUpAmount) {
        this.lockUpAmount = lockUpAmount;
    }

    public BigDecimal getIncomeAmount() {
        return this.incomeAmount;
    }

    public void setIncomeAmount(final BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public LocalDateTime getOperationTime() {
        return this.operationTime;
    }

    public void setOperationTime(final LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public String getLockUpStatus() {
        return this.lockUpStatus;
    }

    public void setLockUpStatus(final String lockUpStatus) {
        this.lockUpStatus = lockUpStatus;
    }

    public Integer getOrderAmount() {
        return this.orderAmount;
    }

    public void setOrderAmount(final Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getTempAmount() {
        return this.tempAmount;
    }

    public void setTempAmount(final BigDecimal tempAmount) {
        this.tempAmount = tempAmount;
    }

    public Integer getCalactionStatus() {
        return this.calactionStatus;
    }

    public void setCalactionStatus(final Integer calactionStatus) {
        this.calactionStatus = calactionStatus;
    }
}