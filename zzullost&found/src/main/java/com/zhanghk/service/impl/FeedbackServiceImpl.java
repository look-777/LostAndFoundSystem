package com.zhanghk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghk.dao.FeedbackDao;
import com.zhanghk.entity.Feedback;
import com.zhanghk.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Feedback)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 09:34:18
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public PageInfo<Feedback> findAll(Integer page, Integer pageSize,Feedback feedback) {
        PageHelper.startPage(page,pageSize);
        List<Feedback> feedbacks = feedbackDao.queryAll(feedback);
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbacks);
        return feedbackPageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Feedback queryById(Integer id) {
        return this.feedbackDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Feedback feedback) {

        return feedbackDao.insert(feedback);
    }

    /**
     * 修改数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    @Override
    public Feedback update(Feedback feedback) {
        this.feedbackDao.update(feedback);
        return this.queryById(feedback.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.feedbackDao.deleteById(id) > 0;
    }
}