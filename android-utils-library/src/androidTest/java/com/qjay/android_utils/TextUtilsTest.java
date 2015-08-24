package com.qjay.android_utils;

import android.test.InstrumentationTestCase;
import android.text.TextUtils;
import android.util.Log;

/**
 * TextUtils - 直译过来意思就是：文本工具类，下面我们就一起看下这个类里面有些什么方法了？
 * Created by JaySeng on 2015/8/23.
 */
public class TextUtilsTest extends InstrumentationTestCase {

    private static final String TAG = TextUtilsTest.class.getSimpleName();

    /**
     * 判断文本是否为null或长度为0，返回boolean值，true表示为null或空字符串【长度为0】
     */
    public void testIsEmpty() {//官方说测试方法必须用public修饰，唉，我也是醉了
        boolean empty = TextUtils.isEmpty("");//经过测试返回true
        boolean empty1 = TextUtils.isEmpty(null);//经过测试返回true
        Log.i(TAG, "empty = " + empty);
        Log.i(TAG, "empty1 = " + empty1);

        /*其实此方法的判断方式很简单*/
        /** 这是官方写法，其实这里可以更精简
         *  public static boolean isEmpty(CharSequence str) {
                 if (str == null || str.length() == 0)
                    return true;
                 else
                    return false;
            }

         下面方法是精简写法
            public static boolean isEmpty(CharSequence str) {
                return str == null || str.length() == 0;
            }

         至于哪种方法在效率上更快，本人做过测试，基本一样，精简写法快点，但可以直接忽略
         三元运算符官方是说性能要比 if else 好一点
         */
    }

    /**
     * 判断文本中是否全是数字，是返回true,否返回false
     */
    public void testIsDigitsOnly() {
        boolean digitsOnly = TextUtils.isDigitsOnly("1243765423");
        boolean digitsOnly1 = TextUtils.isDigitsOnly("124fda3765423");

        Log.i(TAG, "digitsOnly = " + digitsOnly);//true
        Log.i(TAG, "digitsOnly1 = " + digitsOnly1);//false

    }

    /**
     * 判断文本或字符是否有可打印的字符，那可打印的字符是什么意思？
     * 看了源码实现方式，通俗的讲就是如果都可以打印出来，我们能很直观的发现是有字符显示在屏幕上，而一些特殊字符
     * 就算打印出来我们也是看不到的，这种就是不可打印字符
     */
    public void testIsGraphic() {
        boolean graphic = TextUtils.isGraphic(' ');
        boolean graphic1 = TextUtils.isGraphic("");
        boolean graphic2 = TextUtils.isGraphic(" ");
        boolean graphic3 = TextUtils.isGraphic(" ad");

        Log.i(TAG, "graphic = " + graphic);//false
        Log.i(TAG, "graphic1 = " + graphic1);//false
        Log.i(TAG, "graphic2 = " + graphic2);//false
        Log.i(TAG, "graphic3 = " + graphic3);//true
    }

}
