package org.trustnote.activity.service.iface;


import org.trustnote.activity.common.pojo.Media;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
public interface MediaService {

    int insert(Media media);

    int update(Media media);

    int delete(int id);

    Page select(int index, int length, int status);

    Media selectDetail(int id);

    int count(int status);

    int updateStatus(int id, int status);

    int updateQueue(int id, int queue);
}
