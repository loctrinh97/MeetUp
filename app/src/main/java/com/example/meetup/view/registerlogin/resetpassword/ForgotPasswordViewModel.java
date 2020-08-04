package com.example.meetup.view.registerlogin.resetpassword;
import android.text.TextUtils;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordViewModel extends ViewModel {
    public  UserService mUserService;
    public  int messValidateForgotPw;
    public  MutableLiveData<String> messResetPassword = new MutableLiveData<>();

    public  void resetPassword(String emailForgot) {
        mUserService = ApiUtils.getUserService();
        mUserService.resetPassword(emailForgot).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS) {
                        messResetPassword.setValue(MyApplication.getAppContext().getString(R.string.reset_password));
                    } else {
                        messResetPassword.setValue(MyApplication.getAppContext().getString(R.string.email_not_found));
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                messResetPassword.setValue(MyApplication.getAppContext().getString(R.string.error_login));
            }
        });
    }

     boolean isEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public  void checkEnableButtonForgotPw(EditText edtEmail, Button btnForgotPassword) {
        if (edtEmail.getText().length() == 0) {
            btnForgotPassword.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnForgotPassword.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public  boolean validateForgotPassword(String email) {
        if (!isEmail(email)) {
            messValidateForgotPw = R.string.please_enter_email;
            return false;
        }
        return true;
    }



}
