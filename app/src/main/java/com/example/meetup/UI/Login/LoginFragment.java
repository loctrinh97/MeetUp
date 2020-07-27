package com.example.meetup.UI.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.meetup.R;

public class LoginFragment extends Fragment {
    Button btnSignUp;
    Button btnLogin;
    FragmentManager fragmentManager;
    ImageView imgSignUp;
    ImageView imgLogin;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        imgSignUp = view.findViewById(R.id.imgSignUp);
        imgLogin = view.findViewById(R.id.imgLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragmentSignupInfor();
                imgSignUp.setVisibility(View.VISIBLE);
                imgLogin.setVisibility(View.GONE);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragmentLoginInfor();
                imgSignUp.setVisibility(View.GONE);
                imgLogin.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }


    private void addFragmentSignupInfor() {
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container2, new InforSignupFragment(), null);
        fragmentTransaction.commit();

    }

    private void addFragmentLoginInfor() {
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container2, new InforLoginFragment(), "fragmentLoginInfor");
        fragmentTransaction.commit();

    }
    @Override
    public void onPause() {
        Log.d("fragmentB", "fragmentB: onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("fragmentB", "fragmentB: onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("fragmentB", "fragmentB: onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("fragmentB", "fragmentB: onDestroy");
        super.onDestroy();
    }
}
