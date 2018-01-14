package com.blanke.purebook_android.core.main.presenter;

import com.blanke.purebook_android.rx.RxBookColumn;

/**
 * MainPresenter抽象类的实现
 * @author chrischen
 */

public class MainPresenterImpl extends MainPresenter {
    private boolean pullToRefresh;//是否下拉更新

    /**
     * 加载书籍榜单
     * 实现MainPresenter中的方法
     * @param pullToRefresh
     */
    @Override
    public void loadBookColumn(boolean pullToRefresh) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);//showLoading为Mosby的MvpLceView中的方法
        RxBookColumn.getMainColumnData()
                .subscribe(this::onSuccess, this::onFail);
    }

    /**
     * 判断是否下拉更新
     * @return 下拉更新的boolean值
     */
    public boolean getPullToRefresh(){
        return pullToRefresh;
    }
}
