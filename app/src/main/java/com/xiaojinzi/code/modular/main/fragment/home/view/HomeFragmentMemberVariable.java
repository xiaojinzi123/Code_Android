package com.xiaojinzi.code.modular.main.fragment.home.view;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.common.bean.ProLan;
import com.xiaojinzi.code.modular.base.DynamicsAdapter;
import com.xiaojinzi.code.modular.base.LoadMoreFoot;
import com.xiaojinzi.code.util.widget.CommonRefreshLayout;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxj on 2016/10/25.
 */
public class HomeFragmentMemberVariable {

    @Injection(value = R.id.tv_not_resolved, click = "clickView")
    TextView tv_not_resolved;

    @Injection(value = R.id.tv_solved, click = "clickView")
    TextView tv_solved;

    @Injection(value = R.id.iv_search, click = "clickView")
    ImageView iv_search;

    /**
     * 展示所有的编程标签
     */
    @Injection(R.id.tl_category)
    TabLayout tl_category;

    List<ProLan> prolans = new ArrayList<ProLan>();

    @Injection(R.id.rv)
    RecyclerView rv;

//    @Injection(R.id.sfl)
//    SwipeRefreshLayout sfl;

    @Injection(R.id.sfl)
    CommonRefreshLayout sfl;

    /**
     * 列表底部布局
     */
    LoadMoreFoot loadMoreFoot = new LoadMoreFoot();

    /**
     * 显示动态的数据
     */
    List<BugDynamics> dynamicses = new ArrayList<BugDynamics>();

    /**
     * 动态适配器
     */
    DynamicsAdapter dynamicsAdapter;

    /**
     * 编程语言的id
     */
    Integer proLanId = null;

}
