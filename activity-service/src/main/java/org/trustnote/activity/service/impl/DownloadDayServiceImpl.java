package org.trustnote.activity.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.enume.DownloadType;
import org.trustnote.activity.common.example.DownloadDayExample;
import org.trustnote.activity.common.pojo.DayPoJo;
import org.trustnote.activity.common.pojo.DownloadDay;
import org.trustnote.activity.service.iface.DownloadDayService;
import org.trustnote.activity.skeleton.mybatis.mapper.DownloadDayMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 17-12-27
 * @since v0.3
 */
@Service
public class DownloadDayServiceImpl implements DownloadDayService {
    private static final Logger logger = LogManager.getLogger(DownloadDayServiceImpl.class);
    @Resource
    private DownloadDayMapper downloadDayMapper;

    @Override
    public int queryDownload(String type, LocalDate time) {
        int i = downloadDayMapper.selectByType(type, time);
        return i;
    }

    @Override
    public int insertDownloadDay(DownloadDay downloadDay) {
        return downloadDayMapper.insert(downloadDay);
    }

    @Override
    public int updateDownloadDay(DownloadDay downloadDay) {
        return downloadDayMapper.updateByDay(downloadDay);
    }

    @Override
    public Page queryByDay(int index,int length) {
        DownloadDayExample e = new DownloadDayExample();
        e.setOrderByClause("download_time");
        List<DownloadDay> downloadDays = downloadDayMapper.selectByExample(e);
        if(downloadDays.size()==0){return null;}
        List<DayPoJo> list = new ArrayList<DayPoJo>();
        DayPoJo dayPoJo = new DayPoJo();
        for(int i=0;i<downloadDays.size();i++){
            if(i==0){dayPoJo.setTime(downloadDays.get(i).getDownloadTime());}
            if(!downloadDays.get(i).getDownloadTime().isEqual(dayPoJo.getTime())){
                dayPoJo.setSum(dayPoJo.getAndroid()+dayPoJo.getIos()+dayPoJo.getLinux()+dayPoJo.getMac()+dayPoJo.getWindows());
                list.add(dayPoJo);
                dayPoJo=new DayPoJo();
                dayPoJo.setTime(downloadDays.get(i).getDownloadTime());
            }
            if ("android".equals(downloadDays.get(i).getType())){
                dayPoJo.setAndroid(downloadDays.get(i).getSum());
            }
            else if ("ios".equals(downloadDays.get(i).getType())){
                dayPoJo.setIos(downloadDays.get(i).getSum());
            }
            else if ("windows".equals(downloadDays.get(i).getType())){
                dayPoJo.setWindows(downloadDays.get(i).getSum());
            }
            else if ("mac".equals(downloadDays.get(i).getType())){
                dayPoJo.setMac(downloadDays.get(i).getSum());
            }
            else if ("linux".equals(downloadDays.get(i).getType())){
                dayPoJo.setLinux(downloadDays.get(i).getSum());
            }
            if(i==downloadDays.size()-1){
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
        Page page=new Page<>();
        page.setResult(newList);
        page.setTotalCount(list.size());
        return page;
    }
}
