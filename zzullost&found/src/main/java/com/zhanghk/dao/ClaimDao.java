package com.zhanghk.dao;

import com.zhanghk.entity.Claim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Claim)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-05 14:09:55
 */
public interface ClaimDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Claim queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Claim> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param claim 实例对象
     * @return 对象列表
     */
    List<Claim> queryAll(Claim claim);

    /**
     * 新增数据
     *
     * @param claim 实例对象
     * @return 影响行数
     */
    int insert(Claim claim);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Claim> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Claim> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Claim> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Claim> entities);

    /**
     * 修改数据
     *
     * @param claim 实例对象
     * @return 影响行数
     */
    int update(Claim claim);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}