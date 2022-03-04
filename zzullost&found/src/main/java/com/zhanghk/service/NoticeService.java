package com.zhanghk.service;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Notice;

import java.util.List;

/**
 * (Notice)表服务接口
 *
 * @author makejava
 * @since 2021-03-23 11:29:56
 */
public interface NoticeService {

    /**
     * 分页查询，支持高级模糊查询
     * @param page
     * @param pageSize
     * @param notice
     * @return
     */

    PageInfo<Notice> queryAll(Integer page,Integer pageSize,Notice notice);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Notice queryById(Integer id);


    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    int insert(Notice notice);

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    Notice update(Notice notice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    //查询最新公告
    Notice queryByNewTime();

}