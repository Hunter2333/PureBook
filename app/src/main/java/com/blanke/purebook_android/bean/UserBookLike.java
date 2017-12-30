package com.blanke.purebook_android.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("user_book_like")
public class UserBookLike extends AVObject {
    public static final String USER = "user";
    public static final String BOOK = "book";

    public void setUser(User user) {
        put(USER, user);
    }

    public void setBook(Book book) {
        put(BOOK, book);
    }

    public Book getBook() {
        try {
            return getAVObject(BOOK, Book.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
