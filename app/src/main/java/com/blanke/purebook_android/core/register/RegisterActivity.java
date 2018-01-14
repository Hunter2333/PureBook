package com.blanke.purebook_android.core.register;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.base.BaseActivity;
import com.blanke.purebook_android.core.login.LoginActivity;
import com.blanke.purebook_android.core.login.LoginActivity_;
import com.blanke.purebook_android.core.main.MainActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by chrischen on 2018/1/14.
 */

@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    @ViewById(R.id.activity_register_logo) ImageView logo;
    @ViewById(R.id.activity_register_user_name_text) EditText userNameEditText;
    @ViewById(R.id.activity_register_user_password_text) EditText userPasswordEditText;
    @ViewById(R.id.activity_register_confirm_user_password_text) EditText userConfirmPasswordEditText;
    @ViewById(R.id.activity_register_login_button) Button loginButton;
    @ViewById(R.id.activity_register_register_button) Button registerButton;

    @Click(R.id.activity_register_login_button)
    public void login(){
        jumpLogin();
    }

    @Click(R.id.activity_register_register_button)
    public void register(){
        //TODO:
    }

    /**
     * 跳转到login
     */
    public void jumpLogin(){
        LoginActivity_.intent(this).start();
        this.finish();
    }




}
