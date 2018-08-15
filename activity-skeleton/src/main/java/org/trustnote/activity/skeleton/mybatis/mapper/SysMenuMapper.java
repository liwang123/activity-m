package org.trustnote.activity.skeleton.mybatis.mapper;


import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.SysMenuExample;
import org.trustnote.activity.common.pojo.SysMenu;
import org.trustnote.activity.common.pojo.SysMenuEx;

import java.util.List;

public interface SysMenuMapper {
    long countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 根据用户ID查询角色菜单权限
     *
     * @param userId
     * @return
     */
    List<SysMenu> findMenuByUserId(int userId);

    /**
     * 根据角色ID 查询根节点信息
     *
     * @param roleId
     * @return
     */
    List<SysMenuEx> selectRootMenu(Integer roleId);

    /**
     * 根据PID，角色ID查询子节点信息
     *
     * @param pid
     * @param roleId
     * @return
     */
    List<SysMenuEx> selectChildMenu(Integer pid, Integer roleId);
}