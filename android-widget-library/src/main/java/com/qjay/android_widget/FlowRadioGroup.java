package com.qjay.android_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by JaySeng on 2015/8/15.
 * 流式布局的RadioGroup
 */
public class FlowRadioGroup extends RadioGroup {
    private static final int DEFAULT_HORIZONTAL_SPACING = 5;
    private static final int DEFAULT_VERTICAL_SPACING = 5;
    private int mVerticalSpacing;
    private int mHorizontalSpacing;
    public FlowRadioGroup(Context context) {
        super(context);
    }

    public FlowRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        try {
            this.mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontal_spacing, DEFAULT_HORIZONTAL_SPACING);
            this.mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_vertical_spacing, DEFAULT_VERTICAL_SPACING);
        } finally {
            a.recycle();
        }
    }

    /**
     * 设置水平间隙
     * @param pixelSize
     */
    public void setHorizontalSpacing(int pixelSize) {
        this.mHorizontalSpacing = pixelSize;
    }

    /**
     * 设置行间隙
     * @param pixelSize
     */
    public void setVerticalSpacing(int pixelSize) {
        this.mVerticalSpacing = pixelSize;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int myWidth = resolveSize(0, widthMeasureSpec);
        int paddingLeft = this.getPaddingLeft();
        int paddingTop = this.getPaddingTop();
        int paddingRight = this.getPaddingRight();
        int paddingBottom = this.getPaddingBottom();
        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;
        int wantedHeight = 0;

        for(int childCount = this.getChildCount(); wantedHeight < childCount; ++wantedHeight) {
            View childView = this.getChildAt(wantedHeight);
            ViewGroup.LayoutParams childLayoutParams = childView.getLayoutParams();
            childView.measure(getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLayoutParams.width), getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLayoutParams.height));
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            lineHeight = Math.max(childHeight, lineHeight);
            if(childLeft + childWidth + paddingRight > myWidth) {
                childLeft = paddingLeft;
                childTop += this.mVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            } else {
                childLeft += childWidth + this.mHorizontalSpacing;
            }
        }

        wantedHeight = childTop + lineHeight + paddingBottom;
        this.setMeasuredDimension(myWidth, resolveSize(wantedHeight, heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int myWidth = r - l;
        int paddingLeft = this.getPaddingLeft();
        int paddingTop = this.getPaddingTop();
        int paddingRight = this.getPaddingRight();
        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;
        int i = 0;

        for(int childCount = this.getChildCount(); i < childCount; ++i) {
            View childView = this.getChildAt(i);
            if(childView.getVisibility() != View.GONE) {
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();
                lineHeight = Math.max(childHeight, lineHeight);
                if(childLeft + childWidth + paddingRight > myWidth) {
                    childLeft = paddingLeft;
                    childTop += this.mVerticalSpacing + lineHeight;
                    lineHeight = childHeight;
                }

                childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
                childLeft += childWidth + this.mHorizontalSpacing;
            }
        }

    }

}