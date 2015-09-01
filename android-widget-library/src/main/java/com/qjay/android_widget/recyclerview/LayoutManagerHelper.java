package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * 布局管理器管理类
 * Created by Q.Jay on 2015/8/30 0030.
 */
public class LayoutManagerHelper {

    public static void builLinerLayoutManager(Context context) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);


    }


    public static void builLinerLayoutManager(Context context,int orientation,boolean reverseLayout){
        new LinearLayoutManager(context, orientation, reverseLayout);
    }

    public static void builLinerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                              int defStyleRes){

    }
}
