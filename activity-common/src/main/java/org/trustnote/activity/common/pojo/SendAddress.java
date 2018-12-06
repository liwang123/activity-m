package org.trustnote.activity.common.pojo;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SendAddress {
    private Integer id;

    private String address;

    private Integer status;

    private Integer countNumber;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}