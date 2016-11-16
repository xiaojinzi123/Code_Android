package com.xiaojinzi.code.modular.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.autolayout.AutoLayoutFragmentActivity;
import com.xiaojinzi.code.mvp.v.IBaseView;
import com.xiaojinzi.viewinjection.annotation.ViewInjectionUtil;
import com.xiaojinzi.xtool.toast.T;

/**
 * Created by cxj on 2016/10/27.
 */
public class BaseFragmentAct extends AutoLayoutFragmentActivity implements IBaseView, View.OnClickListener {

    protected ImageView iv_back;
    protected TextView tv_back_name;
    protected TextView tv_title_name;

    /**
     * 上下文
     */
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        Object memberVariable = getMemberVariable();

        if (memberVariable == null) {
            //启动注解
            ViewInjectionUtil.injectView(this);
        } else {
            ViewInjectionUtil.injectView(memberVariable, this, this);
        }


        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_back_name = (TextView) findViewById(R.id.tv_back_name);
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);
        if (iv_back != null) {
            iv_back.setOnClickListener(this);
        }
        if (tv_back_name != null) {
            tv_back_name.setOnClickListener(this);
        }

        initView();

        initData();

        initLinstener();

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    /**
     * 获取需要注入属性的对象
     *
     * @return 返回需要注入属性的对象
     */
    protected Object getMemberVariable() {
        return null;
    }

    /**
     * 注册各种监听
     */
    protected void initLinstener() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化视图
     */
    protected void initView() {
    }


    @Override
    public void tip(String content) {
        T.showShort(mContext, content);
    }

    @Override
    public void showDialog(String msg) {
    }

    @Override
    public void closeDialog() {
    }

    @Override
    public void onClick(View view) {
        finish();
    }

}
