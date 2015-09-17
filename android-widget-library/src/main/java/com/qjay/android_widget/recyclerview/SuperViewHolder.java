package com.qjay.android_widget.recyclerview;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Q.Jay on 2015/9/1 0001.
 */
public class SuperViewHolder extends RecyclerView.ViewHolder{
    private final SparseArray<View> mViews;
    public SuperViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();

    }

    @SuppressWarnings("unchecked")
    public <V extends View> V getView(@IdRes int viewId){
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V)view;
    }

    public SuperViewHolder setText(@IdRes int viewId,String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public SuperViewHolder setImageResource(int viewId, int drawable) {
        ImageView view = getView(viewId);
        view.setImageResource(drawable);
        return this;
    }

    public SuperViewHolder setTextViewDrawableRight(int viewId, Drawable drawable) {
        TextView tv = getView(viewId);
        if(drawable == null){
            return this;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(null, null, drawable, null);
        return this;
    }
}
