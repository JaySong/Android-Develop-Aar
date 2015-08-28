package com.qjay.android_utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 吐司工具类
 * Created by Q.Jay on 2015/8/28 0028.
 */
public final class ToastUtil {

    private static int DEFAULT_GRAVITY;

    private static Toast mToast;

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
        show(context, text, DEFAULT_GRAVITY);
    }

    public static void show(Context context, CharSequence text, int gravity) {
        show(context, text, gravity, 0, 0);
    }

    public static void show(Context context, CharSequence text, int gravity, int xOffset, int yOffset) {
        instance(context);
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.setText(text);
        mToast.show();
    }

    private static Toast instance(Context context) {
        cancel();
        mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
        DEFAULT_GRAVITY = mToast.getGravity();
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

//    private static View getView(Context context) {
//        instance(context);
//        return mToast.getView();
//    }
//
//    private Toast setText(int textId){
//        if(mToast == null) {
//            throw new NullPointerException("NPE : Toast 未实例化");
//        }
//        mToast.setText(textId);
//        return mToast;
//    }
//
//    private Toast setText(CharSequence text){
//        if(mToast == null) {
//            throw new NullPointerException("NPE : Toast 未实例化");
//        }
//        mToast.setText(text);
//        return mToast;
//    }

}
