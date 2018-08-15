package org.trustnote.activity.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUserRole {
    private Integer id;

    private Integer userId;

    private Integer roleId;

}