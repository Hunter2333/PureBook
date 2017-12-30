package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;

import java.util.List;

public class MainColumnOnSubscribe extends BaseCloudOnSubscribe<List<BookColumn>> {


    public MainColumnOnSubscribe(AVQuery.CachePolicy cachePolicy, long maxCacheAge, long detaly) {
        super(cachePolicy, maxCacheAge, detaly);
    }

    public MainColumnOnSubscribe() {
    }

    @Override
    protected List<BookColumn> execute() throws Exception {
        return prepare(BookColumn.getQuery(BookColumn.class)
                .whereLessThan("order", Constants.CLOUD_MAIN_COLUMN_MAX_ORDER)
                .orderByAscending("order"))
                .find();
    }
}
