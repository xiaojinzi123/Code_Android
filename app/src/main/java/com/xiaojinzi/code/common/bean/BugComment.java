package com.xiaojinzi.code.common.bean;

/**
 * Created by cxj on 2016/11/4.
 */
public class BugComment extends Comment {

    /**
     * 是否是正确答案
     */
    private boolean isAnswer;

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }
}
