package com.example.meetup.view.registerlogin.resetpassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.meetup.R;
import com.example.meetup.view.home.HomeActivity;
import com.example.meetup.view.registerlogin.LoginFragment;
import com.example.meetup.view.registerlogin.login.LoginViewModel;

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener {
    EditText edtEmailForgot;
    Button btnResetPassword;
    TextView tvMessForgot, tvIgnoreResetPw;
    ImageView imgBack;
    ForgotPasswordViewModel forgotPasswordViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        edtEmailForgot = view.findViewById(R.id.edtEmailForgot);
        tvMessForgot = view.findViewById(R.id.tvMessForgot);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        tvIgnoreResetPw = view.findViewById(R.id.tvIgnoreResetPw);
        imgBack = view.findViewById(R.id.imgBack);
        forgotPasswordViewModel = new ViewModelProvider(getActivity()).get(ForgotPasswordViewModel.class);
        imgBack.setOnClickListener(this);
        btnResetPassword.setOnClickListener(this);
        tvIgnoreResetPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        edtEmailForgot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                forgotPasswordViewModel.checkEnableButtonForgotPw(edtEmailForgot, btnResetPassword);
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.equals(imgBack)) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new LoginFragment(), null);
            fragmentTransaction.commit();
        }
        if (v.equals(btnResetPassword)) {
            String emailForgot = edtEmailForgot.getText().toString().trim();
            if (!forgotPasswordViewModel.validateForgotPassword(emailForgot)) {
                Toast.makeText(getActivity(), forgotPasswordViewModel.messValidateForgotPw, Toast.LENGTH_SHORT).show();
            } else {
                forgotPasswordViewModel.resetPassword(emailForgot);

                forgotPasswordViewModel.messResetPassword.observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        tvMessForgot.setText(forgotPasswordViewModel.messResetPassword.getValue());
                        if (forgotPasswordViewModel.messResetPassword.getValue().equals(R.string.login_success)) {
                            tvMessForgot.setTextColor(getResources().getColor(R.color.colorPrimary));
                        }
                        tvMessForgot.setVisibility(View.VISIBLE);
                    }
                });
            }
        }

    }
}
