package com.blanke.purebook_android.base;

import android.app.Activity;
import android.os.Bundle;

import com.avos.avoscloud.AVAnalytics;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpFragment<V, P> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        AVAnalytics.onFragmentEnd(getClass().getSimpleName());
    }

    public void onResume() {
        super.onResume();
        AVAnalytics.onFragmentStart(getClass().getSimpleName());
    }
}
