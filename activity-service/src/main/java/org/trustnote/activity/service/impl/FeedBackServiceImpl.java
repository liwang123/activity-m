package org.trustnote.activity.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.trustnote.activity.common.example.FeedBackExample;
import org.trustnote.activity.common.pojo.FeedBack;
import org.trustnote.activity.service.iface.FeedBackService;
import org.trustnote.activity.skeleton.mybatis.mapper.FeedBackMapper;
import org.trustnote.activity.skeleton.mybatis.orm.Page;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author zhuxl 18-2-6
 * @since v0.3
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {
    private static final Logger logger = LogManager.getLogger(FeedBackServiceImpl.class);

    @Value("${TENCENT_URL}")
    private String tencentUrl;
    @Value("${FEED_BACK}")
    private String key;
    @Resource
    private FeedBackMapper feedBackMapper;

    @Override
    public int insert(final FeedBack feedBack) {
        return this.feedBackMapper.insert(feedBack);
    }

    @Override
    public int update(final FeedBack feedBack) {
        return this.feedBackMapper.updateByPrimaryKeySelective(feedBack);
    }

    @Override
    public int delete(final int id) {
        return this.feedBackMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page select(final int index, final int length) {
        final int pageNo;
        if (index == 0) {
            pageNo = 1;
        } else {
            pageNo = (index / length) + 1;
        }
        final Page<FeedBack> page = new Page<>(pageNo, length);
        final List<FeedBack> feedBacks = this.feedBackMapper.selectByPage((index - 1) * length, length);
        for (final FeedBack feedBack : feedBacks) {
            feedBack.setScreenshots(this.tencentUrl + this.key + feedBack.getScreenshots());
        }
        page.setResult(feedBacks);
        page.setTotalCount(this.feedBackMapper.count());
        return page;
    }

    @Override
    public FeedBack selectDetail(final int id) {
        return this.feedBackMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FeedBack> listBySelect(final String email) {
        final FeedBackExample e = new FeedBackExample();
        final FeedBackExample.Criteria criteria = e.createCriteria();
        criteria.andEmailLike("%" + email + "%");
        final List<FeedBack> feedBacks = this.feedBackMapper.selectByExample(e);
        for (final FeedBack feedBack : feedBacks) {
            feedBack.setScreenshots(this.tencentUrl + this.key + feedBack.getScreenshots());
        }
        return feedBacks;
    }

    @Override
    public List<FeedBack> listAll() {
        final FeedBackExample e = new FeedBackExample();
        final List<FeedBack> feedBacks = this.feedBackMapper.selectByExample(e);
        return feedBacks;
    }
}
