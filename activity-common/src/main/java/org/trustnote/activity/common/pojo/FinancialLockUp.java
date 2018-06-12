package org.trustnote.activity.common.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhuxl
 */
public class FinancialLockUp {
    private Integer id;

    private String sharedAddress;

    private String deviceAddress;

    private Integer financialBenefitsId;

    private Integer lockUpAmount;

    private BigDecimal incomeAmount;

    private Date crtTime;

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

    public Integer getLockUpAmount() {
        return this.lockUpAmount;
    }

    public void setLockUpAmount(final Integer lockUpAmount) {
        this.lockUpAmount = lockUpAmount;
    }

    public BigDecimal getIncomeAmount() {
        return this.incomeAmount;
    }

    public void setIncomeAmount(final BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Date getCrtTime() {
        return this.crtTime;
    }

    public void setCrtTime(final Date crtTime) {
        this.crtTime = crtTime;
    }
}