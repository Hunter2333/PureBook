package com.blanke.purebook_android.core.column.presenter;

import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.rx.RxBookColumn;


public class ColumnPresenterImpl extends ColumnPresenter {
    private boolean pullToRefresh;

    @Override
    public void getColumnData(BookColumn parentBookColumn, boolean pullToRefresh) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        RxBookColumn.getSubColumnData(parentBookColumn)
                .subscribe(this::onSuccess, this::onFail);
    }

    public boolean getPullToRefresh() {
        return pullToRefresh;
    }
}
