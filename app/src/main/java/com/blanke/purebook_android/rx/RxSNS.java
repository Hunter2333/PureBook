package com.blanke.purebook_android.rx;

import android.app.Activity;

import com.avos.sns.SNSType;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.rx.subscribe.SNSOnSubscribe;
import com.blanke.purebook_android.utils.RxUtils;

import rx.Observable;

public class RxSNS {
    public static Observable<User> snsLogin(Activity activity, SNSType type, String appid, String appsec, String redirecturl) {
        return RxUtils.schedulerNewThread(
                Observable.create(
                        new SNSOnSubscribe(activity, type, appid, appsec, redirecturl)));
    }
}
