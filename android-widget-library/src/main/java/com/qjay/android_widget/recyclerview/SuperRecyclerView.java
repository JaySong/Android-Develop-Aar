package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.qjay.android_widget.R;
import com.qjay.android_widget.recyclerview.divider.HorizontalDividerItemDecoration;
import com.qjay.android_widget.recyclerview.divider.VerticalDividerItemDecoration;

/**
 *
 * Created by Q.Jay on 2015/8/26 0026.
 */
public class SuperRecyclerView extends BaseRecyclerView {
    private static final int LINEAR_LAYOUT_MANAGER = 0;
    private static final int GRID_LAYOUT_MANAGER = 1;
    private static final int STAGGERED_GRID_LAYOUT_MANAGER = 2;

    private boolean isAutoHeight;

    private int mOrientation = VERTICAL;

    private static final int DEFAULT_LAYOUT_MANAGER = LINEAR_LAYOUT_MANAGER;

    private int mLayoutManager;

    private int mDividerMargin;
    private int mDividerMarginLeft;
    private int mDividerMarginRight;
    private int mDividerMarginTop;
    private int mDividerMarginBottom;

    private int mDividerSpace;
    private int mDividerColor;

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
            mOrientation = a.getInt(R.styleable.SuperRecyclerView_orientation, mOrientation);
            isAutoHeight = a.getBoolean(R.styleable.SuperRecyclerView_isAutoHeight, false);
            mDividerMargin = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMargin, 0);
            mDividerColor = a.getColor(R.styleable.SuperRecyclerView_dividerColor, Color.GRAY);
            mDividerSpace = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerSpace, 0);
            if(mDividerMargin == 0) {
                mDividerMarginLeft = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginLeft, 0);
                mDividerMarginRight = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginRight, 0);
                mDividerMarginTop = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginTop, 0);
                mDividerMarginBottom = a.getDimensionPixelSize(R.styleable.SuperRecyclerView_dividerMarginBottom, 0);
            }
            switch (mLayoutManager) {
                case LINEAR_LAYOUT_MANAGER:
                    setLinearLayoutManager(context, a);
                    break;
                case GRID_LAYOUT_MANAGER:
                    setGridLayoutManager(context,a);
                    break;
            }
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        if (mDividerSpace != 0) {
            initItemDivider();
        }
    }

    /**
     * 设置流式布局管理器
     * @param context
     * @param a
     */
    private void setLinearLayoutManager(Context context,TypedArray a) {
        LinearLayoutManager layoutManager = LayoutManagerHelper.buildLinerLayoutManager(context, isAutoHeight,mOrientation, false,mDividerSpace);
        this.setLayoutManager(layoutManager);
    }

    /**
     * 设置风格布局管理器
     * @param context
     * @param a
     */
    private void setGridLayoutManager(Context context,TypedArray a) {
        int spanCount = a.getInt(R.styleable.SuperRecyclerView_span_count, 1);
        GridLayoutManager layoutManager = LayoutManagerHelper.buildGridLayoutManager(context, spanCount, mOrientation, false, isAutoHeight,mDividerSpace);
        this.setLayoutManager(layoutManager);
    }


    private void initItemDivider(){
        if(mOrientation ==VERTICAL){
            this.addItemDecoration(buildHorizontalDivider());
        }else{
            this.addItemDecoration(buildVerticalDivider());
        }
    }

    private HorizontalDividerItemDecoration buildHorizontalDivider(){
        HorizontalDividerItemDecoration.Builder divider = new HorizontalDividerItemDecoration.Builder(getContext()).color(mDividerColor).size(mDividerSpace);
        if(mDividerMargin == 0) {
            divider.margin(mDividerMarginLeft, mDividerMarginRight);
        }else {
            divider.margin(mDividerMargin);
        }
        return divider.build();
    }
    private VerticalDividerItemDecoration buildVerticalDivider(){
        VerticalDividerItemDecoration.Builder divider = new VerticalDividerItemDecoration.Builder(getContext()).color(mDividerColor);
        if(mDividerMargin == 0) {
            divider.margin(mDividerMarginTop, mDividerMarginBottom);
        }else {
            divider.margin(mDividerMargin);
        }
        return divider.build();
    }
}
