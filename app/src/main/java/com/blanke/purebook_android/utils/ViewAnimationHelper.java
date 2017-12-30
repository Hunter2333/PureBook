package com.blanke.purebook_android.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ViewAnimationHelper {
    public static void start(View v, int animResId) {
        if (v != null) {
            v.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(v.getContext(), animResId);
            v.startAnimation(shake);
        }
    }
}
