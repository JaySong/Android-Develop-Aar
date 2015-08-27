package com.qjay.android_utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by 仇杰 on 2015/4/27.
 * Description:常用单位转换的辅助类
 */
public final class DimenUtil {

    private DimenUtil() {
        /** cannot be instantiated [不能被实例化]*/
        throw new UnsupportedOperationException("不支持实例化");
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics()));
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal, context.getResources().getDisplayMetrics());
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal, context.getResources().getDisplayMetrics()));
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
