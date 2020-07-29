package com.example.meetup;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getAppContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
