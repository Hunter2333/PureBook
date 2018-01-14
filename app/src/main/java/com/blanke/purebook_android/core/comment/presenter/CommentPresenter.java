package com.blanke.purebook_android.core.comment.presenter;

import com.blanke.purebook_android.base.BaseRxPresenter;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.core.comment.view.CommentView;

import java.util.List;

public abstract class CommentPresenter extends BaseRxPresenter<CommentView, List<BookComment>> {

    abstract public void getBookCommentData(Book book, boolean pullToRefresh, int skip, int limit);

    abstract public void sendBookComment(Book book, BookComment reply, String content);

    abstract public void deleteComment(BookComment bookComment);
}
