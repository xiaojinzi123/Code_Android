package com.xiaojinzi.code.util;


import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewAdapter;

import java.util.List;


/**
 * Created by cxj on 2016/9/12.
 * 通知适配器,针对RecyclerView的适配器
 */
public class AdapterNotify {

    public static <T> void notifyFreshData(List<T> realData, List<T> data, CommonRecyclerViewAdapter adapter) {

        int size = realData.size();

        realData.clear();

        adapter.notifyItemRangeRemoved(0 + adapter.getHeaderCounts(), size);

        realData.addAll(data);

        adapter.notifyItemRangeInserted(0 + adapter.getHeaderCounts(), data.size());

    }

    public static <T> void notifyAppendData(List<T> realData, List<T> data, CommonRecyclerViewAdapter adapter) {

        int size = realData.size();

        realData.addAll(data);

        adapter.notifyItemRangeInserted(size + adapter.getHeaderCounts(), data.size());

    }

}
