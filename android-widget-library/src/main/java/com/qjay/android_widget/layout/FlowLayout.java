package com.qjay.android_widget.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.qjay.android_widget.R;


/**
 * Created by JaySeng on 2015/8/15.
 * 流式布局
 */
public class FlowLayout extends ViewGroup {
    private static final int DEFAULT_HORIZONTAL_SPACING = 5;
    private static final int DEFAULT_VERTICAL_SPACING = 5;
    private int mVerticalSpacing;
    private int mHorizontalSpacing;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
            LayoutParams childLayoutParams = childView.getLayoutParams();
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
