package com.blanke.purebook_android.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.PushService;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.Tag;
import com.blanke.purebook_android.bean.UserBookLike;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.login.LoginActivity_;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.zhy.changeskin.SkinManager;

import cn.sharesdk.framework.ShareSDK;


public class SoleApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static SoleApplication getApplication(Context context) {
        return (SoleApplication) context.getApplicationContext();
    }

    public void init() {
        initUM();
        initAvos();
        initImageLoader();
        initSkin();
        ShareSDK.initSDK(this);
    }

    private void initUM() {

    }

    private void initAvos() {
        AVUser.alwaysUseSubUserClass(User.class);
        AVObject.registerSubclass(Book.class);
        AVObject.registerSubclass(Tag.class);
        AVObject.registerSubclass(BookComment.class);
        AVObject.registerSubclass(BookColumn.class);
        AVObject.registerSubclass(UserBookLike.class);
        AVOSCloud.setDebugLogEnabled(true);
        AVCloud.setProductionMode(true);
        AVAnalytics.setAppChannel("test");
        AVOSCloud.initialize(this, Constants.APPID_AVOS, Constants.APPKEY_AVOS);
        AVInstallation.getCurrentInstallation().saveInBackground();
        PushService.setDefaultPushCallback(this, LoginActivity_.class);
    }

    private void initSkin() {
        SkinManager.getInstance().init(this);
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 10 * 1000))
                .build();
        ImageLoader.getInstance().init(config);
    }
}
