package com.xiaojinzi.code.modular.main.fragment.find.view;

import com.xiaojinzi.code.common.bean.PopularStar;
import com.xiaojinzi.code.modular.main.fragment.find.bean.Adv;
import com.xiaojinzi.code.mvp.v.IBaseView;

import java.util.List;

/**
 * Created by cxj on 2016/10/30.
 */
public interface IHeaderView extends IBaseView {

    /**
     * 加载广告成功
     *
     * @param advs 广告数据的集合
     */
    void onLoadAllAdvSuccess(List<Adv> advs);

    /**
     * 加载人气明星成功
     *
     * @param popularStars
     */
    void onLoadAllPopularStarsSuccess(List<PopularStar> popularStars);

}
