package org.trustnote.activity.service.iface;


import org.trustnote.activity.common.pojo.FeedBack;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
public interface FeedBackService {

    int insert(FeedBack feedBack);

    int update(FeedBack feedBack);

    int delete(int id);

    Page select(int index, int length);

    FeedBack selectDetail(int id);

    List<FeedBack> listBySelect(String email);

    List<FeedBack> listAll();

}
