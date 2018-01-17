package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//TODO:
public class BookListOnSubscribe extends BaseCloudOnSubscribe<List<Book>> {
    private BookColumn column;
    private int limit, skip;

    public BookListOnSubscribe(AVQuery.CachePolicy cachePolicy, BookColumn column, int limit, int skip) {
        super(cachePolicy);
        this.column = column;
        this.limit = limit;
        this.skip = skip;
    }

    @Override
    protected List<Book> execute() throws Exception {
        String url = Constants.host_addr+"books/top250";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String temp = response.body().string();
            Gson gson = new Gson();
            JsonObject j = new JsonParser().parse(temp).getAsJsonObject();
            Book[] books = gson.fromJson(j.getAsJsonArray("data"), Book[].class);
            List<Book> bookList = Arrays.asList(books);
            return bookList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

        /*return prepare(column.getBooks().getQuery(Book.class)
                .limit(limit)
                .skip(skip)
                .order("top,-updatedAt"))
                .find();*/
    }
}
