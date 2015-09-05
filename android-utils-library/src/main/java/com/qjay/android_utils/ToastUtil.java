package com.qjay.android_utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 吐司工具类
 * Created by Q.Jay on 2015/8/28 0028.
 */
public final class ToastUtil {

    private static Toast mToast;
    private static LinearLayout mToastView;
    private static ImageView mToastIco;

    private ToastUtil() {
        /** cannot be instantiated [不能被实例化]*/
        throw new UnsupportedOperationException("不支持实例化");
    }

    /**
     * 居中显示Toast
     *
     * @param context 上下文
     * @param textId  显示文本资源ID
     */
    public static void showCenter(Context context, int textId) {
        showCenter(context, context.getString(textId));
    }

    /**
     * 居中显示Toast
     *
     * @param context 上下文
     * @param text    显示文本
     */
    public static void showCenter(Context context, CharSequence text) {
        show(context, text, Gravity.CENTER);
    }

    /**
     * @param context 上下文
     * @param textId  显示文本资源ID
     * @param gravity 显示位置
     */
    public static void show(Context context, int textId, int gravity) {
        show(context, context.getString(textId), gravity);
    }

    public static void show(Context context, int textId) {
        show(context, context.getString(textId));

    }

    public static void show(Context context, CharSequence text) {
        show(context, text, 0);
    }
    public static void debugShow(Context context,CharSequence text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
    public static void show(Context context, CharSequence text, int gravity) {
        show(context, text, gravity, 0, 0);
    }

    public static void show(Context context, int textId, int gravity, int xOffset, int yOffset) {
        show(context, context.getString(textId), gravity, 0, 0);
    }
    public static void show(Context context, CharSequence text, int gravity, int xOffset, int yOffset) {
        instance(context);
        if(gravity == 0) {
            gravity = mToast.getGravity();
        }
        if(xOffset == 0) {
            xOffset = mToast.getXOffset();
        }
        if(yOffset == 0) {
            yOffset = mToast.getYOffset();
        }
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.setText(text);
        mToast.show();
    }

    /**
     * 居中显示Toast
     *
     * @param context 上下文
     * @param textId  显示文本资源ID
     * @param ico  图标
     */
    public static void showWithIcoCenter(Context context, int textId,int ico) {
        showWithIcoCenter(context, context.getString(textId), context.getResources().getDrawable(ico));
    }
    /**
     * 居中显示Toast
     *
     * @param context 上下文
     * @param text  显示文本
     * @param drawable  图标
     */
    public static void showWithIcoCenter(Context context, CharSequence text,Drawable drawable) {
        showWithIco(context, text, drawable, Gravity.CENTER);
    }

    /**
     * @param context 上下文
     * @param textId  显示文本资源ID
     * @param ico  图标
     */
    public static void showWithIco(Context context, int textId,int ico) {
        showWithIco(context, context.getString(textId), context.getResources().getDrawable(ico));

    }
    /**
     * @param context 上下文
     * @param text  显示文本
     * @param drawable  图标
     */
    public static void showWithIco(Context context, CharSequence text,Drawable drawable) {
        showWithIco(context, text,drawable, 0);
    }

    public static void showWithIco(Context context, CharSequence text,int ico, int gravity) {
        showWithIco(context, text, context.getResources().getDrawable(ico), gravity);
    }
    public static void showWithIco(Context context, CharSequence text,Drawable drawable, int gravity) {
        showWithIco(context, text, drawable, gravity, 0, 0);
    }

    public static void showWithIco(Context context, CharSequence text,int ico, int gravity, int xOffset, int yOffset) {
        showWithIco(context, text, context.getResources().getDrawable(ico), gravity, 0, 0);
    }
    public static void showWithIco(Context context, CharSequence text,Drawable drawable, int gravity, int xOffset, int yOffset) {
        instance(context);
        if(gravity == 0) {
            gravity = mToast.getGravity();
        }
        if(xOffset == 0) {
            xOffset = mToast.getXOffset();
        }
        if(yOffset == 0) {
            yOffset = mToast.getYOffset();
        }
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.setText(text);
        mToastIco.setImageDrawable(drawable);
        mToast.show();
    }


    @SuppressLint("ShowToast")
    private static Toast instance(Context context) {
        cancel();
        if(mToastView == null){
            mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
            mToastView = (LinearLayout) mToast.getView();
            mToastIco = new ImageView(context);

            mToastView.setGravity(Gravity.CENTER);

            mToastView.addView(mToastIco,0);
        }else {
            mToast = new Toast(context);
        }
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(mToastView);
        return mToast;
    }

    /**
     * 取消显示
     */
    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
