package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Q.Jay on 2015/8/26 0026.
 */
public class BaseRecyclerView extends RecyclerView {
    public BaseRecyclerView(Context context) {
        this(context, null);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addOnItemClickListener(SuperRecyclerViewAdapter.OnItemClickListener listener) {
        Adapter adapter = getAdapter();
        if (adapter instanceof SuperRecyclerViewAdapter) {
            ((SuperRecyclerViewAdapter) adapter).addOnItemClickListener(listener);
        }
    }
}
