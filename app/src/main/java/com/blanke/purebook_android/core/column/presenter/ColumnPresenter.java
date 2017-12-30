package com.blanke.purebook_android.core.column.presenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.core.column.view.ColumnView;

import java.util.List;

public abstract class ColumnPresenter extends BaseRxPresenter<ColumnView,List<BookColumn>> {

    abstract public void getColumnData(BookColumn parentBookColumn,boolean pullToRefresh);
}
