package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.qjay.android_widget.R;

/**
 *
 * Created by Q.Jay on 2015/8/26 0026.
 */
public class SuperRecyclerView extends BaseRecyclerView {
    private static final int DEFAULT_LAYOUT_MANAGER = 0;
    private int mLayoutManager;



    public SuperRecyclerView(Context context) {
        this(context, null);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SuperRecyclerView);
        try {
            mLayoutManager = a.getInt(R.styleable.SuperRecyclerView_layout_manager, DEFAULT_LAYOUT_MANAGER);
//            this.mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontal_spacing, DEFAULT_HORIZONTAL_SPACING);
//            this.mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_vertical_spacing, DEFAULT_VERTICAL_SPACING);
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {

    }

}
