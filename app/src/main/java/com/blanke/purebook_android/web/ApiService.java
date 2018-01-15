package com.blanke.purebook_android.web;

import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBean;

import org.androidannotations.annotations.rest.Post;


import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {

    //@GET("users/{id}")
    //Call<User> getUserById(@Path("id") String id);

    @GET("login")
    Observable<UserBean> login(@Query("id") int id, @Query("password") String key);


}

