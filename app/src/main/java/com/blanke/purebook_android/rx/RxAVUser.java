package com.blanke.purebook_android.rx;

import com.avos.avoscloud.AVUser;
import com.blanke.purebook_android.rx.subscribe.AVUserLoginOnSubscribe;
import com.blanke.purebook_android.utils.RxUtils;

import rx.Observable;

public class RxAVUser {
    public Observable<AVUser> login(String username, String password) {
        return RxUtils.schedulerNewThread(Observable.create(new AVUserLoginOnSubscribe(username, password)));
    }
}
