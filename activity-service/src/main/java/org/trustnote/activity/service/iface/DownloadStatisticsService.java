package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.DownloadStatistics;
import org.trustnote.activity.common.pojo.DownloadStatisticsResult;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
public interface DownloadStatisticsService {
    void recordDownload(DownloadStatistics downloadStatistics) throws Exception;

    DownloadStatisticsResult queryDownloadStatistics() throws Exception;
}
