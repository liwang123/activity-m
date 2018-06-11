package org.trustnote.activity.skeleton.mybatis.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.trustnote.activity.common.example.DownloadDayExample;
import org.trustnote.activity.common.pojo.DownloadDay;

public interface DownloadDayMapper {
    long countByExample(DownloadDayExample example);

    int deleteByExample(DownloadDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DownloadDay record);

    int insertSelective(DownloadDay record);

    List<DownloadDay> selectByExample(DownloadDayExample example);

    DownloadDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DownloadDay record, @Param("example") DownloadDayExample example);

    int updateByExample(@Param("record") DownloadDay record, @Param("example") DownloadDayExample example);

    int updateByPrimaryKeySelective(DownloadDay record);

    int updateByPrimaryKey(DownloadDay record);

    int selectByType(@Param("type") String type, @Param("downloadTime") LocalDate downloadTime);

    int updateByDay(DownloadDay record);
}