package com.blanke.purebook_android.web;

import com.blanke.purebook_android.bean.UserBean;
import com.squareup.okhttp.ResponseBody;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {

    @GET("users/{id}")
    Observable<UserBean> getUserById(@Path("id") String id);

    @GET("login")
    Call<BaseResponse<UserBean>> login(@Query("id") int id, @Query("password") String key);

    @GET("tags")
    Call getTags();

    @POST("users")
    Call<BaseResponse<UserBean>> register(@Query("name")String name,@Query("key")String key);




}

