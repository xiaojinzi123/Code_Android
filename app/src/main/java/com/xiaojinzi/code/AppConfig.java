package com.xiaojinzi.code;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.xiaojinzi.code.netApi.NetWorkService;
import com.xiaojinzi.viewinjection.log.L;

import java.io.IOException;

import cn.smssdk.SMSSDK;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
    public static final String APPPREFIX = "http://192.168.1.101:8080/code/app/";
//    public static final String APPPREFIX = "http://192.168.137.1:8080/code/app/";

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

                            Request request = chain.request();

                            //拿到请求网址
                            String url = request.url().toString();

                            //如果不是注册登录的接口
                            if (!url.endsWith("user/login") && !url.endsWith("user/register")) {

                                //拿到请求对象
                                RequestBody body = request.body();

                                //表单请求构建对象
                                FormBody.Builder builder = new FormBody.Builder();

                                //如果请求就是表单的请求
                                if (body instanceof FormBody) {
                                    //强转
                                    FormBody formBody = (FormBody) body;
                                    //原先的表单中的字段转到新的表单构建对象中
                                    for (int i = 0; i < formBody.size(); i++) {
                                        String name = formBody.name(i);
                                        String value = formBody.value(i);
                                        builder.add(name, value);
                                    }
                                }

                                //然后再生成一个新的表单请求
                                FormBody formBody = builder
                                        .add(Constant.USERID_FLAG, AppInfo.user.getId() + "")
                                        .add(Constant.CLIENTTOKEN_FLAG, AppInfo.user.getClientToken())
                                        .build();

                                StringBuffer sb = new StringBuffer();
                                int size = formBody.size();
                                for (int i = 0; i < size; i++) {
                                    String name = formBody.name(i);
                                    String value = formBody.value(i);
                                    if (sb.length() == 0) {
                                        sb.append(name + "=" + value);
                                    } else {
                                        sb.append("&" + name + "=" + value);
                                    }
                                }

                                L.s("AppConFig", url + "?" + sb.toString());

                                //生成请求
                                request = new Request.Builder()
                                        .headers(request.headers())
                                        .url(url)
                                        .method(request.method(), formBody)
                                        .build();
                            }

                            //执行请求
                            Response response = chain.proceed(request);

                            //返回结果
                            return response;

                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(APPPREFIX)
                    .client(okHttpClient)
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
