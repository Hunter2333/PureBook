package com.blanke.purebook_android.core.details.presenter;

import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.GetCallback;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBean;
import com.blanke.purebook_android.bean.UserBookLike;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.details.view.DetailsView;
import com.blanke.purebook_android.core.login.LoginActivity;
import com.blanke.purebook_android.web.ApiService;
import com.blanke.purebook_android.web.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 书的详情Presenter实现类
 * 继承DetailsPresenter
 * @author chrischen
 */

public class DetailsPresenterImpl extends DetailsPresenter {
    private UserBookLike like;

    public DetailsPresenterImpl(DetailsView view, Book book) {
        super(view, book);
    }

    /**
     * 判断用户是否喜欢过该书籍
     */
    @Override
    public void initLikeState() {
        //TODO:
        if(){
            view.setLike(true);
        }
        //TODO:
//        if (user != null && !user.isAnonymous()) {
//            UserBookLike.getQuery(UserBookLike.class)
//                    .whereEqualTo(UserBookLike.USER, user)
//                    .whereEqualTo(UserBookLike.BOOK, book)
//                    .getFirstInBackground(new GetCallback<UserBookLike>() {
//                        @Override
//                        public void done(UserBookLike userBookLike, AVException e) {
//                            if (e == null && userBookLike != null) {
//                                like = userBookLike;
//                                view.setLike(true);
//                            }
//                        }
//                    });
//        }
    }


    @Override
    public void setLike(boolean isLike) {
        if(isLike){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.REQUEST_HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call call = apiService.likeBookById(Constants.USER_ID,book.getBookID());
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response){
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                }
            });
        }else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.REQUEST_HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call call = apiService.deleteLikeBookById(Constants.USER_ID,book.getBookID());
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response){
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                }
            });
        }
    }
}
