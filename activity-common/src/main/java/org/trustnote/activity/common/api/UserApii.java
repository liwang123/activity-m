package org.trustnote.activity.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApii implements UserDetails {
    private Integer id;
    @NotBlank(message = "请输入姓名")
    @Size(message = "姓名不能超过10个字符", max = 10)
    private String realname;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "请输入手机号")
    @Size(message = "手机号要求11个字符", max = 11, min = 11)
    private String phone;
    @Size(message = "钱包地址不能超过32个字符", max = 32)
    private String walletAddress;

    private Integer state;

    private String userDesc;

    private Date crtTime;

    private Date uptTime;

    private LocalDateTime lastLoginTime;
    @NotBlank(message = "角色不能为空")
    private Integer roleId;

    private List<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
