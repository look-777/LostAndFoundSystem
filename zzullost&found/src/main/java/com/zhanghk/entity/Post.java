package com.zhanghk.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Post)实体类
 *
 * @author makejava
 * @since 2021-03-22 10:19:06
 */
public class Post implements Serializable {
    private static final long serialVersionUID = 700802624723052962L;

    private Integer id;
    /**
     * 发帖人
     */
    private String uname;
    /**
     * 分类
     */
    private String typeName;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片
     */
    private String picture;
    /**
     * 地址
     */
    private String address;
    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 状态：1为进行中，0为结束
     */
    private Integer status;
    /**
     * 标记：1为失物招领，0为寻物启事
     */
    private Integer flag;
    /**
     * 查看次数
     */
    private Integer viewCount;
    /**
     * 评论数
     */
    private Integer counts;

    /**
     * 添加用户对象
     */
    private User user;

    /**
     * 每个启示下面的评论
     */
    private List<Comment> commentList;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }




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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}