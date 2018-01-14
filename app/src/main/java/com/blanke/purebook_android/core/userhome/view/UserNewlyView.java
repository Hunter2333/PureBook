package com.blanke.purebook_android.core.userhome.view;

import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.UserBookLike;

import java.util.List;

/**
 *
 */

//TODO:
public interface UserNewlyView {
    void setLikeData(List<UserBookLike> data);

    void setCommentData(List<BookComment> data);

    void setLoading(boolean isLoading);
}