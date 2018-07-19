package org.trustnote.activity.common.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckAccount {
    private Long id;

    private String fromAddress;

    private String toAddress;

    private BigDecimal amount;

    private Integer type;

    private Long exchangeId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}