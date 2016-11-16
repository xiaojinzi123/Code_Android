package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.presenter;

import com.xiaojinzi.code.AppConfig;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.view.IHeaderView;
import com.xiaojinzi.code.mvp.m.CallBackAdapter;

import retrofit2.Call;

/**
 * Created by cxj on 2016/11/4.
 */
public class HeaderPresenter {

    private IHeaderView view;

    public HeaderPresenter(IHeaderView view) {
        this.view = view;
    }

    public void queryDynamicsById(Integer dynamicsId){

        Call<BaseNetWorkResult<BugDynamics>> call = AppConfig.netWorkService.queryBugDynamicsById(dynamicsId);

        call.enqueue(new CallBackAdapter<BugDynamics>(view) {
            @Override
            public void onResponse(BugDynamics bugDynamics) {
                view.onLoadBugDynamicsSuccess(bugDynamics);
            }
        });

    }

}
