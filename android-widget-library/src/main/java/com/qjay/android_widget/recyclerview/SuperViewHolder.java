package com.qjay.android_widget.recyclerview;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
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
}
