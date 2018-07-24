package org.trustnote.activity.common.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeEmailOrderDTO {
    private Long id;

    private String currency;

    private BigDecimal quantity;

    private String payment;

    private BigDecimal receipt;

    private String toAddress;

    private String tttAddress;

    private String deviceAddress;

    private BigDecimal rate;

    private String state;

    private String inviteCode;

    private LocalDateTime createTime;

}