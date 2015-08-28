package com.qjay.android_widget.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

import com.qjay.android_widget.R;


/**
 * 选择器ImageView
 * Created by JaySeng on 2015/8/5.
 */
public class SelectorImageView extends ImageView implements Checkable {
    private boolean isChecked;
    private Drawable mSelectorDrawable;
    private Drawable mDrawable;
    public SelectorImageView(Context context) {
        this(context, null);
    }
    public SelectorImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SelectorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDrawable = getDrawable();
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectorImageView);
        Drawable d = a.getDrawable(R.styleable.SelectorImageView_selector_src);
        mSelectorDrawable = d;
        isChecked = a.getBoolean(R.styleable.SelectorImageView_checked, false);
        setChecked(isChecked);
        if (d != null && isChecked) {
            setImageDrawable(d);
        }
        a.recycle();
    }
    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
    }
    @Override
    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
    @Override
    public boolean isChecked() {
        return isChecked;
    }
    @Override
    public void toggle() {
        if (isChecked()) {
            setImageDrawable(mSelectorDrawable);
        } else {
            setImageDrawable(mDrawable);
        }
    }

    /**对外公开的方法，调用此方法来切换图片
     * @param checked 是否选择
     */
    public void toggle(boolean checked){
        setChecked(checked);
        toggle();
    }
}
