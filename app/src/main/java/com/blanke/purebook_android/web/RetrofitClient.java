package com.blanke.purebook_android.web;

import com.blanke.purebook_android.bean.UserBean;
import com.blanke.purebook_android.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class RetrofitClient {
    private Retrofit mRetrofit;
    private ApiService mApiService;
    private static RetrofitClient mInstance;

    private  RetrofitClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit=new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constants.REQUEST_HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitClient getInstance(){
        if (mInstance == null){
            synchronized (RetrofitClient.class){
                mInstance = new RetrofitClient();
            }
        }
        return mInstance;
    }





}
