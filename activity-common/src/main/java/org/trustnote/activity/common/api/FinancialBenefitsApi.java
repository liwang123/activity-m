package org.trustnote.activity.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialBenefitsApi {
    private Integer id;

    private Integer financialId;

    private String productName;

    private Long panicStartTime;

    private Long panicEndTime;

    private Long interestStartTime;

    private Long interestEndTime;

    private Long unlockTime;

    private BigDecimal panicTotalLimit;

    private BigDecimal minAmount;

    private BigDecimal purchaseLimit;

    private BigDecimal remainLimit;

    private String financialStatusName;

    private long nextPanicStartTime;

    private long nextPanicEndTime;
}
