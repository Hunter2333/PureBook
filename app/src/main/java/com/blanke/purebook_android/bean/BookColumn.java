package com.blanke.purebook_android.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

@AVClassName("BookColumn")
public class BookColumn extends AVObject {
    public static final String NAME = "name";
    public static final String BOOKS = "books";
    public static final String SUBS = "subs";
    public static final String TYPE = "type";
    public static final String ICON = "icon";

    public String getName() {
        return getString(NAME);
    }

    public AVFile getIcon() {
        return getAVFile(ICON);
    }

    public int getType() {
        return getInt(TYPE);
    }

    public AVRelation<Book> getBooks() {
        return getRelation(BOOKS);
    }

    public AVRelation<BookColumn> getSubs() {
        return getRelation(SUBS);
    }
}
