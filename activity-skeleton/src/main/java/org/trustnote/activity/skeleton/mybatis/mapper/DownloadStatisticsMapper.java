package org.trustnote.activity.skeleton.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.DownloadStatisticsExample;
import org.trustnote.activity.common.pojo.DownloadStatistics;

public interface DownloadStatisticsMapper {
    long countByExample(DownloadStatisticsExample example);

    int deleteByExample(DownloadStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DownloadStatistics record);

    int insertSelective(DownloadStatistics record);

    List<DownloadStatistics> selectByExample(DownloadStatisticsExample example);

    DownloadStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DownloadStatistics record, @Param("example") DownloadStatisticsExample example);

    int updateByExample(@Param("record") DownloadStatistics record, @Param("example") DownloadStatisticsExample example);

    int updateByPrimaryKeySelective(DownloadStatistics record);

    int updateByPrimaryKey(DownloadStatistics record);

    long totalChannel();

    int updateByDownloadType(@Param("record") DownloadStatistics record);

    DownloadStatistics selectDownloadStatistics();
}