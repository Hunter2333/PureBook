package com.blanke.purebook_android.core.booklist.presenter;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.rx.RxBookColumn;

/**
 * 书的榜单Presenter实现类
 * @author chrischen
 */
public class BookListPresenterImpl extends BookListPresenter {

    private boolean pullToRefresh;//用户是否手动刷新

    /** 分页
     * @param bookColumn   栏目bean 包括新书榜,热门榜等
     * @param pullToRefresh 用户是否手动刷新
     * @param skip 忽略的个数,分页参数
     * @param limit 返回结果的个数,分页参数
     */
    @Override
    public void getBookData(BookColumn bookColumn, boolean pullToRefresh, int skip, int limit) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        //TODO:
        RxBookColumn.getBookListData(
                bookColumn, AVQuery.CachePolicy.NETWORK_ELSE_CACHE
                , limit, skip)
                .subscribe(this::onSuccess, this::onFail);
    }

    @Override
    public boolean getPullToRefresh() {
        return pullToRefresh;
    }
}
