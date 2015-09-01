package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Q.Jay on 2015/9/1 0001.
 */
public class SuperGridLayoutManager extends GridLayoutManager {
    private boolean mIsAutoHeight;
    private int mDividerSpace;

    public SuperGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SuperGridLayoutManager(Context context, int spanCount) {
        this(context, spanCount, false,0);
    }

    public SuperGridLayoutManager(Context context, int spanCount, boolean isAutoHeight,int  dividerSpace) {
        super(context, spanCount);
        this.mIsAutoHeight = isAutoHeight;
        this.mDividerSpace = dividerSpace;
    }

    public SuperGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        this(context, spanCount, orientation, reverseLayout, false,0);
    }

    public SuperGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout, boolean isAutoHeight,int  dividerSpace) {
        super(context, spanCount, orientation, reverseLayout);
        this.mIsAutoHeight = isAutoHeight;this.mDividerSpace = dividerSpace;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if (mIsAutoHeight) {
            final int widthMode = View.MeasureSpec.getMode(widthSpec);
            final int heightMode = View.MeasureSpec.getMode(heightSpec);
            final int widthSize = View.MeasureSpec.getSize(widthSpec);
            final int heightSize = View.MeasureSpec.getSize(heightSpec);
            int rowWidth = 0;
            int rowHeight = 0;
            for (int i = 0,width = 0,height = 0; i < getItemCount(); i++) {
                measureScrapChild(recycler, i,
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED));
                View childAt = recycler.getViewForPosition(i);
                // TODO 这里的算法还需要重新设计

                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(childAt);
                int decoratedMeasuredHeight = getDecoratedMeasuredHeight(childAt);

                width = width + decoratedMeasuredWidth;
                height = height >= decoratedMeasuredHeight ? height : decoratedMeasuredHeight;
                if (getOrientation() == HORIZONTAL) {
                    if ((i + 1) % getSpanCount() == 0) {//当前行的最后一个
                        rowWidth = rowWidth >= width ? rowWidth : width;
                        width = 0;
                        rowHeight = rowHeight >= height ? rowHeight : height;
                        height = 0;
                    }else{
                        width += mDividerSpace;
                    }
                } else {
                    if ((i + 1) % getSpanCount() == 0) {//当前行的最后一个
                        rowWidth = rowWidth >= width ? rowWidth : width;
                        width = 0;
                        rowHeight = rowHeight >= height ? rowHeight : height;
                        height = 0;
                    }else {
                        height += mDividerSpace;
                    }
//                    height = height + decoratedMeasuredHeight;
//                    width = width >= decoratedMeasuredWidth ? width : decoratedMeasuredWidth;
                }
            }
            switch (widthMode) {
                case View.MeasureSpec.EXACTLY:
                    rowWidth = widthSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            switch (heightMode) {
                case View.MeasureSpec.EXACTLY:
                    rowHeight = heightSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            widthSpec = View.MeasureSpec.makeMeasureSpec(rowWidth, widthMode);
            heightSpec = View.MeasureSpec.makeMeasureSpec(rowHeight, heightMode);
        }


        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }


    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec) {
        View view = recycler.getViewForPosition(position);
        if (view != null) {
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                    getPaddingLeft() + getPaddingRight(), p.width);
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                    getPaddingTop() + getPaddingBottom(), p.height);
            view.measure(childWidthSpec, childHeightSpec);
//            measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
//            measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
            recycler.recycleView(view);
        }
    }
}
