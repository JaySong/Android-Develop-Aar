package com.qjay.android_utils;

import android.content.res.ColorStateList;

import java.util.ArrayList;
import java.util.List;

/**
 * 颜色选择器工具类
 * Created by Q.Jay on 2015/8/26 0026.
 */
public class ColorSelector {
    private static final String TAG = "ColorSelector";

    private final List<Integer> colorArray = new ArrayList<>();
    private final List<Integer> colorStateArray = new ArrayList<>();

    private ColorSelector() {
    }

    public static ColorSelector instance(){
        return new ColorSelector();
    }

    public ColorSelector setSelect(int norColor, int selectColor) {
        colorArray.add(norColor);
        colorArray.add(selectColor);

        colorStateArray.add(-android.R.attr.state_selected);
        colorStateArray.add(android.R.attr.state_selected);
        return this;
    }

    public ColorStateList build(){
        int[] colors = new int[colorArray.size()];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorArray.get(i);
        }
        int[][] states = new int[colorStateArray.size()][];
        for (int i = 0; i < states.length; i++) {
            states[i] = new int[]{colorStateArray.get(i)};
        }
        return new ColorStateList(states,colors);
    }
}
