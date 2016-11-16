package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.view;

import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.mvp.v.IBaseView;

/**
 * Created by cxj on 2016/11/2.
 */
public interface IHeaderView extends IBaseView {

    /**
     * 加载动态详情成功
     *
     * @param bugDynamics
     */
    void onLoadBugDynamicsSuccess(BugDynamics bugDynamics);

}
