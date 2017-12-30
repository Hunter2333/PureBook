package com.blanke.purebook_android.rx.subscribe;

import android.app.Activity;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.sns.SNS;
import com.avos.sns.SNSBase;
import com.avos.sns.SNSCallback;
import com.avos.sns.SNSException;
import com.avos.sns.SNSType;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.constants.Constants;

import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;

public class SNSOnSubscribe implements Observable.OnSubscribe<User> {
    private String appid, appsec, redirecturl;
    private SNSType type;
    private Activity activity;

    public SNSOnSubscribe(Activity activity, SNSType type, String appid, String appsec, String redirecturl) {
        this.activity = activity;
        this.type = type;
        this.appid = appid;
        this.appsec = appsec;
        this.redirecturl = redirecturl;
    }

    @Override
    public void call(Subscriber<? super User> subscriber) {
        try {
            SNS.setupPlatform(activity, type, appid, appsec, redirecturl);
        } catch (AVException e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
        SNS.loginWithCallback(activity, type, new SNSCallback() {
            int count = 0;

            @Override
            public void done(SNSBase snsBase, SNSException e) {//avos bug 这里会回调两次，源码惨不忍睹
                count++;
                JSONObject authorData = snsBase.authorizedData();
                if (e != null) {
                    subscriber.onError(e);
                } else {
                    if (authorData != null) {
                        SNS.loginWithAuthData(User.class, snsBase.userInfo(), new LogInCallback<User>() {
                            @Override
                            public void done(User user, AVException error) {
                                try {
                                    if (type == SNSType.AVOSCloudSNSQQ) {
                                        user.put(User.NICKNAME, authorData.getString("nickname"));
                                        user.put(User.ICONURL, authorData.getString("figureurl_qq_2"));
                                    } else {
                                        user.put(User.NICKNAME, authorData.getString("userName"));
                                        user.put(User.ICONURL, Constants.getSinaIconUrl(authorData.getString("uid")));
                                    }
                                    user.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(AVException e) {
                                            subscriber.onNext(user);
                                        }
                                    });
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                    subscriber.onError(e1);
                                }
                            }
                        });
                    } else {
                        if (count == 2) {
                            subscriber.onNext(User.getCurrentUser(User.class));
                        }
                    }
                }
            }
        });
    }
}
