package com.blanke.purebook_android.rx;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.bean.Tag;
import com.blanke.purebook_android.rx.subscribe.BookListOnSubscribe;
import com.blanke.purebook_android.rx.subscribe.MainColumnOnSubscribe;
import com.blanke.purebook_android.rx.subscribe.SubColumnOnSubscribe;
import com.blanke.purebook_android.rx.subscribe.TagListOnSubscribe;
import com.blanke.purebook_android.utils.RxUtils;

import java.util.List;

import rx.Observable;

//TODO:
public class RxBookColumn {
    public static Observable<List<BookColumn>> getMainColumnData() {
        return RxUtils.schedulerNewThread(
                Observable.create(new MainColumnOnSubscribe())
        );
    }

    public static Observable<List<BookColumn>> getSubColumnData(BookColumn columnParent) {
        return RxUtils.schedulerNewThread(
                Observable.create(new SubColumnOnSubscribe(columnParent))
        );
    }

    public static Observable<List<Book>> getBookListData(BookColumn column, AVQuery.CachePolicy cachePolicy, int limit, int skip) {
        return RxUtils.schedulerNewThread(
                Observable.create(new BookListOnSubscribe(cachePolicy, column, limit, skip))
        );
    }

    public static Observable<List<Tag>> getTagListData(AVQuery.CachePolicy cachePolicy, int limit, int skip) {
        return RxUtils.schedulerNewThread(
                Observable.create(new TagListOnSubscribe(cachePolicy, limit, skip))
        );
    }
}
