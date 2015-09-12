package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Q.Jay on 2015/9/1 0001.
 */
public class SuperLinearLayoutManager extends LinearLayoutManager {
    private boolean mIsAutoHeight;
    private int mDividerSpace;

    public SuperLinearLayoutManager(Context context) {
        this(context, false,0);
    }

    public SuperLinearLayoutManager(Context context, boolean isAutoHeight,int dividerSpace) {
        super(context);
        this.mIsAutoHeight = isAutoHeight;
        this.mDividerSpace = dividerSpace;
    }

    public SuperLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        this(context, false, orientation, reverseLayout,0);
    }

    public SuperLinearLayoutManager(Context context, boolean isAutoHeight, int orientation, boolean reverseLayout,int dividerSpace) {
        super(context, orientation, reverseLayout);
        this.mIsAutoHeight = isAutoHeight;this.mDividerSpace = dividerSpace;
    }

    public SuperLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, false, attrs, defStyleAttr, defStyleRes,0);
    }

    public SuperLinearLayoutManager(Context context, boolean isAutoHeight, AttributeSet attrs, int defStyleAttr, int defStyleRes,int dividerSpace) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mIsAutoHeight = isAutoHeight;this.mDividerSpace = dividerSpace;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if (mIsAutoHeight) {
//            final int widthMode = View.MeasureSpec.getMode(widthSpec);
            final int heightMode = View.MeasureSpec.getMode(heightSpec);
//            final int widthSize = View.MeasureSpec.getSize(widthSpec);
            final int heightSize = View.MeasureSpec.getSize(heightSpec);
//            int width = 0;
            int height = 0;
            int itemCount = getItemCount();
            for (int i = 0; i < itemCount; i++) {
                measureScrapChild(recycler, i,
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED));
                View childAt = recycler.getViewForPosition(i);
                // TODO 这里的算法还需要重新设计

//                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(childAt);
                int decoratedMeasuredHeight = getDecoratedMeasuredHeight(childAt);
                if (getOrientation() == HORIZONTAL) {
//                        width = width + decoratedMeasuredWidth + mDividerSpace;
                    height = height >= decoratedMeasuredHeight ? height : decoratedMeasuredHeight;
//                    if (i == 0) {
//                        height = mMeasuredDimension[1] ;
//                    }
                } else {
                        height = height + decoratedMeasuredHeight + mDividerSpace;
//                    width = width >= decoratedMeasuredWidth ? width : decoratedMeasuredWidth;
//                    if (i == 0) {
//                        width = mMeasuredDimension[0];
//                    }
                }
            }
//            switch (widthMode) {
//                case View.MeasureSpec.EXACTLY:
////                    width = widthSize;
//                case View.MeasureSpec.AT_MOST:
//                case View.MeasureSpec.UNSPECIFIED:
//            }

            switch (heightMode) {
                case View.MeasureSpec.EXACTLY:
                    height = heightSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }
//            widthSpec = View.MeasureSpec.makeMeasureSpec(width, widthMode);
            heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
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
