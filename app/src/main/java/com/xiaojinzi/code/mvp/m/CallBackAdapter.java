package com.xiaojinzi.code.mvp.m;

import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.mvp.v.IBaseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cxj on 2016/10/27.
 * 回掉的适配器
 */
public abstract class CallBackAdapter<T> implements Callback<BaseNetWorkResult<T>> {

    private IBaseView view;

    public CallBackAdapter(IBaseView view) {
        this.view = view;
    }

    @Override
    public void onResponse(Call<BaseNetWorkResult<T>> call, Response<BaseNetWorkResult<T>> response) {
        BaseNetWorkResult<T> result = response.body();
        if (result.getStatusCode() != 0) {
            view.closeDialog();
            onOtherStatus(result);
        } else {
            view.closeDialog();
            onResponse(result.getData());
        }
    }

    /**
     * 真正相应的数据
     *
     * @param t 返回的真正的对象
     */
    public abstract void onResponse(T t);

    /**
     * 网络请求失败的时候的默认处理方法
     */
    public void onFail() {
        view.tip("网络似乎有点问题");
    }

    /**
     * 其他状态吗的默认处理
     *
     * @param result
     */
    public void onOtherStatus(BaseNetWorkResult<T> result) {
        //拿到错误信息
        String msgText = result.getMsgText();
        //让View层提示出来
        view.tip(msgText);
    }

    @Override
    public void onFailure(Call<BaseNetWorkResult<T>> call, Throwable t) {
        view.closeDialog();
        onFail();
    }
}
