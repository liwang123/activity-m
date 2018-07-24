package org.trustnote.activity.common.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeOrder {
    private Long id;

    private String currency;

    private BigDecimal quantity;

    private String payment;

    private BigDecimal receipt;

    private String fromAddress;

    private String toAddress;

    private String tttAddress;

    private String deviceAddress;

    private BigDecimal rate;

    private Integer states;

    private String inviteCode;

    private LocalDateTime createTime;

    private String creTime;

    private LocalDateTime updateTime;

}