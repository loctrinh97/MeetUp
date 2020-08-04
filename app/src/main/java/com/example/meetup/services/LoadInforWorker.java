package com.example.meetup.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.model.News;
import com.example.meetup.model.NewsResponse;
import com.example.meetup.repository.ListNewsRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;

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
                List<News> list = response.body().getResponse().getNews();
                ListNewsRepository.getInstance().clearList();
                ListNewsRepository.getInstance().insertNews(list);

            }

            @Override
            public void onFailure(@NotNull Call<NewsResponse> call, Throwable t) {
                Log.d("Test", "onFailure: ");
            }
        });
        return Result.success();
    }

    public void logResult(String result){
        Log.d("Test", "logResult: "+result);
    }
}
