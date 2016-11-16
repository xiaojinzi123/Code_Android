package com.xiaojinzi.code.modular.base;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.util.AlwaysRotateAnimationUtil;
import com.xiaojinzi.viewinjection.annotation.Injection;
import com.xiaojinzi.viewinjection.annotation.ViewInjectionUtil;

/**
 * Created by cxj on 2016/11/12.
 */
public class LoadMoreFoot {

    /**
     * 不可用状态
     */
    public static final int STATUE_UNAVAILABLE = -1;

    /**
     * 准备刷新
     */
    public static final int STATUE_PREFRESHING = 0;

    /**
     * 正在刷新
     */
    public static final int STATUE_FRESHING = 1;

    /**
     * 刷新完毕
     */
    public static final int STATUE_SUCCESS_FRESHED = 2;


    /**
     * 刷新失败
     */
    public static final int STATUE_FAIL_FRESHED = 3;

    @Injection(R.id.tv_tip)
    TextView tv_tip;

    @Injection(R.id.iv_loading)
    ImageView iv_loading;

    private View contentView;

    private int mStatus = STATUE_UNAVAILABLE;

    public View getContentView() {
        return contentView;
    }

    /**
     * 初始化
     *
     * @param context
     * @return
     */
    public View init(Context context) {

        //创建加载更多的布局
        contentView = View.inflate(context, R.layout.loadmore, null);

        ViewInjectionUtil.injectView(this, contentView);

        return contentView;

    }

    /**
     * 设置刷新的状态
     *
     * @param status 刷新的状态
     */
    public void setStatus(int status) {
        switch (status) {
            case STATUE_PREFRESHING:

                tv_tip.setText("释放就刷新");
                mStatus = STATUE_PREFRESHING;

                break;
            case STATUE_FRESHING:

                tv_tip.setText("正在努力加载......");
                iv_loading.setVisibility(View.VISIBLE);
                AlwaysRotateAnimationUtil.start(iv_loading);

                mStatus = STATUE_FRESHING;

                break;
            case STATUE_SUCCESS_FRESHED:

                tv_tip.setText("加载完成");
                iv_loading.setVisibility(View.GONE);
                iv_loading.clearAnimation();

                mStatus = STATUE_SUCCESS_FRESHED;

                break;

            case STATUE_FAIL_FRESHED:

                tv_tip.setText("加载失败");
                iv_loading.setVisibility(View.GONE);
                iv_loading.clearAnimation();

                mStatus = STATUE_FAIL_FRESHED;

                break;
            default:

                mStatus = STATUE_UNAVAILABLE;

                break;
        }
    }

}
