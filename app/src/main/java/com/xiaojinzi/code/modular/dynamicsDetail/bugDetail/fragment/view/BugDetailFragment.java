package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.view;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.BugComment;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.adapter.CommentAdapter;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.presenter.BugDetailPresenter;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.view.BugDetailAct;
import com.xiaojinzi.code.util.AdapterNotify;
import com.xiaojinzi.code.util.AlwaysRotateAnimationUtil;
import com.xiaojinzi.code.util.BaseViewPagerFragment;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewAdapter;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.ArrayList;
import java.util.List;

import xiaojinzi.animation.AlphaAnimationUtil;
import xiaojinzi.animation.RotateAnimationUtil;

/**
 * Created by cxj on 2016/11/2.
 * Bug详情的界面,用了Fragment就是为了能用ViewPager左右滑动显示另一个Bug的详情
 * 然后这个Fragment主要的逻辑是显示评论,详情内容由Header承包了
 */
public class BugDetailFragment extends BaseViewPagerFragment implements IBugDetailFragmentView {

    /**
     * 动态的id
     */
    private Integer bugDynamicsId;

    public BugDetailFragment() {
    }

    @SuppressLint("ValidFragment")
    public BugDetailFragment(Integer bugDynamicsId) {
        this.bugDynamicsId = bugDynamicsId;
    }

    /**
     * 视图的头部
     */
    private Header header = new Header();

    @Injection(R.id.rv)
    RecyclerView rv;

    @Injection(R.id.ll_loading)
    LinearLayout ll_loading;

    @Injection(R.id.iv_loading)
    ImageView iv_loading;

    /**
     * 评论的适配器
     */
    private CommonRecyclerViewAdapter adapter;

    /**
     * 要显示的评论的数据
     */
    private List<BugComment> commentList = new ArrayList<BugComment>();

    private BugDetailPresenter bugDetailPresenter = new BugDetailPresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.frag_bug_detail;
    }

    @Override
    public void initView() {
        super.initView();

        //创建评论的适配器
        adapter = new CommentAdapter(mContext, commentList);

        //添加列表的Header
        adapter.addHeaderView(header.initHeader(mContext));

        //创建线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(layoutManager);

        rv.setAdapter(adapter);

        //加载动画
        AlwaysRotateAnimationUtil.start(iv_loading);

    }

    @Override
    public void initData() {
        super.initData();

        //加载动态详情
        header.loadDynamics(bugDynamicsId);

    }

    @Override
    public void setOnlistener() {
        super.setOnlistener();

        //加载详情成功之后
        header.setOnLoadDynamicsDetailListener(new Header.OnLoadDynamicsDetailListener() {
            @Override
            public void onSuccess(BugDynamics bugDynamics) {

                //获取正确答案
                BugComment bugComment = bugDynamics.getBugComment();

                if (bugComment != null) {
                    bugComment.setAnswer(true);
                    commentList.add(bugComment);
                    adapter.notifyItemInserted(0);
                }

                //加载动态的评论
                bugDetailPresenter.loadBugComment(bugDynamicsId, 0);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destoryViewAndData();
    }

    @Override
    protected void onDestoryData() {
        super.onDestoryData();
        header.destory();
        adapter.notifyItemRangeRemoved(0, commentList.size());
        commentList.clear();
        rv.setVisibility(View.INVISIBLE);
        ll_loading.setVisibility(View.VISIBLE);
    }


    @Override
    public void onLoadBugCommentSuccess(List<BugComment> bugComments) {
        filterAnswer(bugComments);
        AdapterNotify.notifyAppendData(this.commentList, bugComments, adapter);
        afterLoadDateComplete();
    }

    /**
     * 加载数据完成之后
     */
    private void afterLoadDateComplete() {
        iv_loading.clearAnimation();
        rv.setVisibility(View.VISIBLE);
        notifyLoadDataComplete();
        ll_loading.setVisibility(View.INVISIBLE);
        AlphaAnimationUtil.fillAfter = false;
        AlphaAnimationUtil.alpha(rv, 0f, 1f, 500);
    }

    @Override
    protected void onUserVisible() {
        setTitleBarTitleName("");
    }

    @Override
    public void freshUI() {
        super.freshUI();
        setTitleBarTitleName(header.getTitle());
    }

    /**
     * 设置所挂载的Activity的标题栏上的文字
     *
     * @param content
     */
    private void setTitleBarTitleName(String content) {
        int titleNameSize = 10;
        if (content.length() > titleNameSize) {
            content = content.substring(0, titleNameSize) + "...";
        }
        BugDetailAct bugDetailAct = (BugDetailAct) getActivity();
        bugDetailAct.setTitle(content);
    }

    /**
     * 如果评论中也有正确答案,筛选出来,做个标记
     *
     * @param bugComments
     */
    private void filterAnswer(List<BugComment> bugComments) {
        if (this.commentList.size() > 0 && this.commentList.get(0).isAnswer()) {
            //拿到正确答案
            BugComment answer = this.commentList.get(0);
            int size = bugComments.size();
            for (int i = 0; i < size; i++) {
                BugComment bugComment = bugComments.get(i);
                if (answer.getId().equals(bugComment.getId())) {
                    bugComment.setAnswer(true);
                }
            }
        }
    }

}
