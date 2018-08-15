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
public class SysRoleMenu {
    private Integer id;

    private Integer roleId;

    private Integer menuId;

    private Boolean checked;
}