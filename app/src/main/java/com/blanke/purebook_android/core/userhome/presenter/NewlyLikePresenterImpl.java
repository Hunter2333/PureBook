package com.blanke.purebook_android.core.userhome.presenter;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.blanke.purebook_android.bean.BookBean;
import com.blanke.purebook_android.bean.BookReviewBean;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBookLike;
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
public class NewlyLikePresenterImpl extends UserNewlyPresenter {
    public NewlyLikePresenterImpl(UserNewlyView view) {
        super(view);
    }

    @Override
    public void loadData(int userId, int skip, int limit) {
        view.get().setLoading(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.REQUEST_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        //获取用户的所有喜欢的书
        Call<BaseResponse<List<BookBean>>> call = apiService.getUserLikeBooks(userId);
        call.enqueue(new Callback<BaseResponse<List<BookBean>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<BookBean>>> call, Response<BaseResponse<List<BookBean>>> response) {
                if(response.body().getCode().toString().equals("200")){
                    Log.i("!!",response.body().getData().toString());
                    //Gson gson = new Gson();
                    //List<BookBean> list = gson.fromJson(response.body().getData().toString(),new TypeToken<List<BookBean>>(){}.getType());
                    //view.get().setLikeData(list);
                    //view.get().setLoading(false);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<BookBean>>> call, Throwable t) {

            }
        });
    }
}
