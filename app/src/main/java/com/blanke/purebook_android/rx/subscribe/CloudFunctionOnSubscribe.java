package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVCloud;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//TODO:
public class CloudFunctionOnSubscribe<T> extends BaseCloudOnSubscribe<T> {
    private HashMap<String, String> params;
    private String cloudFunctionName;

    public CloudFunctionOnSubscribe(String cloudFunctionName, HashMap<String, String> params) {
        this.cloudFunctionName = cloudFunctionName;
        this.params = params;
    }

    @Override
    protected T execute() throws Exception {
        if(cloudFunctionName==Constants.CLOUD_FUNCTION_RANDOM_BOOK){
            int userid = Constants.USER_ID;
            String url = Constants.REQUEST_HTTP_URL + "users/"+ userid +"/recommendation";
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                String temp = response.body().string();
                Gson gson = new Gson();
                JsonObject j = new JsonParser().parse(temp).getAsJsonObject();
                Book[] books = gson.fromJson(j.getAsJsonArray("data"), Book[].class);
                List<Book> bookList = Arrays.asList(books);
                return (T) bookList;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else if(cloudFunctionName==Constants.CLOUD_FUNCTION_SEARCH_BOOK) {
            String url = Constants.REQUEST_HTTP_URL + "books?namelike=" + params.get("key").toString();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                String temp = response.body().string();
                Gson gson = new Gson();
                JsonObject j = new JsonParser().parse(temp).getAsJsonObject();
                Book[] books = gson.fromJson(j.getAsJsonArray("data"), Book[].class);
                List<Book> bookList = Arrays.asList(books);
                return (T) bookList;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
        //return AVCloud.rpcFunction(cloudFunctionName,params);
    }
}
