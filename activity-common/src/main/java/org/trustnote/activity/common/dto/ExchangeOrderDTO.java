package org.trustnote.activity.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeOrderDTO {
    /**
     * 购买币种
     */
    private String currency;
    /**
     * 购买数量
     */
    private BigDecimal quantity;
    /**
     * 支付方式
     */
    private String payment;
    /**
     * 收到BTC数量
     */
    private BigDecimal receipt;
    /**
     * 付款BTC地址
     */
    private String fromAddress;
    /**
     * 收款BTC地址
     */
    private String toAddress;
    /**
     * 转账TTT地址
     */
    private String tttAddress;
    /**
     * 设备地址
     */
    private String deviceAddress;
    /**
     * 汇率
     */
    private BigDecimal rate;
    /**
     * 状态1:未支付2:余额不足3:待发币4:已完成
     */
    private Integer states;
    /**
     * 邀请码
     */
    private String inviteCode;
}
