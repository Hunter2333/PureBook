package com.blanke.purebook_android.core.taglist.presenter;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.rx.RxBookColumn;

public class TagListPresenterImpl extends TagListPresenter {

    private boolean pullToRefresh;

    @Override
    public void getTagData(boolean pullToRefresh, int skip, int limit) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        RxBookColumn.getTagListData(
                AVQuery.CachePolicy.CACHE_ELSE_NETWORK
                , limit, skip)
                .subscribe(this::onSuccess, this::onFail);
    }

    public boolean getPullToRefresh() {
        return pullToRefresh;
    }
}