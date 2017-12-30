package com.blanke.purebook_android.core.comment.view;

import com.blanke.purebook_android.bean.BookComment;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

/**
 * Created by Blanke on 16-3-22.
 */
public interface CommentView extends MvpLceView<List<BookComment>> {
    public void showMsg(String msg);

    public void sendSuccess();

    public void deleteFinish(Exception e);
}
