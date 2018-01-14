package com.blanke.purebook_android.core.comment.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.DeleteCallback;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.rx.RxBookComment;

/**
 * 评论的presenter实现类
 * @author chrischen
 */
public class CommentPresenterImpl extends CommentPresenter {

    private boolean pullToRefresh;//用户是否下拉更新

    /**
     * 获取书籍评论
     * @param book
     * @param pullToRefresh 用户是否下拉更新
     * @param skip
     * @param limit
     */
    @Override
    public void getBookCommentData(Book book, boolean pullToRefresh, int skip, int limit) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        RxBookComment.getBookCommentListData(
                book, limit, skip).subscribe(this::onSuccess, this::onFail);
    }

    /**
     * 添加书籍评论
     * @param book
     * @param reply
     * @param content
     */

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

    /**
     * 删除书籍评论
     * @param bookComment
     */
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
