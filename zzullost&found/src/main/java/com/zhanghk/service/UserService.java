package com.zhanghk.service;


import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-03-17 09:53:42
 */
public interface UserService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param user
     * @return
     */

     PageInfo<User> findAll(Integer page,Integer pageSize,User user);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 统计注册用户数量，管理后台显示
     * @return
     */
    int getUserNum();

    /**
     * 统计性别为男生的用户数
     * @return
     */
    int getMaleNum();

    /**
     * 统计管理员人数
     */
    int getAdminNum();

    /*
    根据用户名找用户
     */
    User findUserByName(String username);

}