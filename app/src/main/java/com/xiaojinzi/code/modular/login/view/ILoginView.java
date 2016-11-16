package com.xiaojinzi.code.modular.login.view;

import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.mvp.v.IBaseView;

/**
 * Created by cxj on 2016/10/27.
 */
public interface ILoginView extends IBaseView {

    /**
     * 获取电话号码
     *
     * @return
     */
    String getPhoneNumber();

    /**
     * 获取密码
     *
     * @return
     */
    String getPassWord();

    /**
     * 登陆成功
     *
     * @param user
     */
    void onLoginSuccess(User user);
}
