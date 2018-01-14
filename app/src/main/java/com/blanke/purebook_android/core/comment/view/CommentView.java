package com.blanke.purebook_android.core.comment.view;

import com.blanke.purebook_android.bean.BookComment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

/**
 * 书籍评论View层接口
 * 继承Mosby库的MvpLceView
 */

public interface CommentView extends MvpLceView<List<BookComment>> {

    public void sendSuccess();

    public void deleteFinish(Exception e);
}
