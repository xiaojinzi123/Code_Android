package com.xiaojinzi.code.modular.login.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaojinzi.code.AppInfo;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.modular.base.BaseAct;
import com.xiaojinzi.code.modular.login.presenter.LoginPresenter;
import com.xiaojinzi.code.modular.main.view.MainAct;
import com.xiaojinzi.code.modular.register.view.RegisterAct;
import com.xiaojinzi.code.util.ActivityUtil;
import com.xiaojinzi.viewinjection.annotation.Injection;

/**
 * 登陆的界面
 */
@Injection(R.layout.act_login)
public class LoginAct extends BaseAct implements ILoginView {

    @Injection(R.id.et_account)
    EditText et_account;

    @Injection(R.id.et_password)
    EditText et_password;

    @Injection(value = R.id.tv_register, click = "clickView")
    TextView tv_register;

    @Injection(value = R.id.bt_login, click = "clickView")
    Button bt_login;

    /**
     * 登陆的主持人
     */
    private LoginPresenter presenter = new LoginPresenter(this);

    /**
     * 点击
     *
     * @param v
     */
    public void clickView(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_register:
                ActivityUtil.startActivity(mContext, RegisterAct.class);
                finish();
                break;
            case R.id.bt_login:
                presenter.login();
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
    public void onLoginSuccess(User user) {
        AppInfo.user = user;
        ActivityUtil.startActivity(mContext, MainAct.class);
        finish();
    }

}
