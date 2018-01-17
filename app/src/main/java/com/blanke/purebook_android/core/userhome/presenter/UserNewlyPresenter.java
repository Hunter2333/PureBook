package com.blanke.purebook_android.core.userhome.presenter;

import com.blanke.purebook_android.core.userhome.view.UserNewlyView;

import java.lang.ref.WeakReference;

/**
 *用户页面Tab页的presenter
 * @author chrischen
 */

public abstract class UserNewlyPresenter {
    protected WeakReference<UserNewlyView> view;

    public UserNewlyPresenter(UserNewlyView view) {
        this.view = new WeakReference<UserNewlyView>(view);
    }

    abstract public void loadData(int userId, int skip, int limit);
}
