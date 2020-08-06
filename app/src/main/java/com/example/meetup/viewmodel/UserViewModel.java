package com.example.meetup.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.gson.JsonObject;


import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private static ApiUtils apiUtils = new ApiUtils();
    public static UserService mUserService;
    public static int idMessValidateSignUp;
    public static int messValidateLogin;
    public static int messValidateForgotPw;
    public static Context context;
    public static MutableLiveData<String> messCreateAccount = new MutableLiveData<>();
    public static MutableLiveData<String> messLogin = new MutableLiveData<>();
    public static MutableLiveData<String> messResetPassword = new MutableLiveData<>();

    public UserViewModel(Context context) {
        this.context = context;
    }


    public static void createAccount(String name, String email, String password) {
        mUserService = apiUtils.getUserService();
        mUserService.signUp(name, email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    //  response.body().getResponse().getToken();
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS) {
                        messCreateAccount.setValue(context.getString(R.string.create_account_success));
                    } else {
                        messCreateAccount.setValue(context.getString(R.string.account_exist));
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("UserViewModel",t.getMessage());
            }
        });
    }

    public static void accountLogin(String emailLogin, String passwordLogin) {
        mUserService = apiUtils.getUserService();
        mUserService.login(emailLogin, passwordLogin).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS) {
                        // save token
//                        SharedPreferences sharedPref = MyApplication.getAppContext()
//                                .getSharedPreferences("tokenPref",Context.MODE_PRIVATE);
//
//                        SharedPreferences.Editor token = sharedPref.edit();
//                        token.putString("token",response.body().getResponse().getToken());
//                        token.commit();

                        messLogin.setValue(context.getString(R.string.login_success));
                    } else {
                        messLogin.setValue(context.getString(R.string.error_login));
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                    messLogin.setValue(context.getString(R.string.error_network));
            }
        });
    }

    public static void resetPassword(String emailForgot) {
        mUserService = apiUtils.getUserService();
        mUserService.resetPassword(emailForgot).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS) {
                        messResetPassword.setValue(context.getString(R.string.reset_password));
                    } else {
                        messResetPassword.setValue(context.getString(R.string.email_not_found));
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    static boolean isEmpty(String name) {
        return TextUtils.isEmpty(name);
    }

    static boolean isEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    static boolean isPassword(@NotNull String password) {
        if (password.length() < 6 || password.length() > 16) {
            return true;
        } else {
            return false;
        }
    }

    public static void checkEnableButtonSignUp(EditText edtName, EditText edtEmail, EditText edtPassword, Button btnSignUp) {
        if ((edtName.getText().length() == 0) || (edtEmail.getText().length() == 0)
                || (edtPassword.getText().length() == 0)) {
            btnSignUp.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnSignUp.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public static void checkEnableButtonLogin(EditText edtEmail, EditText edtPassword, Button btnLogin) {
        if ((edtEmail.getText().length() == 0) || (edtPassword.getText().length() == 0)) {
            btnLogin.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnLogin.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public static void checkEnableButtonForgotPw(EditText edtEmail, Button btnForgotPassword) {
        if (edtEmail.getText().length() == 0) {
            btnForgotPassword.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnForgotPassword.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }


    public static boolean validateSignUp(String name, String email, String password) {
        if (isEmpty(name)) {
            idMessValidateSignUp = R.string.please_enter_your_name;
            return false;
        }
        if (!isEmail(email)) {
            idMessValidateSignUp = R.string.please_enter_email;
            return false;
        }
        if (isPassword(password)) {
            idMessValidateSignUp =R.string.please_enter_password;
            return false;
        }
        return true;
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

    public static boolean validateForgotPassword(String email) {
        if (!isEmail(email)) {
            messValidateForgotPw = R.string.please_enter_email;
            return false;
        }
        return true;
    }


}
