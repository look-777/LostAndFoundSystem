package com.zhanghk.service;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Mail;

import java.util.List;

/**
 * (Mail)表服务接口
 *
 * @author makejava
 * @since 2021-04-10 19:08:51
 */
public interface MailService {


    PageInfo<Mail> queryAll(Integer page,Integer pageSize,Mail mail);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Mail queryById(Integer id);


    /**
     * 新增数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    Mail insert(Mail mail);

    /**
     * 修改数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    Mail update(Mail mail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}