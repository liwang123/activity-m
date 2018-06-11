package org.trustnote.activity.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.pojo.DownloadStatistics;
import org.trustnote.activity.common.pojo.DownloadStatisticsResult;
import org.trustnote.activity.service.iface.DownloadStatisticsService;
import org.trustnote.activity.skeleton.mybatis.mapper.DownloadStatisticsMapper;

import javax.annotation.Resource;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Service
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService {
    private static final Logger logger = LogManager.getLogger(DownloadStatisticsServiceImpl.class);
    @Resource
    private DownloadStatisticsMapper downloadStatisticsMapper;

    @Override
    public void recordDownload(DownloadStatistics downloadStatistics) throws Exception {
        logger.info("downloadStatistics: {}", JSON.toJSONString(downloadStatistics));
        downloadStatisticsMapper.updateByDownloadType(downloadStatistics);
    }

    @Override
    public DownloadStatisticsResult queryDownloadStatistics() throws Exception {
        DownloadStatistics downloadStatistics = downloadStatisticsMapper.selectDownloadStatistics();
        if (downloadStatistics == null) return null;
        long sum = downloadStatisticsMapper.totalChannel();
        DownloadStatisticsResult downloadStatisticsResult = new DownloadStatisticsResult();
        BeanUtils.copyProperties(downloadStatisticsResult, downloadStatistics);
        downloadStatisticsResult.setSum(sum);
        return downloadStatisticsResult;
    }
}
