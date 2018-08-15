package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.UserExample;
import org.trustnote.activity.common.pojo.User;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl
 */
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    List<User> selectByExampleByPage(UserExample example, Page<User> page);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}