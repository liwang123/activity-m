package org.trustnote.activity.skeleton.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.InviteRecordExample;
import org.trustnote.activity.common.pojo.InviteRecord;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface InviteRecordMapper {
    long countByExample(InviteRecordExample example);

    int deleteByExample(InviteRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InviteRecord record);

    int insertSelective(InviteRecord record);

    List<InviteRecord> selectByExample(InviteRecordExample example);

    InviteRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InviteRecord record, @Param("example") InviteRecordExample example);

    int updateByExample(@Param("record") InviteRecord record, @Param("example") InviteRecordExample example);

    int updateByPrimaryKeySelective(InviteRecord record);

    int updateByPrimaryKey(InviteRecord record);

    int updateInviteRecord(InviteRecord record);

    List<InviteRecord> queryInviteRecordPages(Page<InviteRecord> page, InviteRecordExample example);

    int updateRewardTtnAndSeveral(Map<String, Object> map);

    List<Map<String,String>> exportInviteRecord();

    List<InviteRecord> selectByselective(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
                                          @Param("condition") String condition,@Param("size") int size,@Param("label") String label
            ,@Param("offset") int offset, @Param("length") int length, @Param("flag") boolean flag, @Param("num") int num);
    //排行榜
    List<InviteRecord> selectByOrder(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("type") int type);

    List<InviteRecord> selectByMobile(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
                                      @Param("mobile") String mobile,@Param("adress") String adress);

    List<InviteRecord> selectByInviteCode(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
                                         @Param("inviteCode") String inviteCode);

    List<InviteRecord> selectByTime(@Param("time") LocalDateTime time);


    List<InviteRecord> selectByaddress(@Param("trustNoteAddress") String trustNoteAddress,@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
                                 @Param("label") String label);



}