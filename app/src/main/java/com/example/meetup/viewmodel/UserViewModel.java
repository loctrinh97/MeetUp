package com.example.meetup.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.ulti.MyApplication;
import com.example.meetup.R;
import com.example.meetup.model.User;
import com.example.meetup.services.ApiUtils;
import com.example.meetup.services.UserService;
import com.example.meetup.ulti.Define;


import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    public static UserService mUserService;
    public static String messValidateSignUp;
    public static String messValidateLogin;
    public static String messValidateForgotPw;
    public static MutableLiveData<String> messCreateAccount = new MutableLiveData<>();
    public static MutableLiveData<String> messLogin = new MutableLiveData<>();
    public static MutableLiveData<String> messResetPassword = new MutableLiveData<>();



    public static void createAccount(String name, String email, String password) {
        mUserService = ApiUtils.getUserService();
        mUserService.signUp(name, email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                  //  response.body().getResponse().getToken();
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS){
                        messCreateAccount.setValue(Define.CREATE_ACCOUNT_SUCCESS);
                    }else{
                        messCreateAccount.setValue(Define.ACCOUNT_EXIST);
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                messCreateAccount.setValue(Define.ERROR_NETWORK);
            }
        });
    }

    public static void accountLogin(String emailLogin, String passwordLogin) {
        mUserService = ApiUtils.getUserService();
        mUserService.login(emailLogin,passwordLogin).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS){
                        // save token
//                        SharedPreferences sharedPref = MyApplication.getAppContext()
//                                .getSharedPreferences("tokenPref",Context.MODE_PRIVATE);
//
//                        SharedPreferences.Editor token = sharedPref.edit();
//                        token.putString("token",response.body().getResponse().getToken());
//                        token.commit();

                        messLogin.setValue(Define.LOGIN_SUCCESS);
                    }else{
                        messLogin.setValue(Define.ERROR_LOGIN);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public static void resetPassword(String emailForgot) {
        mUserService = ApiUtils.getUserService();
        mUserService.resetPassword(emailForgot).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS){
                        messResetPassword.setValue(Define.RESET_PASSWORD);
                    } else {
                        messResetPassword.setValue(Define.EMAIL_NOT_FOUND);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    static boolean isEmpty(String name){
        return TextUtils.isEmpty(name);
    }

    static boolean isEmail(String email){
        return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    static boolean isPassword(@NotNull String password){
        if (password.length() <6 || password.length() >16){
            return true;
        } else {
            return false;
        }
    }

    public static void checkEnableButtonSignUp (EditText edtName, EditText edtEmail, EditText edtPassword, Button btnSignUp){
        if ((edtName.getText().length() == 0) || (edtEmail.getText().length() == 0)
                ||(edtPassword.getText().length() == 0)){
            btnSignUp.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnSignUp.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public static void checkEnableButtonLogin (EditText edtEmail, EditText edtPassword, Button btnLogin){
        if ((edtEmail.getText().length() == 0) ||(edtPassword.getText().length() == 0)){
            btnLogin.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnLogin.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public static void checkEnableButtonForgotPw (EditText edtEmail, Button btnForgotPassword){
        if (edtEmail.getText().length() == 0){
            btnForgotPassword.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnForgotPassword.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }


    public static boolean validateSignUp(String name, String email, String password) {
        if(isEmpty(name)){
            messValidateSignUp = Define.ENTER_NAME;
            return false;
        }
        if (!isEmail(email)){
            messValidateSignUp = Define.ENTER_EMAIL;
            return false;
        }
        if (isPassword(password)){
            messValidateSignUp =Define.ENTER_PASSWORD;
            return false;
        }
        return true;
    }

    public static boolean validateLogin(String email, String password){
        if (!isEmail(email)){
            messValidateLogin = Define.ENTER_EMAIL;
            return false;
        }
        if (isPassword(password)){
            messValidateLogin =Define.ENTER_PASSWORD;
            return false;
        }
        return true;
    }

    public static boolean validateForgotPassword (String email){
        if (!isEmail(email)){
            messValidateForgotPw = Define.ENTER_EMAIL;
            return false;
        }
        return true;
    }


}
