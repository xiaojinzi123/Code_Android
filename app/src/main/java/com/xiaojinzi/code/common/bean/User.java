package com.xiaojinzi.code.common.bean;

/**
 * Created by cxj on 2016/10/27.
 * 用户的信息
 */
public class User {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * App登陆之后的令牌
     */
    private String clientToken;

    /**
     * 用户的简介
     */
    private String description;

    /**
     * 经验值,通过每日登录而来,每日登录获取的经验值后面再定
     * 也可以通过回答别人的问题获得最佳答案而得到楼主发布问题所花费的经验值
     * 然后相应的就会出现等级,等级这个数据就是由这个经验值计算而来,客户端需要计算,计算方式如下:
     * 1-2 100经验值
     * 2-3 200经验值
     * 3-4 500经验值
     * 4-5 1000经验值
     * 5-6 2000经验值
     * 6-7 5000经验值
     * 7-8 10000经验值
     */
    private int xp;

    /**
     * 性别<br/>
     * 0:男性<br/>
     * 1:女性
     */
    private String sex;

    /* 密码 */
    private String password;

    /* 电话号码 */
    private String phoneNumber;

    /* 头像地址,有默认地址 */
    private String avatarAddress;

    /**
     * 用户名
     */
    private String name;

    public User() {
    }

    public User(Integer id, String description, String avatarAddress, String name) {
        this.id = id;
        this.description = description;
        this.avatarAddress = avatarAddress;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarAddress() {
        return avatarAddress;
    }

    public void setAvatarAddress(String avatarAddress) {
        this.avatarAddress = avatarAddress;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
