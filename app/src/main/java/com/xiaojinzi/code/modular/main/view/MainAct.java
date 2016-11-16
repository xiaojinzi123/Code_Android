package com.xiaojinzi.code.modular.main.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.modular.base.BaseFragmentAct;
import com.xiaojinzi.code.modular.main.fragment.find.view.FindFragment;
import com.xiaojinzi.code.modular.main.fragment.home.view.HomeFragment;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.List;

/**
 * 主界面
 */
@Injection(R.layout.act_main_container)
public class MainAct extends BaseFragmentAct {

    /**
     * 成员变量存储区
     */
    private MainActMemberVariable m = new MainActMemberVariable();

    /**
     * 主界面的菜单
     */
    private Menu menu = new Menu();

    @Override
    protected void initView() {
        super.initView();

        menu.init(this, findViewById(R.id.menu));

        final List<Fragment> fragmentList = m.fragmentList;

        fragmentList.add(new HomeFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new FindFragment());

        m.adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        //设置适配器
        m.vp.setAdapter(m.adapter);

    }

    @Override
    protected void initLinstener() {
        super.initLinstener();

        m.dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                toggleMenuRotate(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                toggleMenuRotate(false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        /**
         * 监听页面的左右滑动
         */
        m.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                chooseFootIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    toggleMenuVisiable(true);
                } else {
                    toggleMenuVisiable(false);
                }
            }
        });

    }

    /**
     * 点击事件的集中处理,由注解框架调用
     *
     * @param v
     */
    public void clickView(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.iv_menu: //点击了菜单

                //弹出菜单
                m.dl.openDrawer(Gravity.LEFT);

                break;
            case R.id.rl_home_container: //首页页面
                chooseFootIcon(0);
                m.vp.setCurrentItem(0);
                break;

            case R.id.rl_find_container: //发现页面
                chooseFootIcon(1);
                m.vp.setCurrentItem(1);
                break;

            case R.id.rl_message_container: //消息页面
                chooseFootIcon(2);
                m.vp.setCurrentItem(2);
                break;

            case R.id.rl_more_container: //更多页面
                chooseFootIcon(3);
                m.vp.setCurrentItem(3);
                break;
        }
    }

    /**
     * 选择了某一个选项卡
     *
     * @param position
     */
    private void chooseFootIcon(int position) {

        //还原所有图标的状态
        m.iv_home.setImageResource(R.mipmap.home1_unselected);
        m.iv_find.setImageResource(R.mipmap.find1_unselected);
        m.iv_message.setImageResource(R.mipmap.message1_unselected);
        m.iv_more.setImageResource(R.mipmap.more1_unselected);

        switch (position) {
            case 0:
                m.iv_home.setImageResource(R.mipmap.home1_selected);
                break;
            case 1:
                m.iv_find.setImageResource(R.mipmap.find1_selected);
                break;
            case 2:
                m.iv_message.setImageResource(R.mipmap.message1_selected);
                break;
            case 3:
                m.iv_more.setImageResource(R.mipmap.more1_select);
                break;
        }

    }

    /**
     * 返回成员变量的存放对象
     *
     * @return
     */
    @Override
    protected Object getMemberVariable() {
        return m;
    }

    private float mAlpha = 1f;

    /**
     * 左右滑动会让菜单慢慢消失,停止了又会慢慢显示,无缝衔接
     *
     * @param isShow 是否显示菜单
     */
    private void toggleMenuVisiable(boolean isShow) {
        if (isShow == m.isMenuShow) {
            return;
        }

        ValueAnimator objectAnimator = ObjectAnimator//
                .ofFloat(mAlpha, isShow ? 1f : 0f)//
                .setDuration(1000);
        //设置更新数据的监听
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAlpha = (Float) animation.getAnimatedValue();
                m.iv_menu.setAlpha(mAlpha);
            }
        });

        objectAnimator.start();

        m.isMenuShow = isShow;


    }

    private float mRotate = 0;

    /**
     * 左右滑动会让菜单慢慢旋转,无缝衔接
     *
     * @param isRotate 是否显示菜单
     */
    private void toggleMenuRotate(boolean isRotate) {

        ValueAnimator objectAnimator = ObjectAnimator//
                .ofFloat(m.iv_menu.getRotation(), isRotate ? 90f : 0f)//
                .setDuration(1000);
        //设置更新数据的监听
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotate = (Float) animation.getAnimatedValue();
                m.iv_menu.setRotation(mRotate);
            }
        });

        objectAnimator.start();

    }


    private int pressCount;

    /**
     * 实现返回两次退出应用的功能
     *
     * @param keyCode 键值
     * @param event   键的事件
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (m.dl.isDrawerOpen(Gravity.LEFT)) {
                m.dl.closeDrawer(Gravity.LEFT);
                return false;
            }
            if (pressCount == 0) {
                tip("再按一次退出应用");
                pressCount++;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                        pressCount = 0;
                    }
                }.start();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
