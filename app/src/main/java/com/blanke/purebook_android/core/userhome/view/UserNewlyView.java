package com.blanke.purebook_android.core.userhome.view;

import com.blanke.purebook_android.bean.BookBean;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.BookReviewBean;
import com.blanke.purebook_android.bean.UserBookLike;

import java.util.List;

/**
 *用户页面Tab页的view接口
 * @author chrischen
 */

//TODO:
public interface UserNewlyView {

    //设置用户喜欢的书的数据
    void setLikeData(List<BookBean> data);

    //设置用户评论的书的数据
    void setCommentData(List<BookReviewBean> data);

    void setLoading(boolean isLoading);
}