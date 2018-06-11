package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.TransactionRecordExample;
import org.trustnote.activity.common.pojo.TransactionRecord;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRecordMapper {
    long countByExample(TransactionRecordExample example);

    int deleteByExample(TransactionRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TransactionRecord record);

    int insertSelective(TransactionRecord record);

    List<TransactionRecord> selectByExample(TransactionRecordExample example);

    TransactionRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TransactionRecord record, @Param("example") TransactionRecordExample example);

    int updateByExample(@Param("record") TransactionRecord record, @Param("example") TransactionRecordExample example);

    int updateByPrimaryKeySelective(TransactionRecord record);

    int updateByPrimaryKey(TransactionRecord record);

    List<TransactionRecord> queryTransactionRecordPages(Map<String, Object> params, Page<TransactionRecord> page);

    List<TransactionRecord> queryAll(Page<TransactionRecord> page);

    List<Map<String, String>> exportTransactionRecord();
    List<TransactionRecord> selectByTransactionRecordTime(@Param("time") LocalDateTime time);


    List<TransactionRecord> queryTransactionByaddress(@Param("condition") String condition,@Param("label") String label,@Param("transactionAddress") String transactionAddress
                                                        ,@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime,@Param("offset") int offset, @Param("length") int length);


}