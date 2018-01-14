package com.blanke.purebook_android.core.taglist.presenter;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.rx.RxBookColumn;

/**
 * 标签Presenter的实现类
 * @author chrischen
 */
public class TagListPresenterImpl extends TagListPresenter {

    private boolean pullToRefresh;//用户是否下拉更新

    @Override
    public void getTagData(boolean pullToRefresh, int skip, int limit) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        //TODO:
        RxBookColumn.getTagListData(
                AVQuery.CachePolicy.CACHE_ELSE_NETWORK
                , limit, skip)
                .subscribe(this::onSuccess, this::onFail);
    }

    public boolean getPullToRefresh() {
        return pullToRefresh;
    }
}