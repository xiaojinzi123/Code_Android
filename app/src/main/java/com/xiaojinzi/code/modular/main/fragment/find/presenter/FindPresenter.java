package com.xiaojinzi.code.modular.main.fragment.find.presenter;

import com.xiaojinzi.code.AppConfig;
import com.xiaojinzi.code.AppInfo;
import com.xiaojinzi.code.common.bean.BlogDynamics;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.main.fragment.find.view.IFindView;
import com.xiaojinzi.code.mvp.m.CallBackAdapter;

import java.util.List;

import retrofit2.Call;

/**
 * Created by cxj on 2016/11/3.
 */
public class FindPresenter {

    private IFindView view;

    public FindPresenter(IFindView view) {
        this.view = view;
    }

    public void getDynamics(){

        Call<BaseNetWorkResult<List<BlogDynamics>>> call = AppConfig.netWorkService.getFindDynamics(
                0
        );
        call.enqueue(new CallBackAdapter<List<BlogDynamics>>(view) {
            @Override
            public void onResponse(List<BlogDynamics> dynamicses) {
                dynamicses.addAll(dynamicses);
                view.onLoadFindDynamicsSuccess(dynamicses);
            }
        });

    }

}
