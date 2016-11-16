package com.xiaojinzi.code.modular.base;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.Dynamics;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewAdapter;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewHolder;

import java.util.List;

/**
 * Created by cxj on 2016/10/30.
 * 动态的适配器,每个要显示的界面继承即可
 */
public class DynamicsAdapter<T extends Dynamics> extends CommonRecyclerViewAdapter<T> {

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param data    显示的数据
     */
    public DynamicsAdapter(Context context, List<T> data) {
        super(context, data);
    }

    /**
     * 实体对象的属性往界面上怼
     *
     * @param h        RecycleView的ViewHolder
     * @param entity   实体对象
     * @param position 当前的下标
     */
    @Override
    public void convert(CommonRecyclerViewHolder h, T entity, int position) {

        //显示头像
        SimpleDraweeView sdv = h.getView(R.id.sdv);
        sdv.setImageURI(Uri.parse(entity.getUser().getAvatarAddress()));

        //显示名称
        h.setText(R.id.tv_user_name, entity.getUser().getName());

        //显示编程语言
        h.setText(R.id.tv_pl_tag, entity.getProLan().getName());

    }

    @Override
    public int getLayoutViewId(int viewType) {
        return 0;
    }


}
