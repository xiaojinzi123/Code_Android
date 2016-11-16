package com.xiaojinzi.code.modular.register.view;

import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.mvp.v.IBaseView;

/**
 * Created by cxj on 2016/10/28.
 */
public interface IRegisterView extends IBaseView {

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
     * 注册成功
     *
     * @param user 用户信息
     */
    void onRegisterSuccess(User user);

}
