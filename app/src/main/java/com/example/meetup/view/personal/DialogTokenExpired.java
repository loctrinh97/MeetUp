package com.example.meetup.view.personal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.meetup.R;
import com.example.meetup.view.nearme.DialogGPS;
import com.example.meetup.view.registerlogin.LoginActivity;

public class DialogTokenExpired extends DialogFragment {
    public DialogTokenExpired(){

    }

    public static DialogTokenExpired newInstance(String title) {
        DialogTokenExpired frag = new DialogTokenExpired();
        Bundle args = new Bundle();
        args.putString("tokenExpired", title);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString(getString(R.string.key_token));
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(R.string.notification);
        alertDialogBuilder.setMessage(R.string.token_expired);
        alertDialogBuilder.setPositiveButton(R.string.ok,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        return alertDialogBuilder.create();
    }
}
