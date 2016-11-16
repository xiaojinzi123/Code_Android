package com.xiaojinzi.code.modular.main.fragment.find.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.PopularStar;
import com.xiaojinzi.code.modular.main.fragment.find.adapter.PopularStarAdapter;
import com.xiaojinzi.code.modular.main.fragment.find.bean.Adv;
import com.xiaojinzi.code.modular.main.fragment.find.presenter.HeaderPresenter;
import com.xiaojinzi.code.util.AdapterNotify;
import com.xiaojinzi.code.util.ViewPagerViewAdapter;
import com.xiaojinzi.code.util.widget.XCircleIndicator;
import com.xiaojinzi.viewinjection.annotation.Injection;
import com.xiaojinzi.viewinjection.annotation.ViewInjectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxj on 2016/10/30.
 * 发现页面的Header
 */
public class Header implements IHeaderView {

    /**
     * 头部对应的试图
     */
    private View contentView;

    /**
     * 上下文
     */
    private Context mContext;

    @Injection(R.id.vp_images)
    ViewPager vp_images;

    @Injection(R.id.view_indicator)
    XCircleIndicator xCircleIndicator;

    @Injection(R.id.tv_desc)
    TextView tv_desc;

    //显示人气明星的列表
    @Injection(R.id.rv_popularstar)
    RecyclerView rv_popularstar;

    /**
     * 显示人气明星的适配器
     */
    private PopularStarAdapter popularStarAdapter;

    /**
     * 要显示的人气明星的数据
     */
    private List<PopularStar> popularStars = new ArrayList<PopularStar>();

    /**
     * 显示轮播图的适配器
     */
    private ViewPagerViewAdapter advAdapter;

    /**
     * 要显示的广告数据集合
     */
    private List<Adv> advs = new ArrayList<Adv>();

    /**
     * 轮播图的图片
     */
    private ArrayList<View> views = new ArrayList<View>();

    /**
     * 头部的主持人
     */
    private HeaderPresenter headerPresenter = new HeaderPresenter(this);

    /**
     * 初始化头部
     *
     * @param mContext 上下文
     * @return
     */
    protected View initHeader(Context mContext) {

        this.mContext = mContext;

        //创建头部的试图
        contentView = View.inflate(mContext, R.layout.frag_find_header, null);

        //注解注入View
        ViewInjectionUtil.injectView(this, contentView);

        //创建适配器
        advAdapter = new ViewPagerViewAdapter(views, true);
        popularStarAdapter = new PopularStarAdapter(mContext, popularStars);

        //人气明星的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_popularstar.setLayoutManager(layoutManager);

        //设置适配器
        vp_images.setAdapter(advAdapter);
        rv_popularstar.setAdapter(popularStarAdapter);

        //初始化监听事件
        initListener();

        //返回视图
        return contentView;
    }

    /**
     * 监听事件
     */
    private void initListener() {
        vp_images.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tv_desc.setText(advs.get(position % views.size()).getDescription());
                xCircleIndicator.setCurrentPage(position % views.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 加载广告和人气明星
     */
    public void loadAdvAndPopularStar() {

        if (advs != null && advs.size() > 0) {
        } else {
            //异步加载广告
            headerPresenter.getAllAdv();
        }

        if (popularStars != null && popularStars.size() > 0) {
        } else {
            //加载人气明星
            headerPresenter.getAllPopularStar();
        }

    }

    @Override
    public void onLoadAllAdvSuccess(List<Adv> advs) {
        this.advs.addAll(advs);
        views.clear();
        int size = advs.size();
        for (int i = 0; i < size; i++) {
            final Adv adv = advs.get(i);
            SimpleDraweeView sdv = new SimpleDraweeView(mContext);
            sdv.setImageURI(Uri.parse(adv.getImage()));
            views.add(sdv);
            if (i == 0) {
                tv_desc.setText(adv.getDescription());
            }
            //设置图片的点击事件
            sdv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //跳转到网页
                    Uri uri = Uri.parse(adv.getHref());
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(it);

                }
            });
        }

        xCircleIndicator.initData(size, 0);

        advAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoadAllPopularStarsSuccess(List<PopularStar> popularStars) {
        AdapterNotify.notifyFreshData(this.popularStars, popularStars, popularStarAdapter);
    }

    @Override
    public void tip(String content) {
        //T.showShort(mContext, content);
    }

    @Override
    public void showDialog(String msg) {
    }

    @Override
    public void closeDialog() {
    }

}
