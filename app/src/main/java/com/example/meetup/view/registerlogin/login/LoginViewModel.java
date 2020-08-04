package com.example.meetup.view.registerlogin.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.R;
import com.example.meetup.model.User;
import com.example.meetup.services.ApiUtils;
import com.example.meetup.services.UserService;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public static MyApplication myApplication;
    public static UserService mUserService;
    public static int messValidateLogin;
    public static Context context;
    public static MutableLiveData<String> messLogin = new MutableLiveData<>();
    public static SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences("tokenPref",Context.MODE_PRIVATE);
    public static SharedPreferences.Editor token = sharedPref.edit();

    public LoginViewModel(Context context) {
        this.context = context;
    }

    public static void accountLogin(String emailLogin, String passwordLogin) {
        mUserService = ApiUtils.getUserService();
        mUserService.login(emailLogin, passwordLogin).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS) {
                        // save token
                        token.putString("token",response.body().getResponse().getToken());
                        token.commit();
                        messLogin.setValue("Đăng nhập thành công");
                    } else {
                        messLogin.setValue("Sai tài khoản hoặc mật khẩu");
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                messLogin.setValue("Đã xảy ra lỗi");
            }
        });
    }

    public static String getPrefToken(){
        String token = sharedPref.getString("token",null);
        return token;
    }

    public static SharedPreferences.Editor clearPrefToken(){
        token.clear();
        token.apply();
        return token;
    }

    static boolean isEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    static boolean isPassword(String password) {
        if (password.length() < 6 || password.length() > 16) {
            return true;
        } else {
            return false;
        }
    }

    public static void checkEnableButtonLogin(EditText edtEmail, EditText edtPassword, Button btnLogin) {
        if ((edtEmail.getText().length() == 0) || (edtPassword.getText().length() == 0)) {
            btnLogin.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnLogin.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public static boolean validateLogin(String email, String password) {
        if (!isEmail(email)) {
            messValidateLogin = R.string.please_enter_email;
            return false;
        }
        if (isPassword(password)) {
            messValidateLogin = R.string.please_enter_password;
            return false;
        }
        return true;
    }


}
