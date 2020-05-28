package com.admin.listen.myView;

import android.view.View;

public class RecycleViewLisitenter {

    /**
     * RecycleView的条目点击监听
     */
    public interface onItemClickLisitenter {
        void onItemClick(View view, int position);
    }

    /**
     * RecycleView的条目点击数据返回监听
     */
    public interface onItemClickDataLisitenter {
        void onItemDataClick(View view, Object data, int position);
    }


    /**
     * RecycleView的条目长按点击监听
     */
    public interface onItemLongClickLisitenter {
        void onItemLongClick(View v, int position);
    }

}