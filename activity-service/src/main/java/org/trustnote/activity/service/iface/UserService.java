package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.User;

/**
 * @author zhuxl 17-12-26
 * @since v0.3
 */
public interface UserService {
    User login(User user) throws Exception;
    User queryUser(String username) throws Exception;
}
