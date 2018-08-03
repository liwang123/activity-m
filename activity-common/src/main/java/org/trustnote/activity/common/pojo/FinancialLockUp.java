package org.trustnote.activity.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private Integer tfansAmount;

    private String walletAddress;

}