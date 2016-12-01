package com.xiaojinzi.code.util;


import com.xiaojinzi.code.util.recyclerView.CommonRecyclerViewAdapter;

import java.util.List;


/**
 * Created by cxj on 2016/9/12.
 * 通知适配器,针对RecyclerView的适配器
 */
public class AdapterNotify {

    /**
     * 通知刷新数据
     *
     * @param realData
     * @param data
     * @param adapter
     * @param <T>
     */
    public static <T> void notifyFreshData(List<T> realData, List<T> data, CommonRecyclerViewAdapter adapter) {

        //拿到真正集合目前的长度
        int realSize = realData.size();

        //拿到新的数据的长度
        int newSize = data.size();

        //清空原有的集合
        realData.clear();

        //添加新的数据
        realData.addAll(data);

        if (newSize > realSize) { //如果新数据的长度大于原有的
            if (realSize != 0) {
                //原先的就是数据改变
                adapter.notifyItemRangeChanged(0, realSize);
            }
            //多出来的就是插入的
            adapter.notifyItemRangeInserted(realSize, newSize - realSize);
        } else if(newSize < realSize) {
            //如果新的数据比原来的少,那么先改变新数据长度的数据
            adapter.notifyItemRangeChanged(0, newSize);
            //剩下的删除
            adapter.notifyItemRangeRemoved(newSize, realSize - newSize);
        }else{
            //原先的就是数据改变
            adapter.notifyItemRangeChanged(0, realSize);
        }

    }

    public static <T> void notifyAppendData(List<T> realData, List<T> data, CommonRecyclerViewAdapter adapter) {

        int size = realData.size();

        realData.addAll(data);

        adapter.notifyItemRangeInserted(size + adapter.getHeaderCounts(), data.size());

    }

}
