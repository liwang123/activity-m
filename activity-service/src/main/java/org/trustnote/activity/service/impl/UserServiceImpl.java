package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.trustnote.activity.common.api.UserApii;
import org.trustnote.activity.common.example.SysUserRoleExample;
import org.trustnote.activity.common.example.UserExample;
import org.trustnote.activity.common.exception.ZxlException;
import org.trustnote.activity.common.pojo.SysUserRole;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.common.utils.Md5Util;
import org.trustnote.activity.service.iface.SysUserRoleService;
import org.trustnote.activity.service.iface.UserService;
import org.trustnote.activity.skeleton.mybatis.mapper.SysUserRoleMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.UserMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl 17-12-26
 * @since v0.3
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    public static final String salt = "^YHN/.,mn";
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public User login(final User user) throws Exception {
        final String md5Password = Md5Util.getMd5Code(UserServiceImpl.salt + user.getPassword());
        final UserExample example = new UserExample();
        final UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(user.getPhone());
        criteria.andPasswordEqualTo(md5Password);
        criteria.andStateEqualTo(0);
        final List<User> users = this.userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User queryUser(final String phone) {
        final UserExample example = new UserExample();
        final UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        final List<User> users = this.userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public UserApii findUserByPhone(final String phone) {
        final UserExample example = new UserExample();
        final UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        final List<User> users = this.userMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(users)) {
            return this.convertUserToUserApii(users.get(0));
        }
        return null;
    }

    @Override
    public User queryUserByPhoneNotContainsOneself(final String phone, final int id) {
        final UserExample example = new UserExample();
        final UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        criteria.andIdNotEqualTo(id);
        final List<User> users = this.userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ZxlException.class)
    @Override
    public int saveUser(final UserApii userApi) throws ZxlException {
        final User hasUser = this.queryUser(userApi.getPhone());
        if (hasUser != null) {
            return -1;
        }
        final User record = User.builder()
                .realname(userApi.getRealname())
                .password(Md5Util.getMd5Code(UserServiceImpl.salt + userApi.getPassword()))
                .phone(userApi.getPhone())
                .walletAddress(userApi.getWalletAddress())
                .state(userApi.getState())
                .userDesc(userApi.getUserDesc())
                .build();
        final int saveUserStatus = this.userMapper.insertSelective(record);
        UserServiceImpl.logger.info("保存用户数据状态: {} 自动生成的ID: {}", saveUserStatus, record.getId());
        int userRoleStatus = 0;
        if (saveUserStatus > 0) {
            final SysUserRole sysUserRole = SysUserRole.builder()
                    .userId(record.getId())
                    .roleId(userApi.getRoleId())
                    .build();
            userRoleStatus = this.sysUserRoleMapper.insertSelective(sysUserRole);
            if (userRoleStatus < 1) {
                throw new ZxlException("添加用户失败！");
            }
        }
        return userRoleStatus;
    }

    @Override
    public UserApii queryUserById(final int id) {
        final User user = this.userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }

        return this.convertUserToUserApii(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ZxlException.class)
    @Override
    public int modifyUser(final UserApii userApii) throws ZxlException {
        final User hasUser = this.queryUserByPhoneNotContainsOneself(userApii.getPhone(), userApii.getId());
        if (hasUser != null) {
            return -1;
        }
        final User record = User.builder()
                .realname(userApii.getRealname())
                .password(Md5Util.getMd5Code(UserServiceImpl.salt + userApii.getPassword()))
                .phone(userApii.getPhone())
                .walletAddress(userApii.getWalletAddress())
                .state(userApii.getState())
                .userDesc(userApii.getUserDesc())
                .id(userApii.getId())
                .build();
        final int saveUserStatus = this.userMapper.updateByPrimaryKeySelective(record);
        int userRoleStatus = 0;
        if (saveUserStatus > 0) {
            final SysUserRole sysUserRole = SysUserRole.builder()
                    .roleId(userApii.getRoleId())
                    .build();
            final SysUserRoleExample example = new SysUserRoleExample();
            final SysUserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userApii.getId());
            userRoleStatus = this.sysUserRoleMapper.updateByExampleSelective(sysUserRole, example);
            if (userRoleStatus < 1) {
                throw new ZxlException("修改用户失败！");
            }
        }
        return userRoleStatus;
    }

    @Override
    public List<UserApii> queryUsers(final Page<User> page) {
        final UserExample example = new UserExample();
        example.setOrderByClause("id DESC");
        final List<User> users = this.userMapper.selectByExampleByPage(example, page);
        return this.convertUserToUserApiis(users);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ZxlException.class)
    @Override
    public int removeUser(final int id) {
        final int removeSysUserRoleStatus = this.sysUserRoleService.removeRelation(id);
        int removeUserStatus = 0;
        if (removeSysUserRoleStatus > 0) {
            removeUserStatus = this.userMapper.deleteByPrimaryKey(id);
            if (removeSysUserRoleStatus < 1) {
                throw new ZxlException("删除用户失败");
            }
        }
        return removeUserStatus;
    }

    @Override
    public void lastLogin(final String phone) {
        final User user = User.builder()
                .lastLoginTime(LocalDateTime.now())
                .build();
        final UserExample example = new UserExample();
        final UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        try {
            this.userMapper.updateByExampleSelective(user, example);
        } catch (final Exception e) {
            UserServiceImpl.logger.info("更新最后登录时间错误: {}", e);
        }
    }


    /**
     * pojo转换方法
     *
     * @param user
     * @return
     */
    private UserApii convertUserToUserApii(final User user) {
        final SysUserRole sysUserRole = this.sysUserRoleService.querySysUserRoleByUserId(user.getId());
        final UserApii userApii = UserApii.builder()
                .id(user.getId())
                .realname(user.getRealname())
                .phone(user.getPhone())
                .password(user.getPassword())
                .walletAddress(user.getWalletAddress())
                .state(user.getState())
                .userDesc(user.getUserDesc())
                .crtTime(user.getCrtTime())
                .uptTime(user.getUptTime())
                .lastLoginTime(user.getLastLoginTime())
                .roleId(sysUserRole == null ? null : sysUserRole.getRoleId())
                .build();
        return userApii;
    }

    private List<UserApii> convertUserToUserApiis(final List<User> users) {
        final List<UserApii> userApiis = new ArrayList<>();
        users.stream().forEach(record -> {
            userApiis.add(this.convertUserToUserApii(record));
        });
        return userApiis;
    }
}
