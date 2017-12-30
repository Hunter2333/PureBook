package com.blanke.purebook_android.core.userhome.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBookLike;
import com.blanke.purebook_android.core.userhome.view.UserNewlyView;

import java.util.List;

public class NewlyLikePresenterImpl extends UserNewlyPresenter {
    public NewlyLikePresenterImpl(UserNewlyView view) {
        super(view);
    }

    @Override
    public void loadData(String userId, int skip, int limit) {
        view.get().setLoading(true);
        try {
            UserBookLike.getQuery(UserBookLike.class)
                    .whereEqualTo(UserBookLike.USER, AVUser.createWithoutData(User.class, userId))
                    .orderByDescending("updatedAt")
                    .include(UserBookLike.BOOK)
                    .skip(skip)
                    .limit(limit)
                    .findInBackground(new FindCallback<UserBookLike>() {
                        @Override
                        public void done(List<UserBookLike> list, AVException e) {
                            if (view.get() != null) {
                                view.get().setLikeData(list);
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
