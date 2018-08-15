package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.api.UserApii;
import org.trustnote.activity.common.exception.ZxlException;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl 17-12-26
 * @since v0.3
 */
public interface UserService {
    User login(User user) throws Exception;

    User queryUser(String phone);

    UserApii findUserByPhone(String phone);

    User queryUserByPhoneNotContainsOneself(String phone, int id);

    int saveUser(UserApii user) throws ZxlException;

    UserApii queryUserById(int id);

    int modifyUser(UserApii userApii) throws ZxlException;

    List<UserApii> queryUsers(Page<User> page);

    int removeUser(int id);

    void lastLogin(String phone);
}
