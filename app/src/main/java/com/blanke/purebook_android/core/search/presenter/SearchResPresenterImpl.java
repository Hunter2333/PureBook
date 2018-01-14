package com.blanke.purebook_android.core.search.presenter;

import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.rx.RxCloudFunction;

import java.util.HashMap;
import java.util.List;

/**
 * SearchResPresenter抽象类的实现
 * @author chrischen
 */
public class SearchResPresenterImpl extends SearchResPresenter {
    private boolean pullToRefresh;//用户是否手动刷新

    /**
     * @param pullToRefresh 用户是否手动刷新
     * @param limit 返回数量
     * @param key 搜索关键字
     */
    @Override
    public void getSearchRes(boolean pullToRefresh, int limit, String key) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);//调用view层显示loading状态
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);//封装参数
        //TODO:
        new RxCloudFunction<List<Book>>()//调用服务器端搜索接口,rxjava语法
                .executeCloud(Constants.CLOUD_FUNCTION_SEARCH_BOOK, map)
                .subscribe(this::onSuccess, this::onFail);
    }

    public boolean getPullToRefresh(){
        return pullToRefresh;
    }
}
