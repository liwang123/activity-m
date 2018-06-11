package org.trustnote.activity.skeleton.mybatis.mapper;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.DownloadChannelExample;
import org.trustnote.activity.common.pojo.DownloadChannel;

public interface DownloadChannelMapper {
    long countByExample(DownloadChannelExample example);

    int deleteByExample(DownloadChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DownloadChannel record);

    int insertSelective(DownloadChannel record);

    List<DownloadChannel> selectByExample(DownloadChannelExample example);

    DownloadChannel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DownloadChannel record, @Param("example") DownloadChannelExample example);

    int updateByExample(@Param("record") DownloadChannel record, @Param("example") DownloadChannelExample example);

    int updateByPrimaryKeySelective(DownloadChannel record);

    int updateByPrimaryKey(DownloadChannel record);

    int updateByDay(DownloadChannel record);
    int selectByType(@Param("type") String type, @Param("downloadTime") LocalDate downloadTime, @Param("channelId") int channelId);

    int selectByChannelTotal(@Param("type") String type, @Param("channelId") int channelId);
}