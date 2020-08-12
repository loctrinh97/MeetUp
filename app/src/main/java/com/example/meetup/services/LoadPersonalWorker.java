package com.example.meetup.services;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.repository.PersonalJoinedRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadPersonalWorker extends Worker {
    ApiUtils apiUtils = new ApiUtils();
    private EventJoinedServices mEventJoinedServices;
    public LoadPersonalWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        loadEventJoinedFromApi("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9tZWV0dXAucmlra2VpLm9yZ1wvYXBpXC92MFwvbG9naW4iLCJpYXQiOjE1OTcyMDIzODQsImV4cCI6MTU5NzIwOTU4NCwibmJmIjoxNTk3MjAyMzg0LCJqdGkiOiI4bFh5dndJbFp1NmdGaXlWIiwic3ViIjo0NDUsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.N1PMDXTkkYQAGL0CnNCUDpHr_QbfDpn6ohk4E66Mb4w",1);
        return Result.success();
    }

    private void loadEventJoinedFromApi(String token, final long status) {
    mEventJoinedServices = apiUtils.getEventJoinedServices();
    mEventJoinedServices.getListMyEventsJoined(token,status).enqueue(new Callback<EventResponse>() {
        @Override
        public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
            if (response.isSuccessful()){
                List<EventGetFromApi> listEvent = response.body().getResponse().getEvents();
                ArrayList listIdJoined = new ArrayList();
                for (EventGetFromApi e: listEvent){
                    listIdJoined.add(e.getId());
                }
                PersonalJoinedRepository personalJoinedRepository = PersonalJoinedRepository.getInstance();
                personalJoinedRepository.getEventStatus(listIdJoined);
                personalJoinedRepository.deleteUsersEvents();
                personalJoinedRepository.updateUsersEvents(listIdJoined,status);
            }
        }

        @Override
        public void onFailure(Call<EventResponse> call, Throwable t) {

        }
    });

    }
}
