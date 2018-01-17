package com.blanke.purebook_android.core.userhome.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.blanke.purebook_android.bean.BookBean;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.BookReviewBean;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.userhome.view.UserNewlyView;
import com.blanke.purebook_android.web.ApiService;
import com.blanke.purebook_android.web.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

//TODO:
public class NewlyCommentPresenterImpl extends UserNewlyPresenter {
    public NewlyCommentPresenterImpl(UserNewlyView view) {
        super(view);
    }

    @Override
    public void loadData(int userId, int skip, int limit) {
    //view.get().setLoading(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.REQUEST_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        //获取用户的所有书评
        Call<BaseResponse<List<BookReviewBean>>> call = apiService.getUserAllReviews(userId);
        call.enqueue(new Callback<BaseResponse<List<BookReviewBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<BookReviewBean>>> call, Response<BaseResponse<List<BookReviewBean>>> response) {
                if(response.body().getCode().toString().equals("200")){
                    Gson gson = new Gson();
                    List<BookReviewBean> list = gson.fromJson(response.body().getData().toString(),new TypeToken<List<BookReviewBean>>(){}.getType());
                    view.get().setCommentData(list);
                    view.get().setLoading(false);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<BookReviewBean>>> call, Throwable t) {

            }
        });


    }
}
