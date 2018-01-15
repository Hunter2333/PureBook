package com.blanke.purebook_android.rx;
import com.blanke.purebook_android.bean.UserBean;
import com.blanke.purebook_android.rx.subscribe.AVUserLoginOnSubscribe;
import com.blanke.purebook_android.rx.subscribe.UserLoginOnSubscribe;
import com.blanke.purebook_android.utils.RxUtils;

import rx.Observable;

/**
 * Created by chrischen on 2018/1/15.
 */

public class RxUser {
    public Observable<UserBean> login(String name, String key) {
        return RxUtils.schedulerNewThread(Observable.create(new UserLoginOnSubscribe(name, key)));
    }
}
