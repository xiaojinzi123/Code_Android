package com.xiaojinzi.code.common.bean;

/**
 * Created by xiaojinzi on 2016/10/28.
 * desc:博文动态,展示在发现页面
 */
public class BlogDynamics extends Dynamics {



    /**
     * 动态的类别<br/>
     * 0:原创<br/>
     * 1:引用<br/>
     */
    private Integer tag1;

    public Integer getTag1() {
        return tag1;
    }

    public void setTag1(Integer tag1) {
        this.tag1 = tag1;
    }

}
