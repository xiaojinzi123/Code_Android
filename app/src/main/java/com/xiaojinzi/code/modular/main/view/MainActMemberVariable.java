package com.xiaojinzi.code.modular.main.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiaojinzi.code.R;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxj on 2016/10/25.
 */
public class MainActMemberVariable {

    @Injection(R.id.dl)
    DrawerLayout dl;

    @Injection(value = R.id.iv_menu,click = "clickView")
    ImageView iv_menu;

    boolean isMenuShow = true;

    @Injection(R.id.vp)
    ViewPager vp;

    @Injection(R.id.iv_home)
    ImageView iv_home;

    @Injection(R.id.iv_find)
    ImageView iv_find;

    @Injection(R.id.iv_message)
    ImageView iv_message;

    @Injection(R.id.iv_more)
    ImageView iv_more;

    @Injection(value = R.id.rl_home_container,click = "clickView")
    RelativeLayout rl_home_container;

    @Injection(value = R.id.rl_find_container,click = "clickView")
    RelativeLayout rl_find_container;

    @Injection(value = R.id.rl_message_container,click = "clickView")
    RelativeLayout rl_message_container;

    @Injection(value = R.id.rl_more_container,click = "clickView")
    RelativeLayout rl_more_container;

    PagerAdapter adapter;

    List<Fragment> fragmentList = new ArrayList<Fragment>();

}
