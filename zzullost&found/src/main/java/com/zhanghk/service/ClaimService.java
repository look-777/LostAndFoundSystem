package com.zhanghk.service;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Claim;
import com.zhanghk.entity.Type;

import java.util.List;

/**
 * (Claim)表服务接口
 *
 * @author makejava
 * @since 2021-06-05 14:09:56
 */
public interface ClaimService {


    /**
     * 分页查询
     */
    PageInfo<Claim> findAll(int page, int pageSize, Claim claim);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Claim queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Claim> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param claim 实例对象
     * @return 实例对象
     */
    Claim insert(Claim claim);

    /**
     * 修改数据
     *
     * @param claim 实例对象
     * @return 实例对象
     */
    Claim update(Claim claim);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}