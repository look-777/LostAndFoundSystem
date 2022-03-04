package com.zhanghk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghk.dao.NoticeDao;
import com.zhanghk.entity.Notice;
import com.zhanghk.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Notice)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 11:29:56
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDao noticeDao;

    @Override
    public PageInfo<Notice> queryAll(Integer page, Integer pageSize, Notice notice) {
        PageHelper.startPage(page,pageSize);
        List<Notice> list = noticeDao.queryAll(notice);
        PageInfo<Notice> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Notice queryById(Integer id) {
        return this.noticeDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Notice notice) {
        return noticeDao.insert(notice);
    }

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice update(Notice notice) {
        this.noticeDao.update(notice);
        return this.queryById(notice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.noticeDao.deleteById(id) > 0;
    }

    @Override
    public Notice queryByNewTime() {
        return noticeDao.queryByNewTime();
    }
}