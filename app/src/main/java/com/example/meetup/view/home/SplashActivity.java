package com.example.meetup.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.meetup.R;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.repository.CategoryRepository;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.services.worker.WorkController;
import com.example.meetup.ulti.Define;
import com.example.meetup.services.LoadInforWorker;
//import com.example.meetup.services.LoadPersonalWorker;
import com.example.meetup.ulti.MyApplication;


public class SplashActivity extends AppCompatActivity {
    private Handler delay = new Handler();
    WorkController workController = WorkController.getInstance();
    EventsRepository repository = EventsRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int event = repository.getCountEvent();
        if (event == 0) {
            workController.setWorkPeriodic();
        }

        Define.initMap();
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
        }, 1000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        delay.removeCallbacksAndMessages(null);
    }

}