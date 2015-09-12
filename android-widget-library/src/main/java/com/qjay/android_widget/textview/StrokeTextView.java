package com.qjay.android_widget.textview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.qjay.android_widget.R;

/**
 * @FileName:
 * @Description:
 * @Author :  Gupeng
 * @Date: 2015-08-31   11:07
 **/
public class StrokeTextView extends TextView {

    private int mStrokeColor;   //画笔颜色
    private int mStrokeWidth,mStrokeLeftWidth,mStrokeTopWidth,mStrokeRightWidth,mStrokeBottomWidth;  //边框粗细
    private float mRadius;  //边框粗细
    private boolean isDrawLeft, isDrawTop, isDrawRight, isDrawBottom;
    private boolean mIsStrokeDrawable, mIsStrokeDrawableLeft, mIsStrokeDrawableTop, mIsStrokeDrawableRight,mIsStrokeDrawableBottom;
    private Drawable[] compoundDrawables;
    private int drawablePadding;

    public StrokeTextView(Context context) {
        this(context,null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);initAttrs(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initAttrs(context,attrs);
//        init();
    }

    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        mStrokeColor = type.getInt(R.styleable.StrokeTextView_stroke_color, Color.BLACK);

        mStrokeWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_stroke_width, 0);

        if(mStrokeWidth == 0){
            mStrokeLeftWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeLeftWidth, 0);
            mStrokeTopWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeTopWidth, 0);
            mStrokeRightWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeRightWidth, 0);
            mStrokeBottomWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeBottomWidth, 0);
        }

//        mIsStrokeDrawable = type.getBoolean(R.styleable.StrokeTextView_isStrokeDrawable, true);
//        if(!mIsStrokeDrawable){
//            mIsStrokeDrawableLeft = type.getBoolean(R.styleable.StrokeTextView_isStrokeDrawableLeft, true);
//            mIsStrokeDrawableTop = type.getBoolean(R.styleable.StrokeTextView_isStrokeDrawableTop, true);
//            mIsStrokeDrawableRight = type.getBoolean(R.styleable.StrokeTextView_isStrokeDrawableRight, true);
//            mIsStrokeDrawableBottom = type.getBoolean(R.styleable.StrokeTextView_isStrokeDrawableBottom, true);
//        }

        mRadius = type.getDimension(R.styleable.StrokeTextView_corner_radius, 0);
        type.recycle();
    }

    private void init() {
        compoundDrawables = getCompoundDrawables();
        drawablePadding = getCompoundDrawablePadding();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStroke(canvas);
    }

    private void drawStroke(Canvas canvas){
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mStrokeColor);
        paint.setStyle(Paint.Style.STROKE);

        if(mStrokeWidth != 0){
            paint.setStrokeWidth(mStrokeWidth);
            RectF rectF = new RectF(0, 0, this.getWidth(), this.getHeight());
            if(mRadius != 0){
                canvas.drawRoundRect(rectF,mRadius,mRadius,paint);
            }else{
                canvas.drawRect(rectF,paint);
            }
            return;
        }

//        int startX = 0;
//        int endX = getWidth();
//        int startY = 0;
//        int endY = getHeight();
//        if(mIsStrokeDrawable) {
//            Drawable drawableLeft = compoundDrawables[0];
//            if(drawableLeft != null){
//                 startX = drawableLeft.getIntrinsicWidth()+drawablePadding;
//            }
//            Drawable drawableRight = compoundDrawables[2];
//            if(drawableRight != null){
//                 endX = drawableRight.getIntrinsicWidth()+drawablePadding;
//            }
//            Drawable drawableTop = compoundDrawables[1];
//            if(drawableTop!=null) {
//                 startY = drawableTop.getIntrinsicHeight() + drawablePadding;
//            }
//            Drawable drawableBottom = compoundDrawables[3];
//            if(drawableBottom!=null) {
//                 endY = drawableBottom.getIntrinsicHeight() + drawablePadding;
//            }
//        }else{
//            if(mIsStrokeDrawableLeft){
//                Drawable drawableLeft = compoundDrawables[0];
//                if(drawableLeft != null){
//                    startX = drawableLeft.getIntrinsicWidth()+drawablePadding;
//                }
//            }
//        }

        if(mStrokeLeftWidth != 0){
            paint.setStrokeWidth(mStrokeLeftWidth);
            canvas.drawLine(0, 0, 0, this.getHeight(), paint);
        }
        if(mStrokeTopWidth != 0){
            paint.setStrokeWidth(mStrokeTopWidth);
            canvas.drawLine(0, 0, this.getWidth(), 0, paint);
        }
        if(mStrokeRightWidth != 0){
            paint.setStrokeWidth(mStrokeRightWidth);
            canvas.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight(), paint);
        }
        if(mStrokeBottomWidth != 0){
            paint.setStrokeWidth(mStrokeBottomWidth);
            canvas.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight(), paint);
        }
    }
}

