package org.trustnote.activity.common.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DepositLockDTO {
    private Integer id;

    private String walletAddress;

    private String receiptAddress;

    private BigDecimal lockAmount;

    private BigDecimal incomeAmount;

    private BigDecimal returnAmount;

    private BigDecimal tfsAmount;

    private String packageType;

    private String lockStatus;

    private String lockTime;

    private String unlockTime;


}