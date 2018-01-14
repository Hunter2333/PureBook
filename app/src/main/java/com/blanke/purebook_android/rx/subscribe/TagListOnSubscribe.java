package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVQuery;
import com.blanke.purebook_android.bean.Tag;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;

import java.util.List;

//TODO:
public class TagListOnSubscribe extends BaseCloudOnSubscribe<List<Tag>> {
    private int limit, skip;

    public TagListOnSubscribe(AVQuery.CachePolicy cachePolicy, int limit, int skip) {
        super(cachePolicy);
        this.limit = limit;
        this.skip = skip;
    }

    @Override
    protected List<Tag> execute() throws Exception {
        return prepare(Tag.getQuery(Tag.class))
                .limit(limit)
                .skip(skip)
                .find();
    }
}
