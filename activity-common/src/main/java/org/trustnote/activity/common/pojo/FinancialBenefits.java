package org.trustnote.activity.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialBenefits {
    private Integer id;

    private Integer financialId;

    private String productName;

    private LocalDateTime panicStartTime;

    private LocalDateTime panicEndTime;

    private LocalDateTime interestStartTime;

    private LocalDateTime interestEndTime;

    private LocalDateTime unlockTime;

    private BigDecimal panicTotalLimit;

    private BigDecimal minAmount;

    private BigDecimal purchaseLimit;

    private BigDecimal remainLimit;

    private Integer financialStatus;

    private Date crtTime;

    private Date uptTime;

}