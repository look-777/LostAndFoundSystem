package com.zhanghk.service;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Feedback;

import java.util.List;

/**
 * (Feedback)表服务接口
 *
 * @author makejava
 * @since 2021-03-23 09:34:17
 */
public interface FeedbackService {

    /**
     * 分页查询（支持高级查询）
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Feedback> findAll(Integer page, Integer pageSize, Feedback feedback);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Feedback queryById(Integer id);


    /**
     * 新增数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    int insert(Feedback feedback);

    /**
     * 修改数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    Feedback update(Feedback feedback);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}