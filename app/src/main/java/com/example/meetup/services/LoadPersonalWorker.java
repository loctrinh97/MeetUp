package com.example.meetup.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.repository.PersonalJoinedRepository;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.view.registerlogin.login.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadPersonalWorker extends Worker {

    public SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences("tokenPref", Context.MODE_PRIVATE);
    String token = sharedPref.getString("token",null);

    ApiUtils apiUtils = new ApiUtils();
    private EventJoinedServices mEventJoinedServices;
    public LoadPersonalWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        loadEventJoinedFromApi(token,Define.STATUS_WENT);
        loadEventCanJoinFromApi(token,Define.STATUS_GOING);
        return Result.success();
    }

    private void loadEventJoinedFromApi(String token, final long status) {
    mEventJoinedServices = apiUtils.getEventJoinedServices();
    mEventJoinedServices.getListMyEventsJoined(token,status).enqueue(new Callback<EventResponse>() {
        @Override
        public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
            if (response.isSuccessful()){
                if(response.body().getStatus() == 0 ){

                }else {
                    List<EventGetFromApi> listEvent = response.body().getResponse().getEvents();
                    List<Integer> listIdJoined = new ArrayList<>();
                    for (EventGetFromApi e: listEvent){
                        listIdJoined.add(e.getId());
                    }
                    PersonalJoinedRepository personalJoinedRepository = PersonalJoinedRepository.getInstance();
                    personalJoinedRepository.deleteUsersEvents();
                    personalJoinedRepository.updateUsersEvents(listIdJoined,status);
                    personalJoinedRepository.getListEventJoined(Define.PAGE_SIZE_DEFAULT,status);
                }
            }
        }

        @Override
        public void onFailure(Call<EventResponse> call, Throwable t) {

        }
    });

    }


    private void loadEventCanJoinFromApi(String token, final long status) {
        mEventJoinedServices = apiUtils.getEventJoinedServices();
        mEventJoinedServices.getListMyEventsJoined(token,status).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()){
                    if(response.body().getStatus() == 0 ){

                    }else {
                        List<EventGetFromApi> listEvent = response.body().getResponse().getEvents();
                        ArrayList listIdCanJoin = new ArrayList();
                        for (EventGetFromApi e : listEvent) {
                            listIdCanJoin.add(e.getId());
                        }
                        PersonalJoinedRepository personalJoinedRepository = PersonalJoinedRepository.getInstance();
                        personalJoinedRepository.deleteUsersEvents();
                        personalJoinedRepository.updateUsersEvents(listIdCanJoin, status);
                        personalJoinedRepository.getListEventJoined(Define.PAGE_SIZE_DEFAULT, status);
                    }
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {

            }
        });

    }
}
