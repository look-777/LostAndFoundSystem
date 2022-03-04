package com.zhanghk.dao;

import com.zhanghk.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Feedback)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-23 09:34:17
 */
public interface FeedbackDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Feedback queryById(Integer id);



    /**
     * 通过实体作为筛选条件查询
     *
     * @param feedback 实例对象
     * @return 对象列表
     */
    List<Feedback> queryAll(Feedback feedback);

    /**
     * 新增数据
     *
     * @param feedback 实例对象
     * @return 影响行数
     */
    int insert(Feedback feedback);



    /**
     * 修改数据
     *
     * @param feedback 实例对象
     * @return 影响行数
     */
    int update(Feedback feedback);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}