package com.xiaojinzi.code.modular.main.fragment.home.presenter;

import com.xiaojinzi.code.AppConfig;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.common.bean.ProLan;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.main.fragment.home.view.IHomeView;
import com.xiaojinzi.code.mvp.m.CallBackAdapter;

import java.util.List;

import retrofit2.Call;

/**
 * Created by cxj on 2016/10/28.
 */
public class HomePresenter {

    private IHomeView view;

    public HomePresenter(IHomeView view) {
        this.view = view;
    }

    /**
     * 获取动态
     */
    public void getDynamics(Integer proLanId) {

        //参数0表示第一页,如果是一个时间戳,那么就是返回此时间戳以前的动态,第二个参数是看哪个编程语言的动态
        Call<BaseNetWorkResult<List<BugDynamics>>> call = AppConfig.netWorkService.getHomeDynamics(0, proLanId);

        call.enqueue(new CallBackAdapter<List<BugDynamics>>(view) {
            @Override
            public void onResponse(List<BugDynamics> dynamicses) {
                view.onLoadHomeDynamicsSuccess(dynamicses);
            }
        });

    }

    public void getMoreDynamics(long timestamp, Integer proLanId) {

        //参数0表示第一页,如果是一个时间戳,那么就是返回此时间戳以前的动态,第二个参数是看哪个编程语言的动态
        Call<BaseNetWorkResult<List<BugDynamics>>> call = AppConfig.netWorkService.getHomeDynamics(timestamp, proLanId);

        call.enqueue(new CallBackAdapter<List<BugDynamics>>(view) {

            @Override
            public void onResponse(List<BugDynamics> dynamicses) {
                view.onLoadMoreHomeDynamicsSuccess(dynamicses);
            }

            @Override
            public void onFail() {
                view.onLoadHomeDynamicsFail("");
            }

        });

    }

    /**
     * 获取所有的编程语言
     */
    public void getAllProLan() {
        Call<BaseNetWorkResult<List<ProLan>>> call = AppConfig.netWorkService.queryAllProLan();
        call.enqueue(new CallBackAdapter<List<ProLan>>(view) {
            @Override
            public void onResponse(List<ProLan> proLans) {
                view.onLoadProLanSuccess(proLans);
            }
        });
    }

}
