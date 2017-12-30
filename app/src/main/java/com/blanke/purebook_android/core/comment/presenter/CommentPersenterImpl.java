package com.blanke.purebook_android.core.comment.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.DeleteCallback;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.rx.RxBookComment;


public class CommentPersenterImpl extends CommentPersenter {

    private boolean pullToRefresh;

    @Override
    public void getBookCommentData(Book book, boolean pullToRefresh, int skip, int limit) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        RxBookComment.getBookCommentListData(
                book, limit, skip).subscribe(this::onSuccess, this::onFail);
    }

    @Override
    public void sendBookComment(Book book, BookComment reply, String content) {
        if (getView() == null) {
            return;
        }
        User user = User.getCurrentUser(User.class);
        if (user == null || user.isAnonymous()) {
            return;
        }
        RxBookComment.sendBookComment(book, reply, content, user)
                .subscribe(bookComments -> getView().sendSuccess()
                        , this::onFail);
    }

    @Override
    public void deleteComment(BookComment bookComment) {
        bookComment.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(AVException e) {
                if (getView() != null) {
                    getView().deleteFinish(e);
                }
            }
        });
    }

    @Override
    public boolean getPullToRefresh() {
        return pullToRefresh;
    }
}
