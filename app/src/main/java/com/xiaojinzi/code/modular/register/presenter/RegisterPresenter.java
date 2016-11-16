package com.xiaojinzi.code.modular.register.presenter;

import android.text.TextUtils;

import com.xiaojinzi.code.AppConfig;
import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.register.view.IRegisterView;
import com.xiaojinzi.code.mvp.m.CallBackAdapter;

import retrofit2.Call;

/**
 * Created by cxj on 2016/10/28.
 */
public class RegisterPresenter {

    private IRegisterView view;

    public RegisterPresenter(IRegisterView view) {
        this.view = view;
    }

    /**
     * 注册的逻辑
     */
    public void register() {

        String phoneNumber = view.getPhoneNumber();
        String passWord = view.getPassWord();

        if (TextUtils.isEmpty(phoneNumber)) {
            view.tip("请先验证手机号");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            view.tip("密码不能为空");
            return;
        }

        view.showDialog("正在注册");

        Call<BaseNetWorkResult<User>> call = AppConfig.netWorkService.register(phoneNumber, passWord);
        call.enqueue(new CallBackAdapter<User>(view) {
            @Override
            public void onResponse(User user) {
                view.onRegisterSuccess(user);
            }
        });


    }

}
