package com.zhanghk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghk.dao.ClaimDao;
import com.zhanghk.entity.Claim;
import com.zhanghk.entity.Post;
import com.zhanghk.service.ClaimService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Claim)表服务实现类
 *
 * @author makejava
 * @since 2021-06-05 14:09:56
 */
@Service("claimService")
public class ClaimServiceImpl implements ClaimService {
    @Resource
    private ClaimDao claimDao;

    @Override
    public PageInfo<Claim> findAll(int page, int pageSize, Claim claim) {
        PageHelper.startPage(page,pageSize);
        List<Claim> claims = claimDao.queryAll(claim);
        PageInfo<Claim> pageInfo = new PageInfo<>(claims);
        return pageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Claim queryById(Integer id) {
        return this.claimDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Claim> queryAllByLimit(int offset, int limit) {
        return this.claimDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param claim 实例对象
     * @return 实例对象
     */
    @Override
    public Claim insert(Claim claim) {
        this.claimDao.insert(claim);
        return claim;
    }

    /**
     * 修改数据
     *
     * @param claim 实例对象
     * @return 实例对象
     */
    @Override
    public Claim update(Claim claim) {
        this.claimDao.update(claim);
        return this.queryById(claim.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.claimDao.deleteById(id) > 0;
    }
}