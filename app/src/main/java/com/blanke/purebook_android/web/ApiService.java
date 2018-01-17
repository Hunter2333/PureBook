package com.blanke.purebook_android.web;

import com.blanke.purebook_android.bean.BookBean;
import com.blanke.purebook_android.bean.BookReviewBean;
import com.blanke.purebook_android.bean.UserBean;
import com.squareup.okhttp.ResponseBody;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {

    @GET("users/{id}")
    Call<BaseResponse<UserBean>> getUserById(@Path("id") int id);

    @GET("login")
    Call<BaseResponse<UserBean>> login(@Query("id") int id, @Query("password") String key);

    @GET("tags")
    Call getTags();

    @POST("users")
    Call<BaseResponse<UserBean>> register(@Query("name")String name,@Query("key")String key);

    @GET("books/{id}")
    Call<BaseResponse<BookBean>> getBookById(@Path("id") int id);

    @GET("users/{id}/reviews")
    Call<BaseResponse<List<BookReviewBean>>> getUserAllReviews(@Path("id") int id);

    @GET("users/{id}/collection")
    Call<BaseResponse<List<BookBean>>> getUserLikeBooks(@Path("id") int id);




}

