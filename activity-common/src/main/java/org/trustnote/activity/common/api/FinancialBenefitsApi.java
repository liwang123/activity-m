package org.trustnote.activity.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialBenefitsApi {
    private Integer id;
    @NotNull(message = "请选择套餐")
    private Integer financialId;
    @NotBlank(message = "请输入产品名称")
    @Size(message = "产品名称不能超过30个字符", max = 30)
    private String productName;
    @NotNull(message = "请输入时间")
    private Long panicStartTime;
    @NotNull(message = "请输入时间")
    private Long panicEndTime;
    @NotNull(message = "请输入时间")
    private Long interestStartTime;
    @NotNull(message = "请输入时间")
    private Long interestEndTime;
    @NotNull(message = "请输入时间")
    private Long unlockTime;
    @Max(message = "长度不能大于10位", value = Integer.MAX_VALUE)
    private Integer panicTotalLimit;
    @NotNull(message = "请输入起购额度")
    @Min(message = "不少于１MN", value = 1)
    @Max(message = "长度不能大于10位", value = Integer.MAX_VALUE)
    private Integer minAmount;
    @Max(message = "长度不能大于10位", value = Integer.MAX_VALUE)
    private Integer purchaseLimit;

    private BigDecimal remainLimit;

    private Integer financialStatus;

    private String activityStatus;

    private long nextPanicStartTime;

    private long nextPanicEndTime;
    @NotNull(message = "请输入年化收益率")
    @Min(message = "不能小于0", value = 0)
    @Max(message = "不能大于１", value = 1)
    private Float financialRate;
    private Integer numericalv;
    private BigDecimal alsoLockUpAmount;
}
