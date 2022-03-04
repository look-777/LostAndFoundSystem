package com.zhanghk.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2021-03-23 21:00:10
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 249877537160219933L;

    private Integer id;
    /**
     * 贴子ID
     */
    private Integer postId;
    /**
     * 评论人
     */
    private String uname;
    /**
     * 内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date ctime;
    /**
     * 是否有父评论
     */
    private Integer parent;

    /**
     * 评论的评论
     */
    private List<Comment> childlist;


    public List<Comment> getChildlist() {
        return childlist;
    }

    public void setChildlist(List<Comment> childlist) {
        this.childlist = childlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

}