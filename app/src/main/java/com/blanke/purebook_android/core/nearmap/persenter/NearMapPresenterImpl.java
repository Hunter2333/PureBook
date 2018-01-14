package com.blanke.purebook_android.core.nearmap.persenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.blanke.purebook_android.bean.User;

import java.util.List;

/**
 * Created by blanke on 16-4-3.
 */
public class NearMapPresenterImpl extends NearMapPresenter {
    @Override
    public void getNearFriend(boolean pullToRefresh, AVGeoPoint location, int size) {
        if (location == null) {
            return;
        }
        getView().showLoading(pullToRefresh);
        AVQuery<User> query = AVUser.getQuery(User.class);
        query.whereNear(User.LOCATION, location);
        query.setLimit(size);
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> list, AVException e) {
                if (getView() != null) {
                    getView().setData(list);
                    getView().showContent();
                }
            }
        });
    }
}
