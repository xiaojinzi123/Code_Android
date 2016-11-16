package com.xiaojinzi.code.common.bean;

/**
 * Created by cxj on 2016/11/2.
 * 评论的实体对象
 */
public class Comment {

    private Integer id;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论的时间
     */
    private long commentTime;

    /**
     * 评论的人
     */
    private User user;

    /**
     * 回复的人
     */
    private User targetUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }
}
