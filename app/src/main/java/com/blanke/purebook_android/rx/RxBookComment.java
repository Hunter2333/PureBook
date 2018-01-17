package com.blanke.purebook_android.rx;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.rx.subscribe.BookCommentListOnSubscribe;
import com.blanke.purebook_android.rx.subscribe.BookCommentSendOnSubscribe;
import com.blanke.purebook_android.utils.RxUtils;

import java.util.List;

import rx.Observable;

//TODO:
public class RxBookComment {

    public static Observable<List<BookComment>> getBookCommentListData(Book book, int limit, int skip) {
        return RxUtils.schedulerNewThread(
                Observable.create(new BookCommentListOnSubscribe(AVQuery.CachePolicy.NETWORK_ELSE_CACHE, book, limit, skip))
        );
    }

    public static Observable<List<BookComment>> sendBookComment(Book book, BookComment reply, String comment, User user) {
        return RxUtils.schedulerNewThread(
                Observable.create(new BookCommentSendOnSubscribe(book,reply, user, comment))
        );
    }
}
