package com.blanke.purebook_android.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * 书籍标签Model
 */

@AVClassName("Tag")
public class Tag extends AVObject {
    public static final String NAME = "name";

    public String getName() {
        return getString(NAME);
    }
}
