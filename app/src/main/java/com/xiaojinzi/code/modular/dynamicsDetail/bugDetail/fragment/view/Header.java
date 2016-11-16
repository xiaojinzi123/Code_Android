package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.view;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.presenter.HeaderPresenter;
import com.xiaojinzi.viewinjection.annotation.Injection;
import com.xiaojinzi.viewinjection.annotation.ViewInjectionUtil;
import com.xiaojinzi.xtool.toast.T;

/**
 * Created by cxj on 2016/10/30.
 * 发现页面的Header
 */
public class Header implements IHeaderView {

    @Injection(R.id.sdv)
    SimpleDraweeView sdv;

    @Injection(R.id.tv_user_name)
    TextView tv_user_name;

    //技术标签
    @Injection(R.id.tv_pl_tag)
    TextView tv_pl_tag;

    @Injection(R.id.tv_content)
    TextView tv_content;

    /**
     * 头部对应的试图
     */
    private View contentView;

    /**
     * 上下文
     */
    private Context mContext;

    private HeaderPresenter presenter = new HeaderPresenter(this);

    /**
     * 加载数据成功之后会有值
     */
    private BugDynamics mBugDynamics;


    /**
     * 初始化头部
     *
     * @param mContext 上下文
     * @return
     */
    protected View initHeader(Context mContext) {
        this.mContext = mContext;
        //创建头部的试图
        contentView = View.inflate(mContext, R.layout.frag_bug_detail_header, null);

        //注解注入View
        ViewInjectionUtil.injectView(this, contentView);

        initListener();

        //返回视图
        return contentView;
    }

    /**
     * 监听事件
     */
    private void initListener() {
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

    /**
     * 不会重复加载数据
     *
     * @param bugDynamicsId
     */
    public void loadDynamics(Integer bugDynamicsId) {
        if (mBugDynamics == null) {
            presenter.queryDynamicsById(bugDynamicsId);
        }
    }

    @Override
    public void onLoadBugDynamicsSuccess(BugDynamics bugDynamics) {

        mBugDynamics = bugDynamics;

        User user = bugDynamics.getUser();

        //设置头像
        sdv.setImageURI(Uri.parse(user.getAvatarAddress()));

        //设置用户名
        tv_user_name.setText(user.getName());

        //设置技术标签
        tv_pl_tag.setText(bugDynamics.getProLan().getName());

        //设置内容
        tv_content.setText(bugDynamics.getContent());

        if (onLoadDynamicsDetailListener != null) {
            onLoadDynamicsDetailListener.onSuccess();
        }

    }


    private OnLoadDynamicsDetailListener onLoadDynamicsDetailListener;

    public void setOnLoadDynamicsDetailListener(OnLoadDynamicsDetailListener onLoadDynamicsDetailListener) {
        this.onLoadDynamicsDetailListener = onLoadDynamicsDetailListener;
    }

    public String getTitle() {
        return mBugDynamics.getTitle();
    }

    public interface OnLoadDynamicsDetailListener {
        void onSuccess();
    }

    public void destory(){
        mBugDynamics = null;
    }

}
