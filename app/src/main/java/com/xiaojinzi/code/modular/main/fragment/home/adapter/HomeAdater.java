package com.xiaojinzi.code.modular.main.fragment.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.common.bean.BugDynamics;
import com.xiaojinzi.code.common.bean.Dynamics;
import com.xiaojinzi.code.modular.base.DynamicsAdapter;
import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewHolder;
import com.xiaojinzi.code.util.widget.CommonNineView;

import java.util.List;

/**
 * Created by cxj on 2016/11/3.
 */
public class HomeAdater extends DynamicsAdapter<BugDynamics> {

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param data    显示的数据
     */
    public HomeAdater(Context context, List<BugDynamics> data) {
        super(context, data);
    }

    @Override
    public void convert(CommonRecyclerViewHolder h, BugDynamics entity, int position) {
        super.convert(h, entity, position);

        //显示标题
        h.setText(R.id.tv_title, entity.getTitle());

        //找到显示图片的控件
        CommonNineView cnv = h.getView(R.id.cnv_images);
        cnv.removeAllViewsInLayout();
        cnv.setHorizontalIntervalDistance(10);
        cnv.setVerticalIntervalDistance(10);

        //拿到动态的类型
        Integer dynamicsType = entity.getDynamicsType();
        if (dynamicsType == Dynamics.IMAGE_TYPE_DYNAMICS) { //如果是图片类型的
            cnv.setVisibility(View.VISIBLE);
            String images = entity.getImages();
            String[] imagesArr = images.split(Dynamics.IMAGE_SPLIT_CHAR);
            for (int i = 0; i < imagesArr.length; i++) {
                //创建图片显示的控件
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                //设置图片地址
                simpleDraweeView.setImageURI(Uri.parse(imagesArr[i]));
                //添加到九宫格里面
                cnv.addClildView(simpleDraweeView);
            }
        } else {
            cnv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutViewId(int viewType) {
        return R.layout.bugdynamics_content_item;
    }
}
