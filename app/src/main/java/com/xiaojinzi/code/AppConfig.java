package com.xiaojinzi.code;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.xiaojinzi.code.netApi.NetWorkService;

import java.io.IOException;

import cn.smssdk.SMSSDK;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cxj on 2016/10/25.
 * 项目的配置类
 */
public class AppConfig {

    /**
     * 项目的访问根路径
     */
    public static final String APPPREFIX = "http://192.168.137.1:8080/code/app/";

    /**
     * 项目的网络对象
     */
    public static NetWorkService netWorkService = null;

    /**
     * 网络请求框架
     */
    public static Retrofit retrofit;

    /**
     * 是否初始化了
     */
    private static boolean isInit = false;

    /**
     * json解析器
     */
    public static Gson gson;

    /**
     * 初始化项目的组建
     *
     * @param context 上下文
     */
    public static void init(Context context) {

        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {

                            Response response = chain.proceed(chain.request());

                            return response;

                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(APPPREFIX)
//                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (netWorkService == null) {
            //让框架自动实现我们的请求接口,让我们的请求接口可以被调用
            netWorkService = retrofit.create(NetWorkService.class);
        }

        if (gson == null) {
            //初始化json解析框架
            gson = new Gson();

        }

        SMSSDK.initSDK(context, "186d2e3c74000", "ed1b490c7eecc2bd0920003d4b6b946a");

        //初始化图片加载框架
        Fresco.initialize(context);

    }

}
