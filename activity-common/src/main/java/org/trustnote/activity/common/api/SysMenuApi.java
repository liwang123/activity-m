package org.trustnote.activity.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysMenuApi {
    private Integer menuId;
    private String menuName;
    private String menuShort;
    private String menuUrl;
    private Boolean checked;
    private List<SysMenuApi> children;
}
