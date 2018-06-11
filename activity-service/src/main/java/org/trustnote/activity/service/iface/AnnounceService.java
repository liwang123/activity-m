package org.trustnote.activity.service.iface;

import org.trustnote.activity.common.pojo.Announce;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import java.util.List;

/**
 * @author zhuxl 18-1-29
 * @since v0.3
 */
public interface AnnounceService {
    /**
     * 后台查询 ID倒序
     * @param page
     * @return
     * @throws Exception
     */
    List<Announce> queryAnnounceByPage(Page<Announce> page) throws Exception;

    /**
     * 官网通知公告置顶+发布时间倒序
     * @param page
     * @return
     * @throws Exception
     */
    List<Announce> queryAnnounceAndTopByPage(Page<Announce> page) throws Exception;

    /**
     * 新增一个通知公告
     * @param announce
     * @return
     * @throws Exception
     */
    int saveAnnounce(Announce announce) throws Exception;

    /**
     * 修改一个通知公告
     * @param announce
     * @return
     * @throws Exception
     */
    int updateAnnounce(Announce announce) throws Exception;

    /**
     * 删除一个通知公告
     * @param id
     * @return
     * @throws Exception
     */
    int delAnnounce(int id) throws Exception;

    /**
     * 增加浏览次数
     * @param id　主键ID
     * @param type 类型　１: cn 2 en
     * @return int >=1 success.
     * @throws Exception
     */
    int changeViewed(int id, int type) throws Exception;

    /**
     * 修改公告置顶
     * @param id 主键ID
     * @return　int >= 1 success.
     * @throws Exception
     */
    int changePlaceTop(int id) throws Exception;

    /**
     * 修改可用状态
     * @param id　主键ID
     * @param status 类型 1启动 0禁用
     * @return int >= 1 success.
     * @throws Exception
     */
    int changeAvailable(int id, int status) throws Exception;

    /**
     * 根据id获取内容
     * @param id
     * @return
     * @throws Exception
     */
    Announce getAnnounceById(int id) throws Exception;
}
