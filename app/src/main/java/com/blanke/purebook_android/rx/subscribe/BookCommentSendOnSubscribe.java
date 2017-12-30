package com.blanke.purebook_android.rx.subscribe;

import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;

import java.util.List;

public class BookCommentSendOnSubscribe extends BaseCloudOnSubscribe<List<BookComment>> {
    private Book book;
    private User user;
    private String content;
    private BookComment reply;

    public BookCommentSendOnSubscribe(Book book, BookComment reply, User user, String content) {
        this.book = book;
        this.user = user;
        this.content = content;
        this.reply = reply;
    }

    @Override
    protected List<BookComment> execute() throws Exception {
        BookComment bookComment = new BookComment();
        bookComment.setBook(book);
        bookComment.setContent(content);
        bookComment.setUser(user);
        bookComment.setReply(reply);
        bookComment.save();
        return null;
    }
}
