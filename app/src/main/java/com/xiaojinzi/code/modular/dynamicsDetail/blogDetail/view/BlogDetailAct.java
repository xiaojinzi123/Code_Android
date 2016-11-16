package com.xiaojinzi.code.modular.dynamicsDetail.blogDetail.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.modular.base.BaseFragmentAct;
import com.xiaojinzi.code.modular.dynamicsDetail.blogDetail.fragment.view.BlogDetailFragment;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态详情的界面,应该怎么展示呢?加载网页是一个不错的选择
 */
@Injection(R.layout.act_blog_detail)
public class BlogDetailAct extends BaseFragmentAct {

    @Injection(R.id.vp)
    ViewPager vp;

    PagerAdapter adapter;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void initView() {
        super.initView();

        tv_title_name.setText("Blog");

        fragmentList.add(new BlogDetailFragment());
        fragmentList.add(new BlogDetailFragment());
        fragmentList.add(new BlogDetailFragment());

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        vp.setAdapter(adapter);

    }
}
