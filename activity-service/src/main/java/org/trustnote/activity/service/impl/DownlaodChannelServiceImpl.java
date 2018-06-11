package org.trustnote.activity.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.ChannelExample;
import org.trustnote.activity.common.example.DownloadChannelExample;
import org.trustnote.activity.common.pojo.Channel;
import org.trustnote.activity.common.pojo.DayPoJo;
import org.trustnote.activity.common.pojo.DownloadChannel;
import org.trustnote.activity.service.iface.DownloadChannelService;
import org.trustnote.activity.skeleton.mybatis.mapper.ChannelMapper;
import org.trustnote.activity.skeleton.mybatis.mapper.DownloadChannelMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class DownlaodChannelServiceImpl implements DownloadChannelService {
    private static final Logger logger = LogManager.getLogger(DownlaodChannelServiceImpl.class);

    @Resource
    private DownloadChannelMapper downloadChannelMapper;
    @Resource
    private ChannelMapper channelMapper;

    @Override
    public int insert(DownloadChannel downloadChannel) {
        return downloadChannelMapper.insert(downloadChannel);
    }

    @Override
    public int update(DownloadChannel downloadChannel) {
        return downloadChannelMapper.updateByDay(downloadChannel);
    }

    @Override
    public int queryDownload(String type, LocalDate time, int id) {
        return downloadChannelMapper.selectByType(type,time,id);
    }

    @Override
    public Page queryByChannel(int index,int length,int channelId,String channelName) {
        Page page=new Page<>();
        if(0==channelId){
            ChannelExample channelExample = new ChannelExample();
            channelExample.or().andNameEqualTo(channelName);
            List<Channel> channels = channelMapper.selectByExample(channelExample);
            if(channels.size()==0){
                page.setTotalCount(-1);
                logger.info("queryByChannel{}channel is not exist");
                return page;
            }
            channelId=channels.get(0).getId();
        }
        DownloadChannelExample e = new DownloadChannelExample();
        e.or().andChannelIdEqualTo(channelId);
        e.setOrderByClause("download_time");
        List<DownloadChannel> downloadChannelList = downloadChannelMapper.selectByExample(e);
        if(downloadChannelList.size()==0){
            logger.info("Channel:"+channelId+"No list");return null;}
        List<DayPoJo> list = new ArrayList<DayPoJo>();
        DayPoJo dayPoJo = new DayPoJo();
        for(int i=0;i<downloadChannelList.size();i++){
            logger.info("Channel:{} Have list",channelId);
            if(i==0){dayPoJo.setTime(downloadChannelList.get(i).getDownloadTime());}
            if(!downloadChannelList.get(i).getDownloadTime().isEqual(dayPoJo.getTime())){
                dayPoJo.setSum(dayPoJo.getAndroid()+dayPoJo.getIos()+dayPoJo.getLinux()+dayPoJo.getMac()+dayPoJo.getWindows());
                list.add(dayPoJo);
                dayPoJo=new DayPoJo();
                dayPoJo.setTime(downloadChannelList.get(i).getDownloadTime());
            }
            if ("android".equals(downloadChannelList.get(i).getType())){
                dayPoJo.setAndroid(downloadChannelList.get(i).getSum());
            }
            else if ("ios".equals(downloadChannelList.get(i).getType())){
                dayPoJo.setIos(downloadChannelList.get(i).getSum());
            }
            else if ("windows".equals(downloadChannelList.get(i).getType())){
                dayPoJo.setWindows(downloadChannelList.get(i).getSum());
            }
            else if ("mac".equals(downloadChannelList.get(i).getType())){
                dayPoJo.setMac(downloadChannelList.get(i).getSum());
            }
            else if ("linux".equals(downloadChannelList.get(i).getType())){
                dayPoJo.setLinux(downloadChannelList.get(i).getSum());
            }
            if(i==downloadChannelList.size()-1){
                dayPoJo.setSum(dayPoJo.getAndroid()+dayPoJo.getIos()+dayPoJo.getLinux()+dayPoJo.getMac()+dayPoJo.getWindows());
                list.add(dayPoJo);
            }
        }
        List newList=new ArrayList<DayPoJo>();
        List<DayPoJo> subList = list.subList(length * (index - 1), ((length * index) > list.size() ? list.size() : (length * index)));
        for (DayPoJo order : subList
                ) {
            newList.add(order);
        }
        page.setResult(newList);
        page.setTotalCount(list.size());
        return page;
    }

    @Override
    public DayPoJo queryByChannelTotal(int channelId,String channelName) {
        DayPoJo dayPoJo = new DayPoJo();
        if(0==channelId){
            ChannelExample channelExample = new ChannelExample();
            channelExample.or().andNameEqualTo(channelName);
            List<Channel> channels = channelMapper.selectByExample(channelExample);
            if(channels.size()==0){
                logger.info("queryByChannelTotal{}channel is not exist");
                dayPoJo.setSum(-1);
                return dayPoJo;
            }
            channelId=channels.get(0).getId();
        }
        dayPoJo.setLinux(downloadChannelMapper.selectByChannelTotal("linux", channelId));
        dayPoJo.setMac(downloadChannelMapper.selectByChannelTotal("mac", channelId));
        dayPoJo.setWindows(downloadChannelMapper.selectByChannelTotal("windows", channelId));
        dayPoJo.setAndroid(downloadChannelMapper.selectByChannelTotal("android", channelId));
        dayPoJo.setIos(downloadChannelMapper.selectByChannelTotal("ios", channelId));
        dayPoJo.setSum(dayPoJo.getAndroid()+dayPoJo.getWindows()+dayPoJo.getMac()+dayPoJo.getIos()+dayPoJo.getLinux());
        return dayPoJo;
    }


}
