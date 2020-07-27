package com.example.meetup.UI.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.meetup.R;

public class InforLoginFragment extends Fragment implements View.OnClickListener {
    TextView tvForgotPassword;
    FragmentManager fragmentManager;

    public InforLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_infor, container, false);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        Log.d("test","test");
        super.onStart();
    }

    @Override
    public void onClick(View v) {
//        Fragment fragment = getFragmentManager().findFragmentByTag("fragmentLoginInfor");
//        if(fragment != null){
//            getFragmentManager().beginTransaction().remove(fragment).commit();
//        }
                fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new ForgotPasswordFragment(), null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
    }
}
