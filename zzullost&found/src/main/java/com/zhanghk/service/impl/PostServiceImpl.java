package com.zhanghk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghk.dao.PostDao;
import com.zhanghk.entity.Post;
import com.zhanghk.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Post)表服务实现类
 *
 * @author makejava
 * @since 2021-03-22 10:19:07
 */
@Service("postService")
public class PostServiceImpl implements PostService {
    @Resource
    private PostDao postDao;

    @Override
    public PageInfo<Post> findAll(Integer page, Integer pageSize, Post post) {
        PageHelper.startPage(page,pageSize);
        List<Post> posts = postDao.queryAll(post);
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        return pageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Post queryById(Integer id) {
        return this.postDao.queryInfoById(id);
    }



    /**
     * 新增数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Post post) {
        return postDao.insert(post);
    }

    /**
     * 修改数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    @Override
    public Post update(Post post) {
        this.postDao.update(post);
        return this.queryById(post.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.postDao.deleteById(id) > 0;
    }

    @Override
    public int getCountsByFlagAndStatus(Integer flag, Integer status) {
        return postDao.getCountsByFlagAndStatus(flag, status);
    }

    @Override
    public int getCountsByMon(String startTime, String endTime) {
        return postDao.getCountsByMon(startTime,endTime);
    }

    @Override
    public List<Post> recommend(Post post) {
        return postDao.recommend(post);
    }
}