package com.blanke.purebook_android.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Rxjava工具类
 */
public class RxUtils {
    public static Observable schedulerNewThread(Observable observable) {
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());//subscriber回调发生在主线程
    }

}
