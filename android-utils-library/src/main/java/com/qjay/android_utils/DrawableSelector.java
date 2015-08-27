package com.qjay.android_utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择器工具类
 * Created by Q.Jay on 2015/8/27 0027.
 */
public class DrawableSelector {

    private static final String TAG = "DrawableSelector";

    private static final List<Drawable> drawableArray = new ArrayList<>();
    private static final List<Integer> drawableStateArray = new ArrayList<>();

    private DrawableSelector() {
    }

    public static DrawableSelector instance(){
        return new DrawableSelector();
    }

    public DrawableSelector setSelect(Drawable norDrawable, Drawable selectDrawable) {
        drawableArray.add(norDrawable);
        drawableArray.add(selectDrawable);
        drawableStateArray.add(-android.R.attr.state_selected);
        drawableStateArray.add(android.R.attr.state_selected);
        return this;
    }

    public StateListDrawable build(){
        StateListDrawable stateList = new StateListDrawable();
        int size = drawableStateArray.size();
        for (int i = 0; i < size; i++) {
            stateList.addState(new int[]{drawableStateArray.get(i)}, drawableArray.get(i));
        }
        return stateList;
    }
}
