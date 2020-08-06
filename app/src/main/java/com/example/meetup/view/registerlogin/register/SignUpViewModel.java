package com.example.meetup.view.registerlogin.register;

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

public class SignUpViewModel extends ViewModel {
    ApiUtils apiUtils = new ApiUtils();
    public UserService mUserService;
    public int idMessValidateSignUp;
    public MutableLiveData<String> messCreateAccount = new MutableLiveData<>();

    public void createAccount(String name, String email, String password) {
        mUserService = apiUtils.getUserService();
        mUserService.signUp(name, email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == Define.STATUS_CODE_SUCCESS) {
                        messCreateAccount.setValue(MyApplication.getAppContext().getString(R.string.create_account_success));
                    } else {
                        messCreateAccount.setValue(MyApplication.getAppContext().getString(R.string.account_exist));
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                messCreateAccount.setValue(MyApplication.getAppContext().getString(R.string.error));
            }
        });
    }


    boolean isEmpty(String name) {
        return TextUtils.isEmpty(name);
    }

    boolean isEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isPassword(String password) {
        if (password.length() < 6 || password.length() > 16) {
            return true;
        } else {
            return false;
        }
    }

    public void checkEnableButtonSignUp(EditText edtName, EditText edtEmail, EditText edtPassword, Button btnSignUp) {
        if ((edtName.getText().length() == 0) || (edtEmail.getText().length() == 0)
                || (edtPassword.getText().length() == 0)) {
            btnSignUp.setBackgroundResource(R.drawable.ic_btn_disable);
        } else {
            btnSignUp.setBackgroundResource(R.drawable.ic_rectangle_btn);
        }
    }

    public boolean validateSignUp(String name, String email, String password) {
        if (isEmpty(name)) {
            idMessValidateSignUp = R.string.please_enter_your_name;
            return false;
        }
        if (!isEmail(email)) {
            idMessValidateSignUp = R.string.please_enter_email;
            return false;
        }
        if (isPassword(password)) {
            idMessValidateSignUp = R.string.please_enter_password;
            return false;
        }
        return true;
    }


}
