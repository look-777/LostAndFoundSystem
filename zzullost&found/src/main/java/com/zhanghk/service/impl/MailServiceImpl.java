package com.zhanghk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghk.dao.MailDao;
import com.zhanghk.entity.Mail;
import com.zhanghk.service.MailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Mail)表服务实现类
 *
 * @author makejava
 * @since 2021-04-10 19:08:51
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
    @Resource
    private MailDao mailDao;

    @Override
    public PageInfo<Mail> queryAll(Integer page, Integer pageSize, Mail mail) {

        PageHelper.startPage(page,pageSize);
        List<Mail> mailList = mailDao.queryAll(mail);
        PageInfo<Mail> mailPageInfo = new PageInfo<>(mailList);
        return mailPageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Mail queryById(Integer id) {
        return this.mailDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    @Override
    public Mail insert(Mail mail) {
        this.mailDao.insert(mail);
        return mail;
    }

    /**
     * 修改数据
     *
     * @param mail 实例对象
     * @return 实例对象
     */
    @Override
    public Mail update(Mail mail) {
        this.mailDao.update(mail);
        return this.queryById(mail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.mailDao.deleteById(id) > 0;
    }
}