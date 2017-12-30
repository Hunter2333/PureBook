package com.blanke.purebook_android.utils;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.TextView;

import com.blanke.purebook_android.R;

public class TextInputUtils {

    public static void setEditerrorStatus(TextInputLayout textInputLayout, TextView et, String reg, String errorInfo) {
        String text = et.getText().toString().trim();
        if (!text.matches(reg)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(errorInfo);
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        textInputLayout.postInvalidate();
    }

    public static boolean checkInputAnim(TextInputLayout textInputLayout) {
        String text = TextViewUtils.getText(textInputLayout.getEditText());
        if (TextUtils.isEmpty(text) || textInputLayout.isErrorEnabled()) {
            ViewAnimationHelper.start(textInputLayout, R.anim.shake);
            return false;
        }
        return true;
    }

}
