package com.example.meetup.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.model.NewsResponse;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadInforWorker extends Worker {
    public LoadInforWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        NewsService newsService = ApiUtils.getNewsService();
        newsService.getListNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NotNull Call<NewsResponse> call, Response<NewsResponse> response) {
                assert response.body() != null;
                logResult(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<NewsResponse> call, Throwable t) {
                Log.d("Test", "onFailure: ");
            }
        });
        return Result.success();
    }

    public void logResult(NewsResponse newsResponse){
        Log.d("Test", "logResult: "+newsResponse.toString());
    }
}
