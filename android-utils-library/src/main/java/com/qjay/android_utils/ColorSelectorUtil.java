package com.qjay.android_utils;

import android.content.res.ColorStateList;

import java.util.ArrayList;
import java.util.List;

/**
 * 颜色选择器工具类
 * Created by Q.Jay on 2015/8/26 0026.
 */
public final class ColorSelectorUtil {
    private static final String TAG = "ColorSelectorUtil";

    private static ColorSelectorUtil instance = new ColorSelectorUtil();

    private static final List<Integer> colorArray = new ArrayList<>();
    private static final List<Integer> colorStateArray = new ArrayList<>();

    private ColorSelectorUtil() {

    }

    public static ColorSelectorUtil setSelect(int norColor, int selectColor) {
        colorArray.add(norColor);
        colorArray.add(selectColor);

        colorStateArray.add(-android.R.attr.state_selected,android.R.attr.state_selected);
        return instance;
    }

    public static ColorStateList build(){
        int[] colors = new int[colorArray.size()];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorArray.get(i);
        }
        int[][] states = new int[colorStateArray.size()][];
        for (int i = 0; i < states.length; i++) {
            states[i] = new int[]{colorStateArray.get(i)};
        }

        ColorStateList colorStateList = new ColorStateList(states,colors);
        return colorStateList;

    }
}
