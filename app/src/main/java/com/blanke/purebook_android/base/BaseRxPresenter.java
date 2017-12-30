package com.blanke.purebook_android.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

public abstract class BaseRxPresenter<V extends MvpLceView, M> extends MvpBasePresenter<V> {

    public void  onSuccess(M data) {
        if (isViewAttached()) {
            getView().setData(data);
            getView().showContent();
        }
    }

    public abstract boolean getPullToRefresh();

    public void onFail(Throwable e) {
        if (isViewAttached()) {
            getView().showError(e, getPullToRefresh());
        }
    }
}
