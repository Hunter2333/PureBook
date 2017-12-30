package com.blanke.purebook_android.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.avos.avoscloud.AVAnalytics;

public abstract class BaseFragment extends Fragment {
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
