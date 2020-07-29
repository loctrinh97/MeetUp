package com.example.meetup.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.meetup.R;
import com.example.meetup.UI.Home.HomeActivity;


public class SplashActivity extends AppCompatActivity {
private Handler delay = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        },2000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        delay.removeCallbacksAndMessages(null);
    }
}