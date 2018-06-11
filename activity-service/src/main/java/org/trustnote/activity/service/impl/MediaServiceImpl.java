package org.trustnote.activity.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.MediaExample;
import org.trustnote.activity.common.pojo.Media;
import org.trustnote.activity.service.iface.MediaService;
import org.trustnote.activity.skeleton.mybatis.mapper.MediaMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class MediaServiceImpl implements MediaService {
    private static final Logger logger = LogManager.getLogger(MediaServiceImpl.class);

    @Value("${TENCENT_URL}")
    private String tencentUrl;
    @Value("${MEDIA_KEY}")
    private String key;
    @Resource
    private MediaMapper mediaMapper;

    @Override
    public int insert(final Media media) {
        media.setStatus(2);
        return this.mediaMapper.insert(media);
    }

    @Override
    public int update(final Media media) {
        return this.mediaMapper.updateByPrimaryKeySelective(media);
    }

    @Override
    public int delete(final int id) {
        return this.mediaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page select(final int index, final int length, final int status) {
        final int pageNo;
        if (index == 0) {
            pageNo = 1;
        } else {
            pageNo = (index / length) + 1;
        }
        final Page<Media> page = new Page<>(pageNo, length);
        final MediaExample example = new MediaExample();
        final MediaExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        if (status == 1) {
            example.setOrderByClause("queue DESC,upt_time DESC");
        } else if (status == 2) {
            example.setOrderByClause("upt_time DESC");
        }
        final List<Media> medias = this.mediaMapper.selectByExample(example, page);
        for (final Media media : medias) {
            media.setImageUrl(this.tencentUrl + this.key + media.getImageUrl());
        }
        page.setResult(medias);
        page.setTotalCount(this.mediaMapper.count(status));
        return page;
    }

    @Override
    public Media selectDetail(final int id) {
        return this.mediaMapper.selectByPrimaryKey(id);
    }

    @Override
    public int count(final int status) {
        return this.mediaMapper.count(status);
    }

    @Override
    public int updateStatus(final int id, final int status) {
        final Media media = new Media();
        media.setId(id);
        media.setStatus(status);
        media.setUptTime(LocalDateTime.now());
        if (status == 1) {
            final int maxQueue = this.mediaMapper.queryMaxQueue(status);
            media.setQueue(maxQueue + 1);
        }
        return this.mediaMapper.updateByPrimaryKeySelective(media);
    }

    @Override
    public int updateQueue(final int id, final int queue) {
        final Media media = new Media();
        media.setId(id);
        media.setQueue(queue);
        media.setUptTime(LocalDateTime.now());
        return this.mediaMapper.updateByPrimaryKeySelective(media);
    }
}
