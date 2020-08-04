package com.example.meetup.view.personal.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.meetup.R;
import com.example.meetup.view.registerlogin.LoginActivity;

public class PersonalLoginFragment extends Fragment implements View.OnClickListener {
    Button btnPersonalLogin;
    TextView tvSignUpNow;
    public static boolean personalLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_login,container,false);
        btnPersonalLogin = view.findViewById(R.id.btnPersonalLogin);
        tvSignUpNow = view.findViewById(R.id.tvSignUpNow);
        btnPersonalLogin.setOnClickListener(this);
        tvSignUpNow.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnPersonalLogin){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            personalLogin = true;
        }
        if (v == tvSignUpNow){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
