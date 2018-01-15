package com.blanke.purebook_android.web;

import com.blanke.purebook_android.bean.User;

import org.androidannotations.annotations.rest.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") String id);

    @GET("login")
    Call<User> login(@Query("name") String name, @Query("key") String key);



}

