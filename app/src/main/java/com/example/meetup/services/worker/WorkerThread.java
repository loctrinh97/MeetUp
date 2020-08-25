package com.example.meetup.services.worker;

import android.os.Build;
import android.text.Html;

import androidx.annotation.RequiresApi;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.repository.VenueRepository;
import com.example.meetup.services.EventService;
import com.example.meetup.ulti.Define;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerThread implements Runnable {

    int status;
    public WorkerThread(int status){
        this.status = status;
    }
    @Override
    public void run() {
        BackgroundWork backgroundWork = new BackgroundWork();
        backgroundWork.run(status);
    }



}
