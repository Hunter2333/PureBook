package com.blanke.purebook_android.rx;

import com.blanke.purebook_android.rx.subscribe.CloudFunctionOnSubscribe;
import com.blanke.purebook_android.utils.RxUtils;

import java.util.HashMap;

import rx.Observable;

//TODO:
public class RxCloudFunction<T> {
    public Observable<T> executeCloud(String cloudFunctionName, HashMap<String, String> params) {
        return RxUtils.schedulerNewThread(
                Observable.create(new CloudFunctionOnSubscribe<T>(cloudFunctionName, params))
        );
    }
}
