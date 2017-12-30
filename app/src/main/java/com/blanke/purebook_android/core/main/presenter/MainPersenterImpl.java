package com.blanke.purebook_android.core.main.presenter;

import com.blanke.purebook_android.rx.RxBookColumn;

public class MainPersenterImpl extends MainPersenter {
    private boolean pullToRefresh;

    @Override
    public void loadBookColumn(boolean pullToRefresh) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        RxBookColumn.getMainColumnData()
                .subscribe(this::onSuccess, this::onFail);
    }


    public boolean getPullToRefresh(){
        return pullToRefresh;
    }
}
