package com.xiaojinzi.code.mvp.v;

/**
 * Created by cxj on 2016/10/27.
 */
public interface IBaseView {

    /**
     * 提示信息
     *
     * @param content
     */
    void tip(String content);

    /**
     * 弹出对话框
     *
     * @param msg
     */
    void showDialog(String msg);

    /**
     * 关闭对话框
     */
    void closeDialog();

}
