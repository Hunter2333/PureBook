package com.blanke.purebook_android.rx.subscribe;

import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBean;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by chrischen on 2018/1/15.
 */

public class UserRegisterOnSubscribe implements Observable.OnSubscribe<UserBean> {
    private UserBean user;
    public UserRegisterOnSubscribe(UserBean user) {
        this.user = user;
    }

    @Override
    public void call(Subscriber<? super UserBean> subscriber) {
        try {
            user.signUp();
            subscriber.onNext(user);
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }
}
