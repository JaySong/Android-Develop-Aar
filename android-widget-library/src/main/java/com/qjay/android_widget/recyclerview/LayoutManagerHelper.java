package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * 布局管理器管理类
 * Created by Q.Jay on 2015/8/30 0030.
 */
public class LayoutManagerHelper {

    public static LinearLayoutManager buildLinerLayoutManager(Context context) {
        return buildLinerLayoutManager(context,false,0);
    }

    public static LinearLayoutManager buildLinerLayoutManager(Context context, boolean isAutoHeight,int dividerSpace) {
        return new SuperLinearLayoutManager(context, isAutoHeight,dividerSpace);
    }

    public static LinearLayoutManager buildLinerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        return buildLinerLayoutManager(context,false, orientation, reverseLayout,0);
    }

    public static LinearLayoutManager buildLinerLayoutManager(Context context, boolean isAutoHeight, int orientation, boolean reverseLayout,int dividerSpace) {
        return new SuperLinearLayoutManager(context, isAutoHeight, orientation, reverseLayout,dividerSpace);
    }

    public static LinearLayoutManager buildLinerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                                              int defStyleRes) {
        return buildLinerLayoutManager(context,false, attrs, defStyleAttr, defStyleRes,0);
    }

    public static LinearLayoutManager buildLinerLayoutManager(Context context, boolean isAutoHeight, AttributeSet attrs, int defStyleAttr,
                                                              int defStyleRes,int dividerSpace) {
        return new SuperLinearLayoutManager(context, isAutoHeight, attrs, defStyleAttr, defStyleRes,dividerSpace);
    }


    public static GridLayoutManager buildGridLayoutManager(Context context,int spanCount) {

        return buildGridLayoutManager(context, spanCount, false,0);
    }
    public static GridLayoutManager buildGridLayoutManager(Context context,int spanCount, boolean isAutoHeight,int dividerSpace) {
        return new SuperGridLayoutManager(context,spanCount, isAutoHeight,dividerSpace);
    }
    public static GridLayoutManager buildGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        return new SuperGridLayoutManager(context,attrs,defStyleAttr,defStyleRes);
    }

    public static GridLayoutManager buildGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        return buildGridLayoutManager(context,spanCount,orientation,reverseLayout,false,0);
    }
    public static GridLayoutManager buildGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout,boolean isAutoHeight,int dividerSpace) {
        return new SuperGridLayoutManager(context,spanCount,orientation,reverseLayout,isAutoHeight,dividerSpace);
    }
}
