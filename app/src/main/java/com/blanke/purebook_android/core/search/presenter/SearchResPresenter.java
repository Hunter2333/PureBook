package com.blanke.purebook_android.core.search.presenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.core.search.view.SearchResView;

import java.util.List;

public abstract class SearchResPresenter extends BaseRxPresenter<SearchResView, List<Book>> {
    public abstract void getSearchRes(boolean pullToRefresh, int limit, String key);

}
