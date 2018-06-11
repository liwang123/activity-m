package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.UserExample;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.common.utils.Md5Util;
import org.trustnote.activity.service.iface.UserService;
import org.trustnote.activity.skeleton.mybatis.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuxl 17-12-26
 * @since v0.3
 */
@Service
public class UserServiceImpl implements UserService {
    public static final String salt = "^YHN/.,mn";
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) throws Exception {
        String md5Password = Md5Util.getMd5Code(salt + user.getPassword());
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(md5Password);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) return null;
        return users.get(0);
    }

    @Override
    public User queryUser(String username) throws Exception {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) return null;
        return users.get(0);
    }
}
