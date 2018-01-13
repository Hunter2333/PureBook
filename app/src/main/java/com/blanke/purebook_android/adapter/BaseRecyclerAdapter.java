package com.blanke.purebook_android.adapter;

import android.content.Context;

import com.joanzapata.android.recyclerview.QuickAdapter;

import java.util.List;



public abstract class BaseRecyclerAdapter<T> extends QuickAdapter<T> {

    public BaseRecyclerAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public BaseRecyclerAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    public void addAll(List<T> data) {
        int temp = getItemCount();
        mItems.addAll(data);
        notifyItemRangeInserted(temp, data.size());
    }

    public List<T> getData() {
        return mItems;
    }

    @Override
    public void addItem(T item) {
        mItems.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }
}
