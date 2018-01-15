package com.blanke.purebook_android.web;

/**
 * Created by chrischen on 2018/1/14.
 */

public class ApiUtils {
    private ApiUtils() {}

    public static ApiService getAPIService() {
        return RetrofitClient.getClient().create(ApiService.class);
    }
}
