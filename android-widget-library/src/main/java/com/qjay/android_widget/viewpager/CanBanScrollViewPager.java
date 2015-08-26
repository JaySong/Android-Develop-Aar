package com.qjay.android_widget.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.qjay.android_widget.R;

/**
 * 可禁止滑动的ViewPager
 * Created by Q.Jay on 2015/8/26 0026.
 */
public class CanBanScrollViewPager extends ViewPager {
    private boolean isCanScroll;//是否可以滑动
    public CanBanScrollViewPager(Context context) {
        super(context);
    }
    public CanBanScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CanBanScrollViewPager);
        isCanScroll = a.getBoolean(R.styleable.CanBanScrollViewPager_isScroll, false);
        a.recycle();
    }
    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }
    public boolean isCanScroll() {
        return isCanScroll;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll()) {
            return super.onTouchEvent(ev);
        }
        return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll()) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }
}
