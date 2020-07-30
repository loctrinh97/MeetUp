package com.example.meetup.view.login;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.meetup.R;

public class LoginActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addFragmentLogin();
        addFragmentSignupInfor();
        Log.d("test", "activity: onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("test", "activity: onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void addFragmentLogin() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container1, new LoginFragment(), null);
        fragmentTransaction.commit();
    }

    private void addFragmentSignupInfor() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container2, new InforSignupFragment(), null);
        fragmentTransaction.commit();

    }
}