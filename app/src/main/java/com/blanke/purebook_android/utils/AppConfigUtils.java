package com.blanke.purebook_android.utils;

import android.content.Context;

public class AppConfigUtils {

    public static int getScreenWidthDp(Context context) {
        return context.getResources().getConfiguration().screenWidthDp;
    }

    public static int getScreenHeighthDp(Context context) {
        return context.getResources().getConfiguration().screenHeightDp;
    }
}