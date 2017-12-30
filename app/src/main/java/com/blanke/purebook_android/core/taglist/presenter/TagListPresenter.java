package com.blanke.purebook_android.core.taglist.presenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.Tag;
import com.blanke.purebook_android.core.taglist.view.TagListView;

import java.util.List;

public abstract class TagListPresenter extends BaseRxPresenter<TagListView, List<Tag>> {
    abstract public void getTagData(boolean pullToRefresh, int skip, int limit);
}
