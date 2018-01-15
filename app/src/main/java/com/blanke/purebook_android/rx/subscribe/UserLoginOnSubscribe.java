package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.blanke.purebook_android.bean.UserBean;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by chrischen on 2018/1/15.
 */

public class UserLoginOnSubscribe implements Observable.OnSubscribe<UserBean> {
    private String name;
    private String key;

    public UserLoginOnSubscribe(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public void call(Subscriber<? super UserBean> subscriber) {
        /**try {
            subscriber.onNext(UserBean.login(name, key));
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }*/
    }
}
