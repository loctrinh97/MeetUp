package com.example.meetup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addFragmentLogin();
        addFragmentSignupInfor();
    }

    private void addFragmentLogin() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new LoginFragment(), null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addFragmentSignupInfor() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container2, new InforSignupFragment(), null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}