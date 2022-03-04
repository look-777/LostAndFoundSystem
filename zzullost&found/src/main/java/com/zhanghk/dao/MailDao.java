package com.zhanghk.dao;

import com.zhanghk.entity.Mail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Mail)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-10 19:08:50
 */
public interface MailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Mail queryById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param mail 实例对象
     * @return 对象列表
     */
    List<Mail> queryAll(Mail mail);

    /**
     * 新增数据
     *
     * @param mail 实例对象
     * @return 影响行数
     */
    int insert(Mail mail);


    /**
     * 修改数据
     *
     * @param mail 实例对象
     * @return 影响行数
     */
    int update(Mail mail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}