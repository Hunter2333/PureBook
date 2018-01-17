package com.blanke.purebook_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.constants.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

//TODO:
public class RandomAdapter extends BaseAdapter {
    private Context context;
    private List<Book> books;

    public RandomAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    public RandomAdapter(Context context) {
        this.context = context;
        books = new ArrayList<>();
    }

    public void addBooks(List<Book> books) {
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        books.remove(position);
        notifyDataSetChanged();
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public int getCount() {
        return books == null ? 0 : books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_random_book, parent, false);
        }
        Integer lastPosition = (Integer) convertView.getTag();
        ImageView imageView = ViewHolderHelper.get(convertView, R.id.item_random_image);
        TextView textViewTitle = ViewHolderHelper.get(convertView, R.id.item_random_title);
        TextView textViewInfo = ViewHolderHelper.get(convertView, R.id.item_random_info);
        Book item = books.get(position);
        textViewTitle.setText(item.getBookName());
        textViewInfo.setText(item.getIntro());
        ImageLoader.getInstance().displayImage(item.getCover(), imageView, Constants.getImageOptions());
        return convertView;
    }
}
