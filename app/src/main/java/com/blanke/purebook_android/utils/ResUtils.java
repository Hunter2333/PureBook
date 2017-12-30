package com.blanke.purebook_android.utils;

import android.content.Context;

public class ResUtils {
    public static String getResString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static int getResColor(Context context, int colorId) {
        return context.getResources().getColor(colorId);
    }
}
