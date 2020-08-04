package com.example.meetup.view.registerlogin.resetpassword;

import android.content.Context;
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
    public static MyApplication myApplication;
    public static UserService mUserService;
    public static int messValidateForgotPw;
    public static Context context;
    public static MutableLiveData<String> messResetPassword = new MutableLiveData<>();
    public ForgotPasswordViewModel(Context context) {
        this.context = context;
    }

    public static void resetPassword(String emailForgot) {
        mUserService = ApiUtils.getUserService();
        mUserService.resetPassword(emailForgot).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS) {
                        messResetPassword.setValue("Mật khẩu của bạn đã được cấp lại, vui lòng kiểm tra email");
                    } else {
                        messResetPassword.setValue("Tài khoản chưa được đăng kí");
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    static boolean isEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static void checkEnableButtonForgotPw(EditText edtEmail, Button btnForgotPassword) {
        if (edtEmail.getText().length() == 0) {
            btnForgotPassword.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnForgotPassword.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public static boolean validateForgotPassword(String email) {
        if (!isEmail(email)) {
            messValidateForgotPw = R.string.please_enter_email;
            return false;
        }
        return true;
    }



}
