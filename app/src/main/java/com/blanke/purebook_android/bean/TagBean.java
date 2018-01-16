package com.blanke.purebook_android.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chrischen on 2018/1/16.
 */

public class TagBean implements Serializable{
    @SerializedName("BookID")
    public Integer bookID;
    @SerializedName("Field")
    public String field;
    @SerializedName("Count")
    public Integer count;

    public Integer getBookID() {
        return bookID;
    }
    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
