package com.xiaojinzi.code.modular.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.autolayout.AutoLayoutActivity;
import com.xiaojinzi.code.mvp.v.IBaseView;
import com.xiaojinzi.viewinjection.annotation.ViewInjectionUtil;
import com.xiaojinzi.xtool.toast.T;

/**
 * Created by cxj on 2016/10/27.
 */
public class BaseAct extends AutoLayoutActivity implements IBaseView, View.OnClickListener {

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

    protected Object getMemberVariable() {
        return null;
    }

    protected void initLinstener() {

    }

    protected void initData() {

    }

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
