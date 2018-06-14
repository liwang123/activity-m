package org.trustnote.activity.common.pojo;

import java.util.Date;

/**
 * @author zhuxl
 */
public class Financial {
    private Integer id;

    private String financialName;

    private Float financialRate;

    private Date uptTime;

    private Integer numericalv;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFinancialName() {
        return this.financialName;
    }

    public void setFinancialName(final String financialName) {
        this.financialName = financialName == null ? null : financialName.trim();
    }

    public Float getFinancialRate() {
        return this.financialRate;
    }

    public void setFinancialRate(final Float financialRate) {
        this.financialRate = financialRate;
    }

    public Date getUptTime() {
        return this.uptTime;
    }

    public void setUptTime(final Date uptTime) {
        this.uptTime = uptTime;
    }

    public Integer getNumericalv() {
        return this.numericalv;
    }

    public void setNumericalv(final Integer numericalv) {
        this.numericalv = numericalv;
    }
}