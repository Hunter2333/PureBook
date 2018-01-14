package com.blanke.purebook_android.core.random.presenter;

import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.rx.RxCloudFunction;

import java.util.HashMap;
import java.util.List;

//TODO:
public class RandomPresenterImpl extends RandomPresenter {
    private boolean pullToRefresh;

    @Override
    public void getSearchRes(boolean pullToRefresh, int count) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        HashMap<String, String> map = new HashMap<>();
        map.put("count", count + "");
        new RxCloudFunction<List<Book>>()
                .executeCloud(Constants.CLOUD_FUNCTION_RANDOM_BOOK, map)
                .subscribe(this::onSuccess, this::onFail);
    }

    @Override
    public boolean getPullToRefresh() {
        return pullToRefresh;
    }
}
