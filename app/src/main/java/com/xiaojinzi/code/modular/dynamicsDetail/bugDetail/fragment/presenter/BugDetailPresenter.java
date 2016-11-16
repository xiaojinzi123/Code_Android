package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.presenter;

import com.xiaojinzi.code.AppConfig;
import com.xiaojinzi.code.common.bean.BugComment;
import com.xiaojinzi.code.modular.base.BaseNetWorkResult;
import com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.view.IBugDetailFragmentView;
import com.xiaojinzi.code.mvp.m.CallBackAdapter;

import java.util.List;

import retrofit2.Call;

/**
 * Created by cxj on 2016/11/2.
 */
public class BugDetailPresenter {

    private IBugDetailFragmentView view;

    public BugDetailPresenter(IBugDetailFragmentView view) {
        this.view = view;
    }

    /**
     * 加载评论
     *
     * @param dynamicsId 动态的id
     * @param timestamp  时间戳
     */
    public void loadBugComment(Integer dynamicsId, long timestamp) {

        Call<BaseNetWorkResult<List<BugComment>>> call = AppConfig.netWorkService.queryBugComment(timestamp, dynamicsId);

        call.enqueue(new CallBackAdapter<List<BugComment>>(view) {
            @Override
            public void onResponse(List<BugComment> bugComments) {
                view.onLoadBugCommentSuccess(bugComments);
            }
        });

    }

}
