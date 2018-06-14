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

    private Integer panicTotalLimit;

    private Integer minAmount;

    private Integer purchaseLimit;

    private BigDecimal remainLimit;

    private Integer financialStatus;

    private String activityStatus;

    private long nextPanicStartTime;

    private long nextPanicEndTime;
}
