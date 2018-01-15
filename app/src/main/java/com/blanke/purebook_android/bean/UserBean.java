package com.blanke.purebook_android.bean;

import java.sql.Timestamp;

/**
 * 用户类
 * @author chrischen
 */

public class UserBean {
    private String userName;
    private int userID;
    private String userKey;
    private String phone;
    private String portrait;
    private Timestamp created;
    private String desc;//用户签名

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPortrait() {
        return portrait;
    }
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void login(String name,String key){


    }



}
