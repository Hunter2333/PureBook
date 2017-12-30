package com.blanke.purebook_android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.blanke.purebook_android.base.BaseColumnFragment;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.manager.ColumnManager;

import java.util.ArrayList;
import java.util.List;

public class ColumnFragmentAdapter extends FragmentPagerAdapter {
    List<BookColumn> bookColumns;
    SparseArray<BaseColumnFragment> fragments;

    public ColumnFragmentAdapter(FragmentManager fm) {
        super(fm);
        bookColumns = new ArrayList<>();
        fragments = new SparseArray<>();
    }

    public void clear() {
        bookColumns.clear();
        fragments.clear();
    }

    public void addTab(BookColumn bookColumn) {
        bookColumns.add(bookColumn);
    }

    @Override
    public Fragment getItem(int position) {//隐藏bug,ColumnFragmentAdapter#getItem()每次都会重新实例化fragment
        if (fragments.get(position) == null) {
            BaseColumnFragment f = ColumnManager.getColumnFragment(bookColumns.get(position));
            fragments.put(position, f);
        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return bookColumns != null ? bookColumns.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return bookColumns.get(position).getName();
    }


}
