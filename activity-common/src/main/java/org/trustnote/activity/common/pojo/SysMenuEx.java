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
public class SysMenuEx {
    private Integer id;

    private String menuName;

    private String menuShort;

    private Integer pid;

    private String menuUrl;
    
    private Boolean checked;
}
