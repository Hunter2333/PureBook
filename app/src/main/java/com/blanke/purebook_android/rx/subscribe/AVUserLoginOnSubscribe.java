package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;

import rx.Observable;
import rx.Subscriber;

//TODO:
public class AVUserLoginOnSubscribe implements Observable.OnSubscribe<AVUser> {
    private String username;
    private String password;

    public AVUserLoginOnSubscribe(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void call(Subscriber<? super AVUser> subscriber) {
        try {
            subscriber.onNext(AVUser.logIn(username, password));
        } catch (AVException e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }
}
