package org.trustnote.activity.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhuxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZxlGrantedAuthority implements GrantedAuthority {
    private String url;

    @Override
    public String getAuthority() {
        return this.url;
    }
}
