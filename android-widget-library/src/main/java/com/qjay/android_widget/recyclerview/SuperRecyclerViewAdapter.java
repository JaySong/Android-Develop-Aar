package com.qjay.android_widget.recyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * SuperRecyclerView专用适配器
 * Created by Q.Jay on 2015/9/1 0001.
 */
public abstract class SuperRecyclerViewAdapter<T> extends RecyclerView.Adapter<SuperViewHolder> implements View.OnClickListener {

    protected List<T> items;
    protected int layout;
    protected OnItemClickListener mOnItemClickListener;
    protected Context mContext;


    public SuperRecyclerViewAdapter(List<T> items, @LayoutRes int layout) {
        this(null, items, layout);
    }

    public SuperRecyclerViewAdapter(Context context, List<T> items, @LayoutRes int layout) {
        this.mContext = context;
        this.layout = layout;
        this.items = items == null ? new ArrayList<T>() : items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void setData(List<T> t) {
        items = t;
        notifyDataSetChanged();
    }

    public void addData(List<T> t) {
        items.addAll(t);
        notifyItemRangeChanged(t.size() - 1, t.size());
    }

    public List<T> getData() {
        return items;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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

    /**
     * 添加点击事件
     */
    public void addOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            int position = (int) v.getTag();
            mOnItemClickListener.onItemClick(this, v, position);
        }
    }


    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        onBindViewHolder(holder, items.get(position));
    }

    public abstract void onBindViewHolder(SuperViewHolder holder, T item);


    public interface OnItemClickListener {
        /**
         * Item 点击事件回调方法
         *
         * @param adapter  当前适配器
         * @param v        接受点击事件的View
         * @param position 点击的位置
         */
        void onItemClick(SuperRecyclerViewAdapter adapter, View v, int position);
    }
}
