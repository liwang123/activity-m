package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.DayPoJo;
import org.trustnote.activity.common.pojo.DownloadDay;
import org.trustnote.activity.common.pojo.DownloadStatistics;
import org.trustnote.activity.common.pojo.DownloadStatisticsResult;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
public interface DownloadDayService {
   //判断当天下载
    int queryDownload(String type, LocalDate time);

    int insertDownloadDay(DownloadDay downloadDay);

    int updateDownloadDay(DownloadDay downloadDay);

    Page queryByDay(int page, int length);
}
