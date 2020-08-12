package com.example.meetup.view.registerlogin.login;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.meetup.R;
import com.example.meetup.view.home.HomeActivity;
import com.example.meetup.view.registerlogin.resetpassword.ForgotPasswordFragment;

import java.util.Objects;

public class InforLoginFragment extends Fragment implements View.OnClickListener {
    TextView tvForgotPassword, tvMessLogin, tvIgnoreLogin;
    FragmentManager fragmentManager;
    EditText edtEmailLogin, edtPasswordLogin;
    Button btnLoginConfirm;
    LoginViewModel loginViewModel;


    public InforLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
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
                loginViewModel.checkEnableButtonLogin(edtEmailLogin, edtPasswordLogin, btnLoginConfirm);
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
                loginViewModel.checkEnableButtonLogin(edtEmailLogin, edtPasswordLogin, btnLoginConfirm);
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

            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
        }

        if (v.equals(tvForgotPassword)) {
            // chuyen sang man hinh quen mat khau
            fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new ForgotPasswordFragment(), null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        if (v.equals(btnLoginConfirm)) {
            loginViewModel.messLogin.setValue("");
            String emailLogin = edtEmailLogin.getText().toString().trim();
            String passwordLogin = edtPasswordLogin.getText().toString().trim();
            if (!loginViewModel.validateLogin(emailLogin, passwordLogin)) {
                Toast.makeText(getActivity(), loginViewModel.messValidateLogin, Toast.LENGTH_SHORT).show();
            } else {
                loginViewModel.accountLogin(emailLogin, passwordLogin);
                //live data
                loginViewModel.messLogin.observe(Objects.requireNonNull(getActivity()), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        tvMessLogin.setText(loginViewModel.messLogin.getValue());
                        if (loginViewModel.messLogin.getValue().equals(getString(R.string.login_success))) {
                            tvMessLogin.setTextColor(getResources().getColor(R.color.colorPrimary));
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            tvMessLogin.setTextColor(getResources().getColor(R.color.color_alert));
                        }
                        tvMessLogin.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
    }


}
