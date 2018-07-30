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
public class FinancialLockSearchApi {
    private String financialName;
    private String productName;
    private String sharedAddress;
    private String deviceAddress;
    private String walletAddress;
    private BigDecimal lockUpAmount;
    private BigDecimal incomeAmount;
    private Long operationTime;
    private String lockUpStatus;
    private BigDecimal tempAmount;
    private Integer tFansAmount;
}
