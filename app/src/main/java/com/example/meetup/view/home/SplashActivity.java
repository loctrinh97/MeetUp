package com.example.meetup.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.meetup.R;
import com.example.meetup.services.LoadInforWorker;
import com.example.meetup.services.LoadPersonalWorker;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.view.registerlogin.LoginActivity;


public class SplashActivity extends AppCompatActivity {
    private Handler delay = new Handler();
    OneTimeWorkRequest workRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //worker
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        OneTimeWorkRequest.Builder myBuilder = new OneTimeWorkRequest.Builder(LoadPersonalWorker.class);
        myBuilder.setConstraints(constraints);
        workRequest = myBuilder.build();
        WorkManager.getInstance(MyApplication.getAppContext()).enqueue(workRequest);
        // hide notification bar
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // delay & go to home screen
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {

                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 100);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        delay.removeCallbacksAndMessages(null);
    }
}