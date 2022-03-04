package com.zhanghk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Notice)实体类
 *
 * @author makejava
 * @since 2021-03-23 11:29:56
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = -19739572884884594L;

    private Integer id;
    /**
     * 创建人
     */
    private String cuser;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 通知标题
     */
    private String title;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}