package com.xiaojinzi.code.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaojinzi.code.mvp.v.IBaseView;
import com.xiaojinzi.viewinjection.annotation.ViewInjectionUtil;
import com.xiaojinzi.xtool.os.ProgressDialogUtil;
import com.xiaojinzi.xtool.toast.T;

/**
 * Created by cxj on 2016/1/24.
 */
public abstract class BaseViewPagerFragment extends Fragment implements IBaseView {

    public static String TAG;

    // 上下文对象
    public Context mContext = null;

    protected Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            freshUI();
            closeDialog();
        }
    };

    /**
     * 进度条对话框
     */
    private ProgressDialog dialog = null;


    /**
     * fragment使用的试图
     */
    private View view = null;

    /**
     * 是否准备好视图
     */
    protected boolean isPrepareView;

    /**
     * 是否加载了数据,这个变量的值,让子类去更改,暴露出一个更改的方法
     */
    protected boolean isLoadData;

    /**
     * 只要类没有销毁,就控制试图也只加载一次,防止多次加载浪费资源
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TAG = this.getClass().getSimpleName();

        //初始化上下文
        mContext = mContext == null ? getActivity() : mContext;

        //如果视图没有加载过,就加载试图
        if (view == null) {
            view = inflater.inflate(getLayoutId(), null);

            Object memberVariable = getMemberVariable();

            if (memberVariable == null) {
                //启动注解
                ViewInjectionUtil.injectView(this, view);
            } else {
                ViewInjectionUtil.injectView(memberVariable, this, view);
            }

            //可能需要额外的初始化控件
            initView();
            setOnlistener();
        }

        //防止有些情况下出现的那个孩子已经有爹的问题
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }

        //标记位视图准备好了
        isPrepareView = true;

        //如果是可见的,那就去加载数据
        if (isVisibleToUser) {
            //准备加载数据
            preLoadData();
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * fragment所使用的布局文件的id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化试图
     */
    public void initView() {
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    /**
     * 设置各种监听
     */
    public void setOnlistener() {
    }

    /**
     * 刷新UI,数据往控件上面显示
     */
    public void freshUI() {
    }

    protected Object getMemberVariable() {
        return null;
    }

    /**
     * 是否弹出对话框
     *
     * @return
     */
    public boolean isPopupDialog() {
        return false;
    }

    /**
     * 简化findViewById方法
     *
     * @param <T>
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T f(int viewId) {
        return (T) view.findViewById(viewId);
    }

    /* 对于用户是否可见的变量 */
    protected boolean isVisibleToUser;

    /**
     * 对于用户可见还是不可见,最先被viewpager调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //记录当前的可见状态
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && isPrepareView) {
            onUserVisible();
            preLoadData();
        }
    }

    protected void onUserVisible() {

    }


    /**
     * 准备加载数据
     */
    private void preLoadData() {
        //如果没有加载过数据
        if (isLoadData) {
            //数据往控件上面显示
            freshUI();
        } else {
            if (isPopupDialog()) {
                showDialog();
            }
            //加载数据
            initData();
        }
    }

    /**
     * 通知数据加载完毕
     */
    protected void notifyLoadDataComplete() {
        isLoadData = true;
        h.sendEmptyMessage(0);
    }

    /**
     * 弹出对话框
     */
    private void showDialog() {
        if (dialog == null) {
            dialog = ProgressDialogUtil.show(getActivity(), ProgressDialog.STYLE_SPINNER, false);
        } else {
            dialog.show();
        }
    }

    /**
     * 关闭duihuakuang
     */
    public void closeDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void tip(String content) {
        if (mContext != null && !TextUtils.isEmpty(content)) {
            T.showShort(mContext, content);
        }
    }

    @Override
    public void showDialog(String msg) {
        showDialog();
        dialog.setMessage(msg);
    }

    /**
     * 销毁视图和数据,数据的销毁需要自己复写onDestoryData实现
     */
    protected void destoryViewAndData() {
        view = null;
        mContext = null;
        isPrepareView = false;
        isLoadData = false;
        onDestoryData();
    }

    /**
     * 如果调用了销毁试图和数据方法destoryViewAndData需要重写的方法
     */
    protected void onDestoryData() {
    }

}
