package com.example.meetup.services.worker;

import android.annotation.SuppressLint;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import com.example.meetup.ulti.MyApplication;

import java.util.concurrent.TimeUnit;

public class WorkController {
    private static WorkController workController = null;
    public PeriodicWorkRequest myWork;
    OneTimeWorkRequest downDataWork;

    public static WorkController getInstance() {
        if (workController == null) {
            workController = new WorkController();
        }
        return workController;
    }

    // Load data hằng ngày, không cần đăng nhập
    public void setWorkPeriodic() {
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        PeriodicWorkRequest.Builder myWorkBuilder = new PeriodicWorkRequest.Builder(LoadInforWorker.class, 1, TimeUnit.DAYS)
                .setConstraints(constraints);
        myWork = myWorkBuilder.build();
        WorkManager.getInstance(MyApplication.getAppContext()).
                enqueue(myWork);
    }

    //load data mỗi khi đăng nhập
    @SuppressLint("EnqueueWork")
    public void setDownDataWork() {
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        OneTimeWorkRequest.Builder mBuider = new OneTimeWorkRequest.Builder(LoadPersonalWorker.class);
        mBuider.setConstraints(constraints);
        downDataWork = mBuider.build();
        WorkManager.getInstance(MyApplication.getAppContext()).enqueue(downDataWork);
    }

    public void cancelWork() {
        WorkManager.getInstance(MyApplication.getAppContext()).cancelAllWork();
    }

    public PeriodicWorkRequest getMyWork() {
        return myWork;
    }
}
