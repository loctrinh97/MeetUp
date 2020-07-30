package com.example.meetup.view.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.meetup.R;
import com.example.meetup.viewmodel.UserViewModel;

public class InforSignupFragment extends Fragment {
    EditText edtName, edtEmail, edtPassword;
    Button btnSignUp;
    Boolean edtNameChange = false;
    Boolean edtEmailChange = false;
    Boolean edtPasswordChange = false;


    public InforSignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_infor, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edtNameChange = true;
                if(edtNameChange == true && edtEmailChange == true && edtPasswordChange == true){
                    btnSignUp.setBackgroundResource(R.drawable.ic_rectangle_btn);
                }
            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edtEmailChange = true;
                if(edtNameChange == true && edtEmailChange == true && edtPasswordChange == true){
                    btnSignUp.setBackgroundResource(R.drawable.ic_rectangle_btn);
                }
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edtPasswordChange = true;
                if(edtNameChange == true && edtEmailChange == true && edtPasswordChange == true){
                    btnSignUp.setBackgroundResource(R.drawable.ic_rectangle_btn);
                }
            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (UserViewModel.validateSignUp(name, email, password) == false) {
                    Toast.makeText(getActivity(), UserViewModel.messValidate, Toast.LENGTH_LONG).show();
                } else {
                    UserViewModel.createAccount(name, email, password);
                    Toast.makeText(getActivity(), UserViewModel.messCreateAccount, Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
