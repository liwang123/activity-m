package org.trustnote.activity.service.iface;


import org.trustnote.activity.common.pojo.Activity;
import org.trustnote.activity.common.pojo.Channel;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

public interface ChannelService {

    int insert(Channel channel);

    int update(Channel channel);

    Page selectAll(int page, int length);

    Channel selectByCode(String code);

    List<Channel> queryAll();

    String queryOne();

    int queryByName(String name);


}
