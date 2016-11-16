package com.xiaojinzi.code.modular.imagePreview.view;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaojinzi.code.R;
import com.xiaojinzi.code.modular.base.BaseAct;
import com.xiaojinzi.code.util.ViewPagerViewAdapter;
import com.xiaojinzi.viewinjection.annotation.Injection;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片预览的界面
 */
@Injection(R.layout.act_image_preview)
public class ImagePreviewAct extends BaseAct {

    public static final String IMAGES_FLAG = "images_flag";

    public static final String IMAGES_INDEX_FLAG = "images_index_flag";

    @Injection(R.id.vp)
    ViewPager vp;

    private List<View> views = new ArrayList<View>();

    @Injection(R.id.tv_tip1)
    TextView tv_tip1;

    @Injection(R.id.tv_tip2)
    TextView tv_tip2;

    @Override
    protected void initView() {
        super.initView();

        int index = getIntent().getIntExtra(IMAGES_INDEX_FLAG, 0);

        //获取到图片的路径的数组
        String[] imagesArr = getIntent().getStringArrayExtra(IMAGES_FLAG);

        if (imagesArr == null) {
            return;
        }

        for (int i = 0; i < imagesArr.length; i++) {
            //拿到网络路径
            String path = imagesArr[i];

            //拿到显示图片的试图
            View v = View.inflate(mContext, R.layout.act_image_preview_item, null);

            SimpleDraweeView sdv = (SimpleDraweeView) v.findViewById(R.id.sdv);
            sdv.setImageURI(Uri.parse(path));

            views.add(v);

        }

        //设置适配器
        vp.setAdapter(new ViewPagerViewAdapter(views));

        //设置第几张被选中
        vp.setCurrentItem(index);

        showTip(index);


    }

    @Override
    protected void initLinstener() {
        super.initLinstener();

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                showTip(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void showTip(int index) {
        tv_tip1.setText((index + 1) + "/" + views.size());
        tv_tip2.setText((index + 1) + "/" + views.size());
    }

}
