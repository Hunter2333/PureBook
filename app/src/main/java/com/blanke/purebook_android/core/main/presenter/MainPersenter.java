package com.blanke.purebook_android.core.main.presenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.core.main.view.MainView;

import java.util.List;

public abstract class MainPersenter extends BaseRxPresenter<MainView, List<BookColumn>> {
    public abstract void loadBookColumn(boolean pullToRefresh);
}
