package com.zhanghk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Claim)实体类
 *
 * @author makejava
 * @since 2021-06-05 14:09:52
 */
public class Claim implements Serializable {
    private static final long serialVersionUID = 967808805171739322L;

    private Integer id;

    private Integer postId;

    private Integer userId;

    private String postTitle;

    private String userName;

    private Date claimTime;


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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

}