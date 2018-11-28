package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.DepositLockExample;
import org.trustnote.activity.common.pojo.DepositLock;

import java.util.List;

public interface DepositLockMapper {
    long countByExample(DepositLockExample example);

    int deleteByExample(DepositLockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DepositLock record);

    int insertSelective(DepositLock record);

    List<DepositLock> selectByExample(DepositLockExample example);

    DepositLock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepositLock record, @Param("example") DepositLockExample example);

    int updateByExample(@Param("record") DepositLock record, @Param("example") DepositLockExample example);

    int updateByPrimaryKeySelective(DepositLock record);

    int updateByPrimaryKey(DepositLock record);
}