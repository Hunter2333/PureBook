package com.blanke.purebook_android.web;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * http response基类
 * @param <T>
 * @author chrischen
 */

public class BaseResponse<T> implements Serializable {
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String msg;
    @SerializedName("data")
    private T data;

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
