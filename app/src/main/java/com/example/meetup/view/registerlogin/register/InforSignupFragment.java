package com.example.meetup.view.registerlogin.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.meetup.R;
import com.example.meetup.view.home.HomeActivity;
import com.example.meetup.view.registerlogin.login.LoginViewModel;

import java.util.Objects;


public class InforSignupFragment extends Fragment {
    EditText edtNameSignup, edtEmailSignup, edtPasswordSignup;
    TextView tvMessCreateAccount, tvIgnore;
    Button btnSignUpConfirm;
    SignUpViewModel signUpViewModel ;

    public InforSignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_infor, container, false);
        tvIgnore = view.findViewById(R.id.tvIgnore);
        edtNameSignup = view.findViewById(R.id.edtNameSignup);
        edtEmailSignup = view.findViewById(R.id.edtEmailSignup);
        edtPasswordSignup = view.findViewById(R.id.edtPasswordSignup);
        btnSignUpConfirm = view.findViewById(R.id.btnSignUpConfirm);
        tvMessCreateAccount = view.findViewById(R.id.tvMessCreateAccount);
        signUpViewModel = new ViewModelProvider(getActivity()).get(SignUpViewModel.class);
        // viewModel = new signUpViewModel(getActivity().getApplicationContext());

        // check unable button when change text
        edtNameSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                signUpViewModel.checkEnableButtonSignUp(edtNameSignup, edtEmailSignup, edtPasswordSignup, btnSignUpConfirm);
            }
        });

        edtEmailSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                signUpViewModel.checkEnableButtonSignUp(edtNameSignup, edtEmailSignup, edtPasswordSignup, btnSignUpConfirm);
            }
        });

        edtPasswordSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                signUpViewModel.checkEnableButtonSignUp(edtNameSignup, edtEmailSignup, edtPasswordSignup, btnSignUpConfirm);
            }
        });

        tvIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

        btnSignUpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameSignUp = edtNameSignup.getText().toString().trim();
                String emailSignUp = edtEmailSignup.getText().toString().trim();
                String passwordSignUp = edtPasswordSignup.getText().toString().trim();
                if (!signUpViewModel.validateSignUp(nameSignUp, emailSignUp, passwordSignUp)) {
                    Toast.makeText(getActivity(), getString(signUpViewModel.idMessValidateSignUp), Toast.LENGTH_SHORT).show();
                } else {
                    signUpViewModel.createAccount(nameSignUp, emailSignUp, passwordSignUp);
                    // live data
                    signUpViewModel.messCreateAccount.observe(Objects.requireNonNull(getActivity()), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            tvMessCreateAccount.setText(signUpViewModel.messCreateAccount.getValue());
                            if (signUpViewModel.messCreateAccount.getValue().equals(getString(R.string.create_account_success))) {
//                                Tạo tài khoản thành công
                                tvMessCreateAccount.setTextColor(getResources().getColor(R.color.colorPrimary));
                            } else {
                                tvMessCreateAccount.setTextColor(getResources().getColor(R.color.color_alert));
                            }
                            tvMessCreateAccount.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });
        return view;
    }
}
