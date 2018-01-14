package com.blanke.purebook_android.core.details.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.GetCallback;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBookLike;
import com.blanke.purebook_android.core.details.view.DetailsView;

/**
 * 书的详情Presenter实现类
 * 继承DetailsPresenter
 * @author chrischen
 */

public class DetailsPresenterImpl extends DetailsPresenter {
    private User user;
    private UserBookLike like;

    public DetailsPresenterImpl(DetailsView view, Book book) {
        super(view, book);
        user = User.getCurrentUser(User.class);
    }

    /**
     * 判断用户是否喜欢过该书籍
     */
    @Override
    public void initLikeState() {
        //TODO:
        if (user != null && !user.isAnonymous()) {
            UserBookLike.getQuery(UserBookLike.class)
                    .whereEqualTo(UserBookLike.USER, user)
                    .whereEqualTo(UserBookLike.BOOK, book)
                    .getFirstInBackground(new GetCallback<UserBookLike>() {
                        @Override
                        public void done(UserBookLike userBookLike, AVException e) {
                            if (e == null && userBookLike != null) {
                                like = userBookLike;
                                view.setLike(true);
                            }
                        }
                    });
        }
    }


    @Override
    public void setLike(boolean isLike) {
        if (user != null && !user.isAnonymous()) {
            if (isLike) {
                UserBookLike t = new UserBookLike();
                t.setBook(book);
                t.setUser(user);
                t.saveInBackground();
            } else {
                if (like != null) {
                    like.deleteInBackground();
                }
            }
        }
    }
}
