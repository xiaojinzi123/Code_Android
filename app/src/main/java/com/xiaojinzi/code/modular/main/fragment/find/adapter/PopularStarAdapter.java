package com.xiaojinzi.code.modular.main.fragment.find.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.PopularStar;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewAdapter;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewHolder;

import java.util.List;

/**
 * Created by cxj on 2016/11/3.
 */
public class PopularStarAdapter extends CommonRecyclerViewAdapter<PopularStar> {

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param data    显示的数据
     */
    public PopularStarAdapter(Context context, List<PopularStar> data) {
        super(context, data);
    }

    @Override
    public void convert(CommonRecyclerViewHolder h, PopularStar entity, int position) {
        SimpleDraweeView icon = h.getView(R.id.icon);
        icon.setImageURI(Uri.parse(entity.getUser().getAvatarAddress()));
    }

    @Override
    public int getLayoutViewId(int viewType) {
        return R.layout.frag_find_header_popularstar_item;
    }
}
