package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.view;

import com.xiaojinzi.code.common.bean.BugComment;
import com.xiaojinzi.code.mvp.v.IBaseView;

import java.util.List;

/**
 * Created by cxj on 2016/11/4.
 */
public interface IBugDetailFragmentView extends IBaseView {

    /**
     * 加载Bug动态的评论成功
     *
     * @param bugComments
     */
    void onLoadBugCommentSuccess(List<BugComment> bugComments);
}
