package org.trustnote.activity.common.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DepositLock {
    private Integer id;

    private String walletAddress;

    private String receiptAddress;

    private BigDecimal lockAmount;

    private BigDecimal incomeAmount;

    private BigDecimal returnAmount;

    private BigDecimal tfsAmount;

    private Integer packageType;

    private Integer lockStatus;

    private LocalDateTime lockTime;

    private LocalDateTime unlockTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}