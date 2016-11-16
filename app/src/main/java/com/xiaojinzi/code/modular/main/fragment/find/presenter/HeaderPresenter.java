package com.xiaojinzi.code.modular.main.fragment.find.presenter;

import com.xiaojinzi.code.AppConfig;
import com.xiaojinzi.code.common.bean.PopularStar;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.main.fragment.find.bean.Adv;
import com.xiaojinzi.code.modular.main.fragment.find.view.IHeaderView;
import com.xiaojinzi.code.mvp.m.CallBackAdapter;

import java.util.List;

import retrofit2.Call;

/**
 * Created by cxj on 2016/10/30.
 */
public class HeaderPresenter {

    private IHeaderView view;

    public HeaderPresenter(IHeaderView view) {
        this.view = view;
    }

    /**
     * 获取所有的广告
     */
    public void getAllAdv() {
        Call<BaseNetWorkResult<List<Adv>>> call = AppConfig.netWorkService.getAllAdv();
        call.enqueue(new CallBackAdapter<List<Adv>>(view) {
            @Override
            public void onResponse(List<Adv> advs) {
                view.onLoadAllAdvSuccess(advs);
            }
        });
    }

    public void getAllPopularStar() {
        Call<BaseNetWorkResult<List<PopularStar>>> call = AppConfig.netWorkService.getAllPopularStar();
        call.enqueue(new CallBackAdapter<List<PopularStar>>(view) {
            @Override
            public void onResponse(List<PopularStar> popularStars) {
                view.onLoadAllPopularStarsSuccess(popularStars);
            }
        });
    }

}
