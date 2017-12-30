package com.blanke.purebook_android.core.details.presenter;

import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.core.details.view.DetailsView;

public abstract class DetailsPresenter {
    protected DetailsView view;
    protected Book book;

    public DetailsPresenter(DetailsView view, Book book) {
        this.view = view;
        this.book = book;
    }

    public abstract void initLikeState();
    public abstract void setLike(boolean isLike);
}
