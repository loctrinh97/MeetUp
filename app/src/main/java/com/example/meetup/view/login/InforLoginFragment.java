package com.example.meetup.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.meetup.R;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.home.HomeActivity;
import com.example.meetup.viewmodel.UserViewModel;

import java.util.Objects;

public class InforLoginFragment extends Fragment implements View.OnClickListener {
    TextView tvForgotPassword, tvMessLogin, tvIgnoreLogin;
    FragmentManager fragmentManager;
    EditText edtEmailLogin, edtPasswordLogin;
    Button btnLoginConfirm;


    public InforLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_infor, container, false);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        btnLoginConfirm = view.findViewById(R.id.btnLoginConfirm);
        edtPasswordLogin = view.findViewById(R.id.edtPasswordLogin);
        edtEmailLogin = view.findViewById(R.id.edtEmailLogin);
        tvMessLogin = view.findViewById(R.id.tvMessLogin);
        tvIgnoreLogin = view.findViewById(R.id.tvIgnoreLogin);
        btnLoginConfirm.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvIgnoreLogin.setOnClickListener(this);

        // check unable button when change text
        edtEmailLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                UserViewModel.checkEnableButtonLogin(edtEmailLogin, edtPasswordLogin, btnLoginConfirm);
            }
        });

        edtPasswordLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                UserViewModel.checkEnableButtonLogin(edtEmailLogin, edtPasswordLogin, btnLoginConfirm);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(tvIgnoreLogin)) {

         Intent intent = new Intent(getContext(),HomeActivity.class);
         startActivity(intent);
        }

        if (v.equals(tvForgotPassword) ) {
            // chuyen sang man hinh quen mat khau
            fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new ForgotPasswordFragment(), null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        if (v.equals(btnLoginConfirm) ) {
            String emailLogin = edtEmailLogin.getText().toString().trim();
            String passwordLogin = edtPasswordLogin.getText().toString().trim();
            if (!UserViewModel.validateLogin(emailLogin, passwordLogin)) {
                Toast.makeText(getActivity(), UserViewModel.messValidateLogin, Toast.LENGTH_SHORT).show();
            } else {
                UserViewModel.accountLogin(emailLogin, passwordLogin);

                UserViewModel.messLogin.observe(Objects.requireNonNull(getActivity()), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        tvMessLogin.setText(UserViewModel.messLogin.getValue());
                        if (UserViewModel.messLogin.getValue().equals(R.string.login_success)) {
                            tvMessLogin.setTextColor(getResources().getColor(R.color.colorPrimary));
                        }
                        tvMessLogin.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
