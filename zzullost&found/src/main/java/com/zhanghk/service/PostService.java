package com.zhanghk.service;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * (Post)表服务接口
 *
 * @author makejava
 * @since 2021-03-22 10:19:06
 */
public interface PostService {


    /**
     * 分页查询
     */
    PageInfo<Post> findAll(Integer page, Integer pageSize,Post post);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Post queryById(Integer id);


    /**
     * 新增数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    int insert(Post post);

    /**
     * 修改数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    Post update(Post post);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    //根据状态查询记录数
    int getCountsByFlagAndStatus(@Param("flag")Integer flag, @Param("status") Integer status);

    //根据月份统计每个月的启示数量，用以在前端绘制折线图
    int getCountsByMon(String startTime,String endTime);

    List<Post> recommend(Post post);

}