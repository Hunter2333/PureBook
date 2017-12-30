package com.blanke.purebook_android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.avos.avoscloud.AVAnalytics;
import com.umeng.analytics.MobclickAgent;
import com.zhy.changeskin.SkinManager;


public abstract class BaseActivity extends AppCompatActivity {

    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
        MobclickAgent.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }
}
