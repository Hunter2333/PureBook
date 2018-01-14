package com.blanke.purebook_android.core.random.presenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.core.random.view.RandomView;

import java.util.List;

//TODO:
public abstract class RandomPresenter extends BaseRxPresenter<RandomView, List<Book>> {
    public abstract void getSearchRes(boolean pullToRefresh, int count);
}
