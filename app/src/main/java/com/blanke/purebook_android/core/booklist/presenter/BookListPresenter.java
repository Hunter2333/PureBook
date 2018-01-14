package com.blanke.purebook_android.core.booklist.presenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.core.booklist.view.BookListView;

import java.util.List;

/**
 * 书的榜单Presenter抽象类
 * 继承BaseRxPresenter
 * @author chrischen
 */
public abstract class BookListPresenter extends BaseRxPresenter<BookListView, List<Book>> {

    abstract public void getBookData(BookColumn bookColumn, boolean pullToRefresh, int skip, int limit);
}
