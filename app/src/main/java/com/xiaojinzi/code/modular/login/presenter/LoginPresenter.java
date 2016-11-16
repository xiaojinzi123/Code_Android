package com.xiaojinzi.code.modular.login.presenter;

import com.xiaojinzi.code.AppConfig;
import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.login.view.ILoginView;
import com.xiaojinzi.code.mvp.m.CallBackAdapter;

import retrofit2.Call;

/**
 * Created by cxj on 2016/10/27.
 */
public class LoginPresenter {

    private ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    /**
     * 登陆
     */
    public void login() {
        Call<BaseNetWorkResult<User>> call = AppConfig.netWorkService.login(view.getPhoneNumber(), view.getPassWord());
        call.enqueue(new CallBackAdapter<User>(view) {
            @Override
            public void onResponse(User user) {
                view.onLoginSuccess(user);
            }
        });
    }

}
