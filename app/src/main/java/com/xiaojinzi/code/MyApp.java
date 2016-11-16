package com.xiaojinzi.code;

import android.app.Application;

/**
 * Created by cxj on 2016/10/27.
 */
public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        AppConfig.init(this);

        //瞎写一点东西

    }


}
