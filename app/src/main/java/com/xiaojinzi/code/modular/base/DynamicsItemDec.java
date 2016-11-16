package com.xiaojinzi.code.modular.base;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaojinzi.code.autolayout.utils.AutoUtils;

/**
 * Created by cxj on 2016/10/29.
 */
public class DynamicsItemDec extends RecyclerView.ItemDecoration {

    private int startIndex;

    private int v;

    public DynamicsItemDec(int startIndex) {
        this.startIndex = startIndex;
        v = AutoUtils.getPercentHeightSize(24);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (position >= startIndex) {
            outRect.top = v;
        }
    }
}
