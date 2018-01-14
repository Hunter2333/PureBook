package com.blanke.purebook_android.core.userhome.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.core.userhome.view.UserNewlyView;

import java.util.List;

/**
 *
 */

//TODO:
public class NewlyCommentPresenterImpl extends UserNewlyPresenter {
    public NewlyCommentPresenterImpl(UserNewlyView view) {
        super(view);
    }

    @Override
    public void loadData(String userId, int skip, int limit) {
        view.get().setLoading(true);
        try {
            BookComment.getQuery(BookComment.class)
                    .whereEqualTo(BookComment.USER, AVUser.createWithoutData(User.class, userId))
                    .orderByDescending("updatedAt")
                    .include(BookComment.BOOK)
                    .skip(skip)
                    .limit(limit)
                    .findInBackground(new FindCallback<BookComment>() {
                        @Override
                        public void done(List<BookComment> list, AVException e) {
                            if (view.get() != null) {
                                view.get().setCommentData(list);
                                view.get().setLoading(false);
                            }
                        }
                    });
        } catch (AVException e) {
            e.printStackTrace();
            view.get().setLoading(false);
        }
    }
}
