package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;

import java.util.List;


public class SubColumnOnSubscribe extends BaseCloudOnSubscribe<List<BookColumn>> {
    private BookColumn columnParent;

    public SubColumnOnSubscribe(BookColumn columnParent) {
        this.columnParent = columnParent;
    }

    public SubColumnOnSubscribe(AVQuery.CachePolicy cachePolicy, long maxCacheAge, long detaly, BookColumn columnParent) {
        super(cachePolicy, maxCacheAge, detaly);
        this.columnParent = columnParent;
    }

    @Override
    protected List<BookColumn> execute() throws Exception {
        return prepare(columnParent.getSubs().getQuery(BookColumn.class)
                .orderByAscending("order"))
                .find();
    }
}
