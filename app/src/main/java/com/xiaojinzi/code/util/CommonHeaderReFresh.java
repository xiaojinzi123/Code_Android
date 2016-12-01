package com.xiaojinzi.code.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.util.widget.CommonRefreshLayout;

/**
 * Created by cxj on 2016/11/22.
 * 抽取出下拉刷新更新刷新视图ui的代码
 */
public class CommonHeaderReFresh implements CommonRefreshLayout.OnRefreshListener {

    private ImageView iv = null;

    private TextView tv = null;

    private ProgressBar pb;

    private CommonRefreshLayout crl;

    private View contentView;

    public CommonHeaderReFresh(View contentView, CommonRefreshLayout crl) {
        this.contentView = contentView;
        this.crl = crl;
        iv = (ImageView) contentView.findViewById(R.id.iv);
        tv = (TextView) contentView.findViewById(R.id.tv_tip);
        pb = (ProgressBar) contentView.findViewById(R.id.pb);
    }

    @Override
    public void onPullPercentage(float percent) {
        if (percent < 0) {
            percent = 0f;
        }
        if (percent > 1) {
            percent = 1f;
        }
        if (percent > 0.7f) {
            percent = (percent - 0.7f) * 10 / 3;
            iv.setRotation(180 * percent);
        } else {
            iv.setRotation(0);
        }
    }

    @Override
    public void onHeaderRefreshComplete() {
        //还原ui
        tv.setText("下拉刷新");
        pb.setVisibility(View.GONE);
        iv.setImageResource(R.mipmap.arrow_down_back1);
        iv.setVisibility(View.VISIBLE);
        iv.setRotation(0);
    }

    @Override
    public void onHeaderRefresh() {
        tv.setText("正在刷新.......");
        iv.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHeaderPrepareRefresh() {
        tv.setText("释放立即刷新");
    }

    @Override
    public void onHeaderCamcelPrepareRefresh() {
        tv.setText("下拉刷新");
    }

    /**
     * 成功的时候
     */
    public void success() {
        //设置刷新控件完成刷新
        crl.setOnRefreshComplete();
        tv.setText("刷新成功");
        pb.setVisibility(View.GONE);
        iv.setImageResource(R.mipmap.refresh_conplete_white1);
        iv.setVisibility(View.VISIBLE);
        iv.setRotation(0);
    }

    /**
     * 失败的时候
     */
    public void fail() {
        //设置刷新控件完成刷新
        crl.setOnRefreshComplete();
        tv.setText("刷新失败");
        pb.setVisibility(View.GONE);
        iv.setImageResource(R.mipmap.refresh_fail_white1);
        iv.setVisibility(View.VISIBLE);
        iv.setRotation(0);
    }

}
