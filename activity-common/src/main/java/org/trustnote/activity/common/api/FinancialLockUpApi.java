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
public class FinancialLockUpApi {
    private Integer id;

    private String sharedAddress;

    private String deviceAddress;

    private Integer financialBenefitsId;

    private BigDecimal lockUpAmount;

    private BigDecimal incomeAmount;

    private Long operationTime;

    private String lockUpStatus;

    private Integer orderAmount;

    private BigDecimal tempAmount;
}
