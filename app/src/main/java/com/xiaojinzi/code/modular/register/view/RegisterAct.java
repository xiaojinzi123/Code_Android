package com.xiaojinzi.code.modular.register.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.modular.base.BaseAct;
import com.xiaojinzi.code.modular.register.presenter.RegisterPresenter;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * 注册的页面
 */
@Injection(R.layout.act_register)
public class RegisterAct extends BaseAct implements IRegisterView {

    @Injection(value = R.id.et_account, click = "clickView")
    EditText et_account;

    @Injection(R.id.et_password)
    EditText et_password;

    @Injection(value = R.id.bt_register, click = "clickView")
    Button bt_register;

    private RegisterPresenter presenter = new RegisterPresenter(this);

    /**
     * 验证手机号
     */
    private void validatePhoneNumber() {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {

                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    et_account.setText(phone);

                }
            }
        });

        registerPage.show(mContext);
    }

    /**
     * 点击事件的集中处理
     *
     * @param v
     */
    public void clickView(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_register: //如果是注册按钮
                presenter.register();
                break;
            case R.id.et_account: //如果是手机号
                validatePhoneNumber();
                break;
        }
    }


    @Override
    public String getPhoneNumber() {
        return et_account.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return et_password.getText().toString().trim();
    }

    @Override
    public void onRegisterSuccess(User user) {
        System.out.println("user = " + user);
    }


}
