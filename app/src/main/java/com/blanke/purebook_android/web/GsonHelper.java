package com.blanke.purebook_android.web;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * Created by chrischen on 2018/1/16.
 */

public class GsonHelper {
    public static Gson gson = new Gson();
    public static JsonParser jsonParser = new JsonParser();

    public static <T>T convertEntity(String jsonString,Class<T> entityClass){
        T entity =null;
        try{
            entity = gson.fromJson(jsonString.toString(),entityClass);
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }
}
