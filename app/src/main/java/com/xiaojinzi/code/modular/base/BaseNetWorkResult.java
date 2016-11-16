package com.xiaojinzi.code.modular.base;

/**
 * Created by cxj on 2016/10/27.
 */
public class BaseNetWorkResult<T> {

    /**
     * 提示的信息
     */
    private String msgText;

    /**
     * 状态码
     */
    private Integer statusCode;

    /**
     * 数据
     */
    private T data;

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
