package com.blanke.purebook_android.core.register;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blanke.purebook_android.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by chrischen on 2018/1/14.
 */

@EActivity(R.layout.activity_register)
public class RegisterActivity {

    @ViewById(R.id.activity_register_logo) ImageView logo;
    @ViewById(R.id.activity_register_user_name_text) EditText userNameEditText;
    @ViewById(R.id.activity_register_user_password_text) EditText userPasswordEditText;
    @ViewById(R.id.activity_register_confirm_user_password_text) EditText userConfirmPasswordEditText;
    @ViewById(R.id.activity_register_login_button) Button loginButton;
    @ViewById(R.id.activity_register_register_button) Button registerButton;

}
