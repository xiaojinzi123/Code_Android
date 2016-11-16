package com.xiaojinzi.code.common.bean;

/**
 * Created by xiaojinzi on 2016/10/28.
 * desc:动态,展示在首页
 */
public class Dynamics {


    /**
     * 动态的类型,纯文本
     */
    public static final int NO_TYPE_DYNAMICS = 0;

    /**
     * 动态的类型,带有图,图片可能有多张
     */
    public static final int IMAGE_TYPE_DYNAMICS = 1;

    /**
     * 多张图片路径的分隔符
     */
    public static final String IMAGE_SPLIT_CHAR = ";";


    /* 主键id */
    private Integer id;

    /**
     * 动态类型<br/>
     * 1:表示展示图片的{@link Dynamics#IMAGE_TYPE_DYNAMICS}<br/>
     * 这个数值不是由客户端传过来的时候,而是服务端自己判断然后赋值的<br/>
     */
    private Integer dynamicsType = NO_TYPE_DYNAMICS;

    /**
     * 发布动态的人
     */
    private User user;

    /**
     * 动态的图片,每个图片用";"隔开,一个变量表示多个图片地址
     */
    private String images;

    /**
     * 技术标签,比如:
     * Java
     * Php
     * Python
     */
    private ProLan proLan;

    /**
     * 内容的标题
     */
    private String title;

    /**
     * 发布的动态的内容
     */
    private String content;

    /**
     * 发布的时间
     */
    private long postTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getDynamicsType() {
        return dynamicsType;
    }

    public void setDynamicsType(Integer dynamicsType) {
        this.dynamicsType = dynamicsType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProLan getProLan() {
        return proLan;
    }

    public void setProLan(ProLan proLan) {
        this.proLan = proLan;
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

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }
}
