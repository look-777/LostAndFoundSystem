package com.zhanghk.service;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Comment;
import com.zhanghk.entity.Notice;

import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2021-03-23 21:00:12
 */
public interface CommentService {

    /**
     * 分页查询，支持高级查询
     */
    PageInfo<Comment> queryAll(Integer page,Integer pageSize,Comment comment);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据postId查询该启示的评论数
     * @param postId
     * @return
     */
    int getCommentCounts(Integer postId);

    //查询最新公告
    List<Comment> queryByNewTime();


}