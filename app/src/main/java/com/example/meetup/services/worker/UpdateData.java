package com.example.meetup.services.worker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.meetup.R;
import com.example.meetup.model.response.ResultUpdateResponse;
import com.example.meetup.services.ApiUtils;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateData implements Runnable {
    int status;
    int eventId;
    String token;
    SharedPreferences updateSp = MyApplication.getAppContext().getSharedPreferences(Define.UPDATE_LIST_TOKEN, Context.MODE_PRIVATE);

    ApiUtils apiUtils = new ApiUtils();
    public UpdateData(String token,int eventId,int status){
        this.status = status;
        this.eventId = eventId;
        this.token = token;
    }
    @Override
    public void run() {
        Log.d("Update", "run: ");
        apiUtils.getService().doUpdateEvent(token,status,eventId).enqueue(new Callback<ResultUpdateResponse>() {
            @Override
            public void onResponse(Call<ResultUpdateResponse> call, Response<ResultUpdateResponse> response) {
                assert response.body() != null;
                Log.d("Update", "onResponse: "+ response.body().errorCode);
            }

            @Override
            public void onFailure(Call<ResultUpdateResponse> call, Throwable t) {
                String list = updateSp.getString(Define.LIST,"");
                list = list + "-" + eventId + "-" + status;
                updateSp.edit().putString("List",list).apply();

            }
        });
    }
}
