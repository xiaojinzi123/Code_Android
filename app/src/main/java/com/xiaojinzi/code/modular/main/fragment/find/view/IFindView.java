package com.xiaojinzi.code.modular.main.fragment.find.view;

import com.xiaojinzi.code.common.bean.BlogDynamics;
import com.xiaojinzi.code.mvp.v.IBaseView;

import java.util.List;

/**
 * Created by cxj on 2016/11/3.
 */
public interface IFindView extends IBaseView {

    /**
     * 加载发现页面的动态成功
     *
     * @param dynamicses
     */
    void onLoadFindDynamicsSuccess(List<BlogDynamics> dynamicses);

}
