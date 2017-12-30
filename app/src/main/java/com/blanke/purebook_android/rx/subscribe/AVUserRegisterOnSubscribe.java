package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;

import rx.Observable;
import rx.Subscriber;

public class AVUserRegisterOnSubscribe implements Observable.OnSubscribe<AVUser> {
    private AVUser user;

    public AVUserRegisterOnSubscribe(AVUser user) {
        this.user = user;
    }

    @Override
    public void call(Subscriber<? super AVUser> subscriber) {
        try {
            user.signUp();
            subscriber.onNext(user);
        } catch (AVException e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }
}
