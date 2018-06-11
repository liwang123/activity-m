package org.trustnote.activity.service.iface;


import org.trustnote.activity.common.pojo.DayPoJo;
import org.trustnote.activity.common.pojo.DownloadChannel;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.time.LocalDate;

public interface DownloadChannelService {

    int insert(DownloadChannel downloadChannel);

    int update(DownloadChannel downloadChannel);

    int queryDownload(String type, LocalDate time,int id);

    Page queryByChannel(int page, int length,int channelId,String channelName);

    DayPoJo queryByChannelTotal(int channelId,String channelName);

}
