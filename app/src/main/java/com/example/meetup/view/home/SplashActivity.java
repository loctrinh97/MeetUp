package com.example.meetup.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meetup.R;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.services.worker.WorkController;
import com.example.meetup.ulti.Define;



public class SplashActivity extends AppCompatActivity {
    private Handler delay = new Handler();
    WorkController workController = WorkController.getInstance();
    EventsRepository repository = EventsRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int event = repository.getCountEvent();

        Define.initMap();
        // hide notification bar
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // delay & go to home screen
        if (event == 0) {
            workController.setWorkPeriodic();
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
            }, 3000);
        }
        else {
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
            }, 500);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        delay.removeCallbacksAndMessages(null);
    }

}