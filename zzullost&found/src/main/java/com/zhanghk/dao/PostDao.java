package com.zhanghk.dao;

import com.zhanghk.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Post)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-22 10:19:06
 */
public interface PostDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Post queryInfoById(Integer id);


    /**
     * 通过实体作为筛选条件查询--- 多条件查询
     *
     * @param post 实例对象
     * @return 对象列表
     */
    List<Post> queryAll(Post post);

    /**
     * 新增数据
     *
     * @param post 实例对象
     * @return 影响行数
     */
    int insert(Post post);



    /**
     * 修改数据
     *
     * @param post 实例对象
     * @return 影响行数
     */
    int update(Post post);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    //根据状态查询记录数
    int getCountsByFlagAndStatus(@Param("flag")Integer flag,@Param("status") Integer status);

    //根据月份统计每个月的启示数量，用以在前端绘制折线图
    int getCountsByMon(String startTime,String endTime);

    //用户推荐
    List<Post> recommend(Post post);

}