package com.xiaojinzi.code.modular.main.fragment.home.view;

import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.common.bean.ProLan;
import com.xiaojinzi.code.mvp.v.IBaseView;

import java.util.List;

/**
 * Created by cxj on 2016/10/28.
 */
public interface IHomeView extends IBaseView {

    /**
     * 加载动态成功
     *
     * @param dynamicses
     */
    void onLoadHomeDynamicsSuccess(List<BugDynamics> dynamicses);


    /**
     * 加载所有的编程语言成功
     *
     * @param proLans
     */
    void onLoadProLanSuccess(List<ProLan> proLans);

    /**
     * 加载更多动态成功
     *
     * @param dynamicses
     */
    void onLoadMoreHomeDynamicsSuccess(List<BugDynamics> dynamicses);


    /**
     * 加载更多动态失败
     *
     * @param s 失败的错误信息
     */
    void onLoadHomeDynamicsFail(String s);

}
