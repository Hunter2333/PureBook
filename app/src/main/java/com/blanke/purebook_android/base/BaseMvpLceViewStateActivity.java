package com.blanke.purebook_android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.avos.avoscloud.AVAnalytics;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.umeng.analytics.MobclickAgent;
import com.zhy.changeskin.SkinManager;

/**
 *
 * 继承Mosby库的MvpLceViewStateActivity
 * @param <CV>
 * @param <M>数据
 * @param <V>View
 * @param <P>Presenter
 */

public abstract class BaseMvpLceViewStateActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends MvpLceViewStateActivity<CV, M, V, P> {
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
