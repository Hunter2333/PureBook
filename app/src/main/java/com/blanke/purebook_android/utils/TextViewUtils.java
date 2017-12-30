package com.blanke.purebook_android.utils;

import android.widget.TextView;

public class TextViewUtils {
    public static String getText(TextView tv) {
        return tv.getText().toString().trim();
    }
}
