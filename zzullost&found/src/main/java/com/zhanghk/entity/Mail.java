package com.zhanghk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Mail)实体类
 *
 * @author makejava
 * @since 2021-04-10 19:08:50
 */
public class Mail implements Serializable {
    private static final long serialVersionUID = 905091834325231569L;
    /**
     * 邮件编号，主键
     */
    private Integer id;

    /**
     * 邮件主题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 邮件创建时间
     */
    private Date ctime;
    /**
     * 邮件接收方
     */
    private Integer revId;

    /**
     * 收件人姓名
     * @return
     */
    private String revName;

    public String getRevName() {
        return revName;
    }

    public void setRevName(String revName) {
        this.revName = revName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getRevId() {
        return revId;
    }

    public void setRevId(Integer revId) {
        this.revId = revId;
    }

}