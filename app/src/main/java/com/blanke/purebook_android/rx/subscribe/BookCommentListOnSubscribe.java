package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;

import java.util.List;

public class BookCommentListOnSubscribe extends BaseCloudOnSubscribe<List<BookComment>> {
    private Book book;
    private int limit, skip;

    public BookCommentListOnSubscribe(AVQuery.CachePolicy cachePolicy, Book book, int limit, int skip) {
        super(cachePolicy);
        this.book = book;
        this.limit = limit;
        this.skip = skip;
    }

    @Override
    protected List<BookComment> execute() throws Exception {
        return prepare(BookComment.getQuery(BookComment.class)
                .whereEqualTo(BookComment.BOOK, book)
                .limit(limit)
                .include(BookComment.USER)
//                .include(BookComment.REPLY)
                .include(BookComment.REPLY + "." + BookComment.USER+"."+ User.NICKNAME)
                .skip(skip)
                .order("-updatedAt"))
                .find();
    }
}
