package com.xiaojinzi.code.modular.dynamicsDetail.bugDetail.fragment.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.BugComment;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewAdapter;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewHolder;

import java.util.List;

/**
 * Created by cxj on 2016/11/2.
 */
public class CommentAdapter extends CommonRecyclerViewAdapter<BugComment> {

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param data    显示的数据
     */
    public CommentAdapter(Context context, List<BugComment> data) {
        super(context, data);
    }

    @Override
    public void convert(CommonRecyclerViewHolder h, BugComment entity, int position) {

        //显示头像
        SimpleDraweeView icon = h.getView(R.id.icon);
        icon.setImageURI(Uri.parse(entity.getUser().getAvatarAddress()));

        //显示用户名
        h.setText(R.id.tv_name, entity.getUser().getName());

        //显示内容
        h.setText(R.id.tv_content, entity.getContent());

    }

    @Override
    public int getLayoutViewId(int viewType) {
        return R.layout.frag_bug_detail_item;
    }

}
