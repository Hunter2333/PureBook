package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;

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
        return prepare(column.getBooks().getQuery(Book.class)
                .limit(limit)
                .skip(skip)
                .order("top,-updatedAt"))
                .find();
    }
}
