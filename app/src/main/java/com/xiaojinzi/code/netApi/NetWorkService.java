package com.xiaojinzi.code.netApi;

import com.xiaojinzi.code.common.bean.BlogDynamics;
import com.xiaojinzi.code.common.bean.BugComment;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.common.bean.PopularStar;
import com.xiaojinzi.code.common.bean.ProLan;
import com.xiaojinzi.code.common.bean.User;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.main.fragment.find.bean.Adv;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by cxj on 2016/10/27.
 */
public interface NetWorkService {

    /**
     * 登陆
     *
     * @param phoneNumber 电话号码
     * @param password    密码
     * @return 返回请求执行对象
     */
    @FormUrlEncoded
    @POST("user/login")
    Call<BaseNetWorkResult<User>> login(
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password
    );

    /**
     * 注册
     *
     * @param phoneNumber
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Call<BaseNetWorkResult<User>> register(
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password
    );


    /**
     * 分页获取主页动态
     *
     * @param timestamp 时间戳
     * @param proLanId  编程语言id
     * @return
     */
    @FormUrlEncoded
    @POST("dynamics/queryForBugList")
    Call<BaseNetWorkResult<List<BugDynamics>>> getHomeDynamics(
            @Field("timestamp") long timestamp,
            @Field("proLanId") Integer proLanId
    );

    /**
     * 分页获取发现页动态
     *
     * @param timestamp
     * @return
     */
    @FormUrlEncoded
    @POST("dynamics/queryForBlogList")
    Call<BaseNetWorkResult<List<BlogDynamics>>> getFindDynamics(
            @Field("timestamp") long timestamp
    );

    /**
     * 获取所有的编程语言
     *
     * @return
     */
    @POST("proLan/queryAllProLan")
    Call<BaseNetWorkResult<List<ProLan>>> queryAllProLan(
    );


    /**
     * 获取所有的广告
     *
     * @return
     */
    @POST("adv/queryAllAdv")
    Call<BaseNetWorkResult<List<Adv>>> getAllAdv(
    );

    /**
     * 获取所有人气明星
     *
     * @return
     */
    @POST("popularStar/queryAllPopularStar")
    Call<BaseNetWorkResult<List<PopularStar>>> getAllPopularStar(
    );

    /**
     * 获取动态详情,能获取到内容
     *
     * @param dynamicsId 动态的id
     * @return
     */
    @FormUrlEncoded
    @POST("dynamics/queryForBugById")
    Call<BaseNetWorkResult<BugDynamics>> queryBugDynamicsById(
            @Field("dynamicsId") int dynamicsId
    );

    /**
     * 加载动态的评论
     *
     * @param timestamp
     * @param dynamicsId
     * @return
     */
    @FormUrlEncoded
    @POST("comment/queryForBugComment")
    Call<BaseNetWorkResult<List<BugComment>>> queryBugComment(
            @Field("timestamp") long timestamp,
            @Field("dynamicsId") int dynamicsId
    );

}
