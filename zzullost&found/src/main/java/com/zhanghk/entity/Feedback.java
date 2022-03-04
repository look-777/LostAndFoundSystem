package com.zhanghk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Feedback)实体类
 *
 * @author makejava
 * @since 2021-03-23 09:34:16
 */
public class Feedback implements Serializable {
    private static final long serialVersionUID = 993389705859537927L;

    private Integer id;
    /**
     * 反馈用户
     */
    private String uname;
    /**
     * 反馈标题
     */
    private String title;
    /**
     * 反馈内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date ctime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

}