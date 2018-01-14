package com.blanke.purebook_android.core.details.presenter;

import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.core.details.view.DetailsView;

/**
 * 书的详情Presenter抽象类
 * @author chrischen
 */

public abstract class DetailsPresenter {
    protected DetailsView view;//绑定view层
    protected Book book;

    /**
     * 构造函数
     * @param view 绑定的View
     * @param book 呈现的书籍
     */
    public DetailsPresenter(DetailsView view, Book book) {
        this.view = view;
        this.book = book;
    }

    public abstract void initLikeState();
    public abstract void setLike(boolean isLike);
}
