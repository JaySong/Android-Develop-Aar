package com.qjay.android_widget.textview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
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

    public StrokeTextView(Context context) {
        this(context,null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        mStrokeColor = type.getInt(R.styleable.StrokeTextView_strokeColor, Color.BLACK);

        mStrokeWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeWidth, 0);
        mStrokeLeftWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeLeftWidth, 0);
        mStrokeTopWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeTopWidth, 0);
        mStrokeRightWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeRightWidth, 0);
        mStrokeBottomWidth = type.getDimensionPixelSize(R.styleable.StrokeTextView_strokeBottomWidth, 0);

        mRadius = type.getDimension(R.styleable.StrokeTextView_radius, 0);
        type.recycle();
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

