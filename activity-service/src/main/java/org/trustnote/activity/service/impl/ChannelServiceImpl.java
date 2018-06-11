package org.trustnote.activity.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.ChannelExample;
import org.trustnote.activity.common.pojo.Channel;
import org.trustnote.activity.common.utils.Utils;
import org.trustnote.activity.service.iface.ChannelService;
import org.trustnote.activity.skeleton.mybatis.mapper.ChannelMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    private static final Logger logger = LogManager.getLogger(ChannelServiceImpl.class);

    @Resource
    private ChannelMapper channelMapper;

    @Override
    public int insert(Channel channel) {
        channel.setCrtTime(LocalDateTime.now());
        String random= Utils.RandomString(8);
        channel.setCode(random);
        return channelMapper.insertSelective(channel);
    }

    @Override
    public int update(Channel channel) {
        channel.setUptTime(LocalDateTime.now());
        return channelMapper.updateByPrimaryKeySelective(channel);
    }

    @Override
    public Page selectAll(int page, int length) {
        Page<Channel> pg = new Page<Channel>();
        List<Channel> channelList = channelMapper.selectByPage((page - 1) * length, length);
        pg.setResult(channelList);
        pg.setTotalCount(channelMapper.selectAll());
        return pg;
    }

    @Override
    public Channel selectByCode(String code) {
        ChannelExample e = new ChannelExample();
        e.or().andCodeEqualTo(code);
        List<Channel> channels = channelMapper.selectByExample(e);
        if(channels.size()!=0){return channels.get(0);}
        return null;
    }

    @Override
    public List<Channel> queryAll() {
        ChannelExample e = new ChannelExample();
        return channelMapper.selectByExample(e);
    }

    @Override
    public String queryOne() {
        ChannelExample e = new ChannelExample();
        e.or().andNameEqualTo("官网首页渠道");
        List<Channel> list = channelMapper.selectByExample(e);
        if(list.size()!=0){return list.get(0).getCode();}
        return null;
    }

    @Override
    public int queryByName(String name) {
        ChannelExample e = new ChannelExample();
        e.or().andNameEqualTo(name);
        List<Channel> list = channelMapper.selectByExample(e);
        if(list.size()!=0){return 1;}
        return 0;
    }
}
