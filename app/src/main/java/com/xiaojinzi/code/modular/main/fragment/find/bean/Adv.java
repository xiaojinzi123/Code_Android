package com.xiaojinzi.code.modular.main.fragment.find.bean;

/**
 * Created by cxj on 2016/8/17.
 */
public class Adv {

    /**
     * 流水id
     */
    private Integer id;

    /**
     * 广告图片地址
     */
    private String image;

    /**
     * 链接地址
     */
    private String href;

    /**
     * 简单的描述
     */
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
