package com.xiaojinzi.code.modular.main.fragment.home.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.adapter.OnTabSelectedListenerAdapter;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.common.bean.Dynamics;
import com.xiaojinzi.code.common.bean.ProLan;
import com.xiaojinzi.code.modular.base.DynamicsItemDec;
import com.xiaojinzi.code.modular.base.LoadMoreFoot;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.view.BugDetailAct;
import com.xiaojinzi.code.modular.imagePreview.view.ImagePreviewAct;
import com.xiaojinzi.code.modular.main.fragment.home.adapter.HomeAdater;
import com.xiaojinzi.code.modular.main.fragment.home.presenter.HomePresenter;
import com.xiaojinzi.code.util.AdapterNotify;
import com.xiaojinzi.code.util.BaseViewPagerFragment;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewAdapter;
import com.xiaojinzi.code.util.widget.CommonNineView;
import com.xiaojinzi.code.util.widget.CommonRefreshLayout;

import java.util.List;

/**
 * Created by cxj on 2016/10/28.
 * 主页的fragment
 */
public class HomeFragment extends BaseViewPagerFragment implements IHomeView {

    private Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //1用于加载更多
            int what = msg.what;
            if (what == 1) {
                int size = hm.dynamicses.size();
                if (size > 0) {
                    presenter.getMoreDynamics(hm.dynamicses.get(size - 1).getPostTime(), hm.proLanId);
                }
            }

        }
    };

    /**
     * 成员变量
     */
    private HomeFragmentMemberVariable hm = new HomeFragmentMemberVariable();

    private HomePresenter presenter = new HomePresenter(this);

    private HeaderReFresh headerReFresh;

    @Override
    public int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    public void initView() {
        super.initView();

        //创建显示Bug动态的适配器
        hm.dynamicsAdapter = new HomeAdater(mContext, hm.dynamicses);

        LinearLayoutManager dLayoutManager = new LinearLayoutManager(mContext);
        dLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hm.rv.setLayoutManager(dLayoutManager);

        DynamicsItemDec itemDec = new DynamicsItemDec(0);
        hm.rv.addItemDecoration(itemDec);

        //初始化加载更多的试图
        hm.loadMoreFoot.init(mContext);

        hm.rv.setAdapter(hm.dynamicsAdapter);

        //
        headerReFresh = new HeaderReFresh(hm.sfl, hm.sfl){
            @Override
            public void onHeaderRefresh() {
                super.onHeaderRefresh();
                if (hm.dynamicsAdapter.getFootCounts() > 0) {
                    hm.dynamicsAdapter.removeFootView(0, true);
                }
                presenter.getDynamics(hm.proLanId);
            }
        };

    }

    @Override
    public void initData() {
        super.initData();
        //获取所有的编程语言
        presenter.getAllProLan();
    }

    @Override
    public void setOnlistener() {
        super.setOnlistener();

//        hm.sfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });

        hm.sfl.setOnRefreshListener(headerReFresh);

        //编程语言的选中回调用
        hm.tl_category.addOnTabSelectedListener(new OnTabSelectedListenerAdapter() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击标签的下标
                int position = tab.getPosition();
                if (position == 0) {
                    hm.proLanId = null;
                } else {
                    hm.proLanId = hm.prolans.get(position - 1).getId();
                }
                if (hm.dynamicsAdapter.getFootCounts() > 0) {
                    hm.dynamicsAdapter.removeFootView(0, true);
                }
                presenter.getDynamics(hm.proLanId);
            }
        });

        //设置item的点击事件
        hm.dynamicsAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //创建跳转到动态详情的意图
                Intent intent = new Intent(mContext, BugDetailAct.class);
                //收集当前页面所有的动态id
                int[] dynamicsIds = new int[hm.dynamicses.size()];
                for (int i = 0; i < dynamicsIds.length; i++) {
                    dynamicsIds[i] = hm.dynamicses.get(i).getId();
                }
                //把所有Id数组带过去
                intent.putExtra(BugDetailAct.BUGDYNAMICS_IDS_FLAG, dynamicsIds);
                intent.putExtra(BugDetailAct.POSITION_FLAG, position);
                //跳转到详情
                mContext.startActivity(intent);
            }
        });

        //监听item内部的控件的点击事件
        hm.dynamicsAdapter.setOnViewInItemClickListener(new CommonRecyclerViewAdapter.OnViewInItemClickListener() {
            @Override
            public void onViewInItemClick(View v, int position) {
                int id = v.getId();
                switch (id) {

                    case R.id.cnv_images: //如果点击了图片

                        CommonNineView cnv = (CommonNineView) v;

                        //拿到点击的是哪个图片
                        int clickImageIndex = cnv.getClickImageIndex();

                        BugDynamics bugDynamics = hm.dynamicses.get(position);

                        String[] images = bugDynamics.getImages().split(Dynamics.IMAGE_SPLIT_CHAR);

                        Intent intent = new Intent(mContext, ImagePreviewAct.class);

                        intent.putExtra(ImagePreviewAct.IMAGES_FLAG, images);
                        intent.putExtra(ImagePreviewAct.IMAGES_INDEX_FLAG, clickImageIndex < 0 ? 0 : clickImageIndex);

                        mContext.startActivity(intent);

                        break;
                }
            }
        }, R.id.cnv_images);

        hm.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //如果暂停了

                    boolean b = !ViewCompat.canScrollVertically(hm.rv, 1);
                    //如果已经不可以向下滑动了,就去加载更多
                    if (b && !hm.sfl.isFreshing()) {
                        //尾部显示正在刷新
                        hm.loadMoreFoot.setStatus(LoadMoreFoot.STATUE_FRESHING);
                        h.sendEmptyMessageDelayed(1, 500);
                    }

                }
            }
        });

    }

    /**
     * 点击事件的集中处理
     *
     * @param v
     */
    public void clickView(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_not_resolved:

                hm.tv_not_resolved.setBackgroundResource(R.drawable.fillet_left_solid_bg1);
                hm.tv_solved.setBackgroundResource(R.drawable.fillet_right_solid_bg1);

                hm.tv_not_resolved.setTextColor(Color.WHITE);
                hm.tv_solved.setTextColor(Color.parseColor("#88FFFFFF"));

                break;
            case R.id.tv_solved:

                hm.tv_not_resolved.setBackgroundResource(R.drawable.fillet_left_solid_bg2);
                hm.tv_solved.setBackgroundResource(R.drawable.fillet_right_solid_bg2);
                hm.tv_not_resolved.setTextColor(Color.parseColor("#88FFFFFF"));
                hm.tv_solved.setTextColor(Color.WHITE);

                break;

            case R.id.iv_search:

                tip("您点击了搜索");

                break;
        }
    }

    @Override
    public void onLoadProLanSuccess(List<ProLan> proLans) {

        hm.tl_category.removeAllTabs();
        hm.prolans.clear();
        hm.prolans.addAll(proLans);
        int size = hm.prolans.size();
        hm.tl_category.addTab(hm.tl_category.newTab().setText("All"));
        for (int i = 0; i < size; i++) {
            ProLan proLan = hm.prolans.get(i);
            hm.tl_category.addTab(hm.tl_category.newTab().setText(proLan.getName()));
        }

    }

    @Override
    public void onLoadHomeDynamicsSuccess(List<BugDynamics> dynamicses) {
        AdapterNotify.notifyFreshData(hm.dynamicses, dynamicses, hm.dynamicsAdapter);
        notifyLoadDataComplete();
        if (dynamicses.size() > 0) {
            hm.dynamicsAdapter.addFootView(hm.loadMoreFoot.getContentView(), true);
        }
        hm.loadMoreFoot.setStatus(LoadMoreFoot.STATUE_SUCCESS_FRESHED);
        headerReFresh.success();
    }

    @Override
    public void onLoadMoreHomeDynamicsSuccess(List<BugDynamics> dynamicses) {
        AdapterNotify.notifyAppendData(hm.dynamicses, dynamicses, hm.dynamicsAdapter);
        hm.loadMoreFoot.setStatus(LoadMoreFoot.STATUE_SUCCESS_FRESHED);
    }

    @Override
    public void onLoadHomeDynamicsFail(String s) {
        hm.loadMoreFoot.setStatus(LoadMoreFoot.STATUE_FAIL_FRESHED);
        headerReFresh.fail();
    }

    @Override
    protected Object getMemberVariable() {
        return hm;
    }

}
