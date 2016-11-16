package com.xiaojinzi.code.modular.main.fragment.find.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.BlogDynamics;
import com.xiaojinzi.code.modular.base.DynamicsAdapter;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewHolder;

import java.util.List;

/**
 * Created by cxj on 2016/11/3.
 * 发现页面的适配器
 */
public class FindAdapter extends DynamicsAdapter<BlogDynamics> {

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param data    显示的数据
     */
    public FindAdapter(Context context, List<BlogDynamics> data) {
        super(context, data);
    }

    @Override
    public void convert(CommonRecyclerViewHolder h, BlogDynamics entity, int position) {
        super.convert(h, entity, position);

        //对于原创还是引用的两个文字做了颜色的处理,一眼就能看到是原创的还是引用的
        TextView tv_title = h.getView(R.id.tv_title);
        SpannableString spanString = new SpannableString(entity.getTag1() == 0 ? "[原创]" : "[引用]");
        int color = entity.getTag1() == 0 ? Color.RED : Color.GRAY;
        spanString.setSpan(new ForegroundColorSpan(color), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色金色
        tv_title.setText(spanString);
        tv_title.append("  " + entity.getTitle());

    }

    @Override
    public int getLayoutViewId(int viewType) {
        return R.layout.blogdynamics_content_item;
    }

}
