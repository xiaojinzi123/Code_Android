package com.xiaojinzi.code.modular.main.view;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.AppInfo;
import com.xiaojinzi.code.R;
import com.xiaojinzi.viewinjection.annotation.Injection;
import com.xiaojinzi.viewinjection.annotation.ViewInjectionUtil;
import com.xiaojinzi.xtool.toast.T;

/**
 * Created by cxj on 2016/11/3.
 * 主界面的左边的菜单
 */
public class Menu implements View.OnClickListener {

    /**
     * 背景图
     */
    @Injection(R.id.header_bg)
    SimpleDraweeView header_bg;

    /**
     * 用户图标
     */
    @Injection(R.id.icon)
    SimpleDraweeView icon;

    /**
     * 用户名
     */
    @Injection(R.id.tv_user_name)
    TextView tv_user_name;

    /**
     * 设置
     */
    @Injection(value = R.id.tv_setting, click = "onClick")
    TextView tv_setting;

    /**
     * 退出App文本
     */
    @Injection(value = R.id.tv_exit, click = "onClick")
    TextView tv_exit;

    /**
     * 上下文
     */
    private Context mContext;


    /**
     * 初始化
     *
     * @param context
     * @param contentView
     */
    public void init(Context context, View contentView) {

        mContext = context;

        //为了不让侧滑菜单能穿透
        contentView.setOnClickListener(this);

        ViewInjectionUtil.injectView(this, contentView);

        header_bg.setImageURI(Uri.parse("http://huaishi-image.oss-cn-shanghai.aliyuncs.com/1.jpg?x-oss-process=image/blur,r_8,s_30"));
//        icon.setImageURI(Uri.parse(AppInfo.user.getAvatarAddress()));
        icon.setImageURI(Uri.parse("http://huaishi-image.oss-cn-shanghai.aliyuncs.com/1.jpg?x-oss-process=image/blur,r_8,s_30"));

        tv_user_name.setText(AppInfo.user.getName());

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.tv_setting: //设置

                T.showShort(mContext, "设置界面");

                break;

            case R.id.tv_exit: //退出

                T.showShort(mContext, "退出App");

                break;
        }
    }
}
