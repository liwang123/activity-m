package org.trustnote.activity.skeleton.mybatis.mapper;


import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.SysRoleMenuExample;
import org.trustnote.activity.common.pojo.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuMapper {
    long countByExample(SysRoleMenuExample example);

    int deleteByExample(SysRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    List<SysRoleMenu> selectByExample(SysRoleMenuExample example);

    SysRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRoleMenu record, @Param("example") SysRoleMenuExample example);

    int updateByExample(@Param("record") SysRoleMenu record, @Param("example") SysRoleMenuExample example);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
}