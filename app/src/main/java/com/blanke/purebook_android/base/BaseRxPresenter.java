package com.blanke.purebook_android.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;


/**
 * Presenter基类
 * 继承Mosby库的MvpBasePresenter,绑定View层
 * @param <V>Presenter绑定的view层
 * @param <M>数据
 * @author chrischen
 */
public abstract class BaseRxPresenter<V extends MvpLceView, M> extends MvpBasePresenter<V> {

    public void  onSuccess(M data) {
        if (isViewAttached()) {
            getView().setData(data);//获取绑定的view，设置数据
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
