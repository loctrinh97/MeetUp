package com.example.meetup.viewmodel;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
    public static String messValidate;
    public static MutableLiveData<String> messCreateAccount = new MutableLiveData<>();
    public static void createAccount(String name, String email, String password) {
        mUserService = ApiUtils.getUserService();
        mUserService.signUp(name, email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                  //  response.body().getResponse().getToken();
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS){
                        messCreateAccount.setValue("Create account successful !");
                    }else{
                        messCreateAccount.setValue("Account is exist !");
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                messCreateAccount.setValue("Error network !");
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

    public static boolean validateSignUp(String name, String email, String password) {
        if(isEmpty(name)){
            messValidate = "Please enter name";
            return false;
        }
        if (!isEmail(email)){
            messValidate =" Email is not valid";
            return false;
        }
        if (isPassword(password)){
            messValidate ="Password is not valid";
            return false;
        }
        return true;
    }

}
