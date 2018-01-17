package com.blanke.purebook_android.core.login;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.app.SoleApplication;
import com.blanke.purebook_android.base.BaseActivity;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBean;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.main.MainActivity;
import com.blanke.purebook_android.core.main.MainActivity_;
import com.blanke.purebook_android.core.register.RegisterActivity;
import com.blanke.purebook_android.core.register.RegisterActivity_;
import com.blanke.purebook_android.utils.AnimUtils;
import com.blanke.purebook_android.utils.ResUtils;
import com.blanke.purebook_android.utils.SnackUtils;
import com.blanke.purebook_android.utils.StatusBarCompat;
import com.blanke.purebook_android.web.ApiService;
import com.blanke.purebook_android.web.BaseResponse;
import com.blanke.purebook_android.web.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.ResponseBody;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.HashMap;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
        String userIdString = userNameEditText.getText().toString();
        String password =  userPasswordEditText.getText().toString();

        if(userNameEditText.getText().toString().isEmpty()||password.isEmpty()){
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }else{
            int userId = Integer.parseInt(userIdString);
            //TODO:是否匹配,如果匹配那么
            /**RetrofitClient.getInstance().login(new Subscriber<BaseResponse<UserBean>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(BaseResponse<UserBean> userBeanBaseResponse) {
                    Toast.makeText(LoginActivity.this, userBeanBaseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            },userId,password);**/
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.REQUEST_HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<BaseResponse<UserBean>> call = apiService.login(userId,password);
            call.enqueue(new Callback<BaseResponse<UserBean>>() {
                @Override
                public void onResponse(Call<BaseResponse<UserBean>> call, Response<BaseResponse<UserBean>> response) {
                    if(response.body().getMsg().toString().equals("成功！")){
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        jumpMain(response);//跳转首页
                    }else if(response.body().getMsg().toString().equals("没有登录")){
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<UserBean>> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    /**
     * 点击跳转注册
     */
    @Click(R.id.activity_login_register_button)
    public void register(){
        jumpRegister();
    }

    /**
     * 跳转首页
     * 传递user object
     */
    private void jumpMain(Response<BaseResponse<UserBean>> response) {
        UserBean user=new UserBean();
        user.setUserID(response.body().getData().getUserID());
        user.setUserName(response.body().getData().getUserName().toString());
        user.setUserKey(response.body().getData().getUserKey().toString());
        user.setCreated(response.body().getData().getCreated());
        user.setDesc(response.body().getData().getDesc().toString());
        user.setPhone(response.body().getData().getPhone());
        user.setPortrait(response.body().getData().getPortrait().toString());

        Intent intent = MainActivity_.intent(this).get();
        intent.putExtra("UserObject",user);
        startActivity(intent);
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
