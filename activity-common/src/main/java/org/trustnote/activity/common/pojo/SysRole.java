package org.trustnote.activity.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysRole {
    private Integer id;
    @NotBlank(message = "请输入角色名称")
    @Size(message = "角色名称不能超过50个字符", max = 50)
    private String roleName;

    private Integer state;
    @Size(message = "角色描述不能超过200个字符", max = 200)
    private String roleDesc;

    private Date crtTime;

    private Date uptTime;

}