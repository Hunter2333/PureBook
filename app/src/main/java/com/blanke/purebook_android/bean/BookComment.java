package com.blanke.purebook_android.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("BookComment")
public class BookComment extends AVObject {
    public static final String CONTENT = "content";
    public static final String BOOK = "book";
    public static final String USER = "user";
    public static final String REPLY = "reply";

    public void setContent(String content) {
        put(CONTENT, content);
    }

    public void setBook(Book book) {
        put(BOOK, book);
    }

    public void setUser(User user) {
        put(USER, user);
    }

    public void setReply(BookComment comment) {
        put(REPLY, comment);
    }

    public String getContent() {
        return getString(CONTENT);
    }

    public User getUser() {
        try {
            User re = getAVObject(USER, User.class);
            return re;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book getBook() {
        try {
            return getAVObject(BOOK, Book.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BookComment getReply() {
        try {
            return getAVObject(REPLY, BookComment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
