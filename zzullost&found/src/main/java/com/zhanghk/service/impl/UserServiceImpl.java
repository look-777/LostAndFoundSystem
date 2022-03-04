package com.zhanghk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghk.dao.UserDao;
import com.zhanghk.entity.User;
import com.zhanghk.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-03-17 09:53:42
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<User> findAll(Integer page, Integer pageSize, User user) {
        PageHelper.startPage(page,pageSize);
        List<User> userList = userDao.queryAll(user);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public int getUserNum() {
        return userDao.getUserNum();
    }

    @Override
    public int getMaleNum() {
        return userDao.getMaleNum();
    }

    @Override
    public int getAdminNum() {
        return userDao.getAdminNum();
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }
}