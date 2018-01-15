package com.blanke.purebook_android.web;

import com.blanke.purebook_android.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class RetrofitClient {
    private static Retrofit retrofit;

    public static ApiService getService(){
        retrofit=new Retrofit.Builder()
                .baseUrl(Constants.REQUEST_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();//增加返回值为实体类的支持
        //创建service
        return retrofit.create(ApiService.class);
    }

}
