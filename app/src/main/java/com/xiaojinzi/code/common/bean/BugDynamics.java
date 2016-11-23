package com.xiaojinzi.code.common.bean;

/**
 * Created by xiaojinzi on 2016/10/28.
 * desc:Bug提问动态,展示在首页
 */
public class BugDynamics extends Dynamics {


    private BugComment bugComment;

    public BugComment getBugComment() {
        return bugComment;
    }

    public void setBugComment(BugComment bugComment) {
        this.bugComment = bugComment;
    }
}
