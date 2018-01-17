package com.blanke.purebook_android.bean;

import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.web.ApiService;
import com.blanke.purebook_android.web.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BookReviewBean implements Serializable {
    @SerializedName("UserID")
    private int userId;
    @SerializedName("BookID")
    private int bookId;
    @SerializedName("Review")
    private String review;
    @SerializedName("Time")
    private long time;

    public int getUserId(){
        return this.userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getBookId(){
        return this.bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public String getReview(){
        return this.review;
    }

    public void setReview(String review){
        this.review = review;
    }

    public Long getTime(){
        return this.time;
    }

    public void setTime(long time){
        this.time = time;
    }



}
