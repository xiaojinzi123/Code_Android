package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.modular.base.BaseFragmentAct;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.view.BugDetailFragment;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.ArrayList;
import java.util.List;

/**
 * Bug提问的详情,分为已经解决和未解决,楼主可以从评论中找答案,如果找到了,采纳那个答案之后表示一个Bug已经解决
 * 提供正确答案的人会得到一定的积分,而发布一个Bug也需要一定的积分
 * <p/>
 * 跳转到这个页面需要参数:
 */
@Injection(R.layout.act_bug_detail)
public class BugDetailAct extends BaseFragmentAct {

    /**
     * 当点击BugDynamica的Item的时候跳转到这个界面选哟携带的int[]数组数据的key
     */
    public static final String BUGDYNAMICS_IDS_FLAG = "bugdynamics_ids_flag";

    public static final String POSITION_FLAG = "position";

    @Injection(R.id.tv_title_name)
    TextView tv_title_name;

    @Injection(R.id.vp)
    ViewPager vp;

    private FragmentPagerAdapter adapter;

    /**
     * 显示的界面
     */
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void initView() {
        super.initView();

        //拿到动态的集合id
        int[] bugDynamicsIds = getIntent().getIntArrayExtra(BUGDYNAMICS_IDS_FLAG);
        //循环集合创建Fragmemt准备让每一个fragment去根据id显示
        for (int i = 0; i < bugDynamicsIds.length; i++) {
            //拿到循环中的id
            int bugDynamicsId = bugDynamicsIds[i];
            //创建Fragment
            BugDetailFragment f = new BugDetailFragment(bugDynamicsId);
            //添加到集合中
            fragmentList.add(f);
        }

        //创建显示fragment的适配器
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

        //设置适配器
        vp.setAdapter(adapter);

        //显示指定的位置的详情
        int position = getIntent().getIntExtra(POSITION_FLAG, 0);
        vp.setCurrentItem(position);

    }

    /**
     * 设置标题
     *
     * @param titleName
     */
    public void setTitle(String titleName) {
        tv_title_name.setText(titleName);
    }

}
