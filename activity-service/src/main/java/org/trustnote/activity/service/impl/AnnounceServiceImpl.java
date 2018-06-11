package org.trustnote.activity.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.trustnote.activity.common.example.AnnounceExample;
import org.trustnote.activity.common.pojo.Announce;
import org.trustnote.activity.service.iface.AnnounceService;
import org.trustnote.activity.skeleton.mybatis.mapper.AnnounceMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxl 18-1-29
 * @since v0.3
 */
@Service
public class AnnounceServiceImpl implements AnnounceService {
    private static final Logger logger = LogManager.getLogger(AnnounceServiceImpl.class);
    @Resource
    private AnnounceMapper announceMapper;

    @Override
    public List<Announce> queryAnnounceByPage(Page<Announce> page) throws Exception {
        AnnounceExample example = new AnnounceExample();
        example.setOrderByClause("id DESC");
        List<Announce> announces = announceMapper.selectByExampleAndPage(example, page);
        if (CollectionUtils.isEmpty(announces)) return null;
        return announces;
    }

    @Override
    public List<Announce> queryAnnounceAndTopByPage(Page<Announce> page) throws Exception {
        AnnounceExample example = new AnnounceExample();
        AnnounceExample.Criteria criteria = example.createCriteria();
        criteria.andAvailableEqualTo(1);
        example.setOrderByClause("placed_top desc,release_time desc");
        List<Announce> announces = announceMapper.selectByExampleAndPage(example, page);
        if (CollectionUtils.isEmpty(announces)) return null;
        return announces;
    }

    @Override
    public int saveAnnounce(Announce announce) throws Exception {
        LocalDateTime nowTime = LocalDateTime.now();
        announce.setReleaseTime(nowTime);
        announce.setLastModifed(nowTime);
        int saveResult = announceMapper.insertSelective(announce);
        if (announce.getPlacedTop().intValue() == 1) {
            this.changePlaceTop(announce.getId());
        }
        return saveResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int updateAnnounce(Announce announce) throws Exception {
        announce.setLastModifed(LocalDateTime.now());
        if (announce.getPlacedTop() == 1) {
            this.changePlaceTop(announce.getId());
        }
        return announceMapper.updateByPrimaryKeySelective(announce);
    }

    @Override
    public int delAnnounce(int id) throws Exception {
        return announceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int changeViewed(int id, int type) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);
        return announceMapper.changeViewed(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int changePlaceTop(int id) throws Exception {
        //first- Take back the original top data.
        AnnounceExample example = new AnnounceExample();
        AnnounceExample.Criteria criteria = example.createCriteria();
        criteria.andPlacedTopEqualTo(1);

        Announce announce = new Announce();
        announce.setPlacedTop(0);
        announceMapper.updateByExampleSelective(announce, example);

        //next- Modify the current record as the top.
        Announce announceC = new Announce();
        announceC.setId(id);
        announceC.setPlacedTop(1);
        return announceMapper.updateByPrimaryKeySelective(announceC);
    }

    @Override
    public int changeAvailable(int id, int status) throws Exception {
        Announce announce = new Announce();
        announce.setId(id);
        announce.setAvailable(status);
        return announceMapper.updateByPrimaryKeySelective(announce);
    }

    @Override
    public Announce getAnnounceById(int id) throws Exception {
        return announceMapper.selectByPrimaryKey(id);
    }
}
