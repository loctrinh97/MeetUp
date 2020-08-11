package com.example.meetup.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.model.response.CategoryResponse;
import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.model.response.NewResponse;
import com.example.meetup.repository.CategoryRepository;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.repository.ListNewsRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadInforWorker extends Worker {
    ApiUtils apiUtils = new ApiUtils();
    public LoadInforWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        loadNewsFromApi();
//        loadEventsFromApi();
//        loadCategories();
        return Result.success();
    }

    private void loadEventsFromApi() {
        EventService eventService = apiUtils.getEventService();
        eventService.getListPopularEvents().enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(@NotNull Call<EventResponse> call, @NotNull Response<EventResponse> response) {
                if (response.body() != null) {
                    List<EventGetFromApi> eventGetFromApis;
                    eventGetFromApis = response.body().getEvents();
                    List<Event> eventList = new ArrayList<>();
                    for (EventGetFromApi e : eventGetFromApis) {
                        Event event = new Event(e.getId(), e.getPhoto(), e.getName(), e.getLink(), e.getGoingCount(), e.getWentCount(), e.getDescriptionRaw(), e.getDescriptionHtml(), e.getSchedulePermanent(), e.getScheduleDateWarning(), e.getScheduleTimeAlert(), e.getScheduleStartDate(), e.getScheduleStartTime(), e.getScheduleEndDate(), e.getScheduleEndTime(), e.getScheduleOneDayEvent(), e.getScheduleExtra(), e.getVenue().getId());
                        eventList.add(event);
                    }
                    EventsRepository eventsRepository = EventsRepository.getInstance();
                    eventsRepository.deleteEvents();
                    eventsRepository.insertEvent(eventList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<EventResponse> call, @NotNull Throwable t) {

            }
        });
    }

    private void loadNewsFromApi(){
        NewsService newsService = apiUtils.getNewsService();
        newsService.getListNews().enqueue(new Callback<NewResponse>() {

            @Override
            public void onResponse(@NonNull Call<NewResponse> call, @NotNull Response<NewResponse> response) {
                if (response.body() != null) {
                    List<News> list = response.body().getResponse().getNews();
                    ListNewsRepository.getInstance().clearList();
                    ListNewsRepository.getInstance().insertNews(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewResponse> call, @NotNull Throwable t) {
                Log.d("LoadNewsFromApi", "onFailure: ");
            }
        });
    }

    private void loadCategories(){
        CategoryService categoryService = apiUtils.getCategoryService();
        categoryService.getListCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.body()!=null) {
                    List<Category> list = response.body().getCategories();
                    CategoryRepository categoryRepository = CategoryRepository.getInstance();
                    categoryRepository.deleteCaregories();
                    categoryRepository.insertCategories(list);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }
}
