package org.trustnote.activity.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhuxl 18-1-2
 * @since v0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApi {
    private String phone;

    private String password;

    private String code;

}
