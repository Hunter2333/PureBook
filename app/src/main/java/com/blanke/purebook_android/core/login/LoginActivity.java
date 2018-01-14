package com.blanke.purebook_android.core.login;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.app.SoleApplication;
import com.blanke.purebook_android.base.BaseActivity;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.core.main.MainActivity_;
import com.blanke.purebook_android.core.register.RegisterActivity;
import com.blanke.purebook_android.core.register.RegisterActivity_;
import com.blanke.purebook_android.utils.AnimUtils;
import com.blanke.purebook_android.utils.ResUtils;
import com.blanke.purebook_android.utils.SnackUtils;
import com.blanke.purebook_android.utils.StatusBarCompat;
import com.socks.library.KLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * 登录Activity
 * 继承封装的BaseActivity基类
 */

//TODO:
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById(R.id.activity_login_logo)ImageView logo;
    @ViewById(R.id.activity_login_user_name_text) EditText userNameEditText;
    @ViewById(R.id.activity_login_user_password_text) EditText userPasswordEditText;
    @ViewById(R.id.activity_login_login_button) Button loginButton;
    @ViewById(R.id.activity_login_register_button) Button registerButton;

    private String type;
    private long lessTime = 3000, temp;

    @AfterViews
    public void init() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorAccent));
        SoleApplication.getApplication(this).init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Click(R.id.activity_login_login_button)
    public void login(){
        String userName = userNameEditText.getText().toString();
        String password =  userPasswordEditText.getText().toString();
        //TODO:是否匹配,如果匹配那么
        jumpMain();

    }

    /**
     * 点击跳转注册
     */
    @Click(R.id.activity_login_register_button)
    public void register(){
        jumpRegister();
    }

    private void onNext(User user) {
        jumpMain();
    }

    /**
     * 跳转首页
     */
    private void jumpMain() {
        MainActivity_.intent(this).start();
        this.finish();
    }


    /**
     * 跳转注册
     */
    private void jumpRegister(){
        RegisterActivity_.intent(this).start();
        this.finish();

    }



}
