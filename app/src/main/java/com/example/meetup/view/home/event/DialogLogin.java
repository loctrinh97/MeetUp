package com.example.meetup.view.home.event;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.meetup.R;
import com.example.meetup.view.registerlogin.LoginActivity;

public class DialogLogin  {
    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_login);


        TextView text = dialog.findViewById(R.id.tvDialogTitle);
//        text.setText(R.string.you_must_login);

        TextView tvLogin = dialog.findViewById(R.id.tvDialogLogin);
//        tvLogin.setText(R.string.login_lowercase);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
