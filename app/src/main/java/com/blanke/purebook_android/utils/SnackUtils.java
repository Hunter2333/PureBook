package com.blanke.purebook_android.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackUtils {
    public static void show(View v, String msg, int du) {
        Snackbar.make(v, msg, du).show();
    }

    public static void show(View v, String msg) {
        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void show(View v, int msgRes) {
        Snackbar.make(v, ResUtils.getResString(v.getContext(), msgRes), Snackbar.LENGTH_SHORT).show();
    }
}
