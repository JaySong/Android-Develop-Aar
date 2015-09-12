package com.qjay.android_widget.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Q.Jay on 2015/9/1 0001.
 */
public abstract class SuperRecyclerViewAdapter<T> extends RecyclerView.Adapter<SuperViewHolder> implements View.OnClickListener {

    protected  List<T> items;
    protected  int layout;
    protected OnItemClickListener mOnItemClickListener;

    public SuperRecyclerViewAdapter(List<T> items, @LayoutRes int layout) {
        this.items = items;
        this.layout = layout;
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new SuperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        View itemView = holder.itemView;
        itemView.setTag(position);
        itemView.setOnClickListener(this);//实现条目点击事件

    }

    @Override
    public abstract void onBindViewHolder(SuperViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(SuperRecyclerViewAdapter adapter, View v, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            int position = (int) v.getTag();
            mOnItemClickListener.onItemClick(this, v, position);
        }
    }

    public void setData(List<T> t) {
        items = t;
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return items;
    }

}
