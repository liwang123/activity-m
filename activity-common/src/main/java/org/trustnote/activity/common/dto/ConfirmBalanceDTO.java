package org.trustnote.activity.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmBalanceDTO {
    /**
     * 购买数量
     */
    private BigDecimal quantity;
    /**
     * 余额
     */
    private BigDecimal balance;

    private String states;
}
