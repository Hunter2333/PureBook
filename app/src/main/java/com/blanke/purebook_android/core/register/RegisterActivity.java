package com.blanke.purebook_android.core.register;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.base.BaseActivity;
import com.blanke.purebook_android.bean.UserBean;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.login.LoginActivity;
import com.blanke.purebook_android.core.login.LoginActivity_;
import com.blanke.purebook_android.core.main.MainActivity_;
import com.blanke.purebook_android.web.ApiService;
import com.blanke.purebook_android.web.BaseResponse;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        String userName = userNameEditText.getText().toString();
        String password =  userPasswordEditText.getText().toString();
        String confirmPassword = userConfirmPasswordEditText.getText().toString();

        if(userName.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()){
            Toast.makeText(RegisterActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPassword)){
            Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
        }
        else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.REQUEST_HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<BaseResponse<UserBean>> call = apiService.register(userName,password);
            call.enqueue(new Callback<BaseResponse<UserBean>>() {
                @Override
                public void onResponse(Call<BaseResponse<UserBean>> call, Response<BaseResponse<UserBean>> response) {
                    //Toast.makeText(LoginActivity.this, response.body().getMsg().toString(), Toast.LENGTH_SHORT).show();
                    if(response.body().getMsg().toString().equals("成功")){
                        String id = response.body().getData().getUserID()+"";
                        Toast.makeText(RegisterActivity.this, "注册成功，id为"+id, Toast.LENGTH_SHORT).show();
                        jumpLogin();//注册成功跳转登录
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<UserBean>> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    /**
     * 跳转到login
     */
    public void jumpLogin(){
        LoginActivity_.intent(this).start();
        this.finish();
    }




}
