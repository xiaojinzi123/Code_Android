package com.xiaojinzi.code.modular.main.fragment.find.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.BlogDynamics;
import com.xiaojinzi.code.modular.base.DynamicsAdapter;
import com.xiaojinzi.code.modular.base.DynamicsItemDec;
import com.xiaojinzi.code.modular.main.fragment.find.adapter.FindAdapter;
import com.xiaojinzi.code.modular.main.fragment.find.presenter.FindPresenter;
import com.xiaojinzi.code.util.AdapterNotify;
import com.xiaojinzi.code.util.BaseViewPagerFragment;
import com.xiaojinzi.code.util.CommonHeaderReFresh;
import com.xiaojinzi.code.util.widget.CommonRefreshLayout;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现页面
 * Created by cxj on 2016/10/28.
 */
public class FindFragment extends BaseViewPagerFragment implements IFindView {

    private Header header = new Header();

    @Injection(R.id.sfl)
    CommonRefreshLayout sfl;

    @Injection(R.id.rv)
    RecyclerView rv;

    private List<BlogDynamics> dynamicses = new ArrayList<BlogDynamics>();

    private DynamicsAdapter dynamicsAdapter;

    private FindPresenter findPresenter = new FindPresenter(this);

    private CommonHeaderReFresh headerReFresh;

    @Override
    public int getLayoutId() {
        return R.layout.frag_find;
    }

    @Override
    public void initView() {
        super.initView();

        //创建适配器
        dynamicsAdapter = new FindAdapter(mContext, dynamicses);

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        //item的间隔
        DynamicsItemDec itemDec = new DynamicsItemDec(2);
        rv.addItemDecoration(itemDec);

        //添加头部试图
        dynamicsAdapter.addHeaderView(header.initHeader(mContext));
        dynamicsAdapter.addHeaderView(View.inflate(mContext, R.layout.loadmore, null));
        dynamicsAdapter.addFootView(View.inflate(mContext, R.layout.loadmore, null));
        dynamicsAdapter.addFootView(View.inflate(mContext, R.layout.loadmore, null));
        dynamicsAdapter.addFootView(View.inflate(mContext, R.layout.loadmore, null));

        //设置适配器
        rv.setAdapter(dynamicsAdapter);

        headerReFresh = new CommonHeaderReFresh(sfl, sfl) {
            @Override
            public void onHeaderRefresh() {
                super.onHeaderRefresh();
            }
        };

    }

    /**
     * 此方法在不通知加载数据完成之前,页面切换了会不断的被调用的,直到你调用notifyLoadDataComplete();
     */
    @Override
    public void initData() {
        super.initData();

        header.loadAdvAndPopularStar();
        findPresenter.getDynamics();

    }

    @Override
    public void setOnlistener() {
        super.setOnlistener();

        sfl.setOnRefreshListener(headerReFresh);

    }

    @Override
    public void onLoadFindDynamicsSuccess(List<BlogDynamics> dynamicses) {
        AdapterNotify.notifyFreshData(this.dynamicses, dynamicses, dynamicsAdapter);
        notifyLoadDataComplete();
    }

}
