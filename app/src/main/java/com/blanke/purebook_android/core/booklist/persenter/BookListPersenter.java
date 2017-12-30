package com.blanke.purebook_android.core.booklist.persenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.core.booklist.view.BookListView;

import java.util.List;

public abstract class BookListPersenter extends BaseRxPresenter<BookListView, List<Book>> {

    abstract public void getBookData(BookColumn bookColumn, boolean pullToRefresh, int skip, int limit);
}
