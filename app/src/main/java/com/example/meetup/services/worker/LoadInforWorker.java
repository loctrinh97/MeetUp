package com.example.meetup.services.worker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.R;
import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.model.response.CategoryResponse;
import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.model.response.EventVenue;
import com.example.meetup.model.response.NewResponse;
import com.example.meetup.repository.CategoryRepository;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.repository.ListNewsRepository;
import com.example.meetup.repository.VenueRepository;
import com.example.meetup.services.ApiUtils;
import com.example.meetup.services.BaseService;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadInforWorker extends Worker {
    ApiUtils apiUtils = new ApiUtils();
    BaseService service = apiUtils.getService();
    CategoryRepository categoryRepository = CategoryRepository.getInstance();
    EventsRepository eventsRepository = EventsRepository.getInstance();

    public SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences(Define.PRE_TOKEN, Context.MODE_PRIVATE);
    String token = sharedPref.getString(Define.TOKEN, "");
    public SharedPreferences.Editor sharedPrefEventNearId = sharedPref.edit();

    public LoadInforWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        loadNewsFromApiFirst();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                loadEventsFromApi();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        loadCategories();

        ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

        Runnable run = new Runnable() {
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                loadEventsCategories();
            }
        };
        worker.schedule(run, 2, TimeUnit.SECONDS);
        loadNewsFromApi();

        return Result.success();
    }

    private void loadEventsFromApi() {
        service.getListPopularEvents().enqueue(new Callback<EventResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Call<EventResponse> call, @NotNull Response<EventResponse> response) {
                if (response.body() != null) {
                    EventsRepository eventsRepository = EventsRepository.getInstance();
                    VenueRepository venueRepository = VenueRepository.getInstance();
                    List<EventGetFromApi> eventGetFromApis;
                    eventGetFromApis = response.body().getResponse().getEvents();
                    List<Event> eventList = new ArrayList<>();
                    for (EventGetFromApi e : eventGetFromApis) {
                        String desRaw = null;
                        if (e.getDescriptionHtml() != null) {
                            if (((int) Build.VERSION.SDK_INT) >= 24) {
                                desRaw = String.valueOf(Html.fromHtml(e.getDescriptionHtml(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                desRaw = String.valueOf(Html.fromHtml(e.getDescriptionHtml()));
                            }
                        }
                        Event event = new Event(e.getId(), e.getPhoto(), e.getName(), e.getLink(), Define.STATUS_DEFAULT, e.getGoingCount(), e.getWentCount(), desRaw, e.getDescriptionHtml(), e.getSchedulePermanent(), e.getScheduleDateWarning(), e.getScheduleTimeAlert(), e.getScheduleStartDate(), e.getScheduleStartTime(), e.getScheduleEndDate(), e.getScheduleEndTime(), e.getScheduleOneDayEvent(), e.getScheduleExtra(), e.getVenue().getId(), e.getDistance());
                        Venue venue = e.getVenue();

                        venueRepository.insertVenue(venue);
                        eventList.add(event);
                    }
                    eventsRepository.deleteEvents();
                    eventsRepository.insertEvent(eventList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<EventResponse> call, @NotNull Throwable t) {
                ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

                Runnable run = new Runnable() {
                    public void run() {
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                        loadEventsFromApi();
                    }
                };
                worker.schedule(run, 2, TimeUnit.SECONDS);
            }
        });

    }

    private void loadNewsFromApi() {

        service.getListNews().enqueue(new Callback<NewResponse>() {

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
                ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

                Runnable run = new Runnable() {
                    public void run() {
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                        loadNewsFromApi();
                    }
                };
                worker.schedule(run, 2, TimeUnit.SECONDS);
            }
        });
    }

    private void loadNewsFromApiFirst() {

        service.getListNewsFirst(1, 100).enqueue(new Callback<NewResponse>() {

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
                ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

                Runnable run = new Runnable() {
                    public void run() {
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                        loadNewsFromApi();
                    }
                };
                worker.schedule(run, 2, TimeUnit.SECONDS);
            }
        });
    }

    private void loadEventsCategories() {

        List<Category> categories = categoryRepository.getCategories();
        for (final Category category : categories) {
            service.getListEventsByCategory(category.getId()).enqueue(new Callback<EventResponse>() {
                @Override
                public void onResponse(@NotNull Call<EventResponse> call, @NotNull Response<EventResponse> response) {
                    if (response.body() != null) {
                        List<EventGetFromApi> list = response.body().getResponse().getEvents();
                        for (EventGetFromApi e : list) {
                            eventsRepository.updateEventCategory(category.getId(), e.getId());
                        }
                    }
                }

                @Override
                public void onFailure(@NotNull Call<EventResponse> call, Throwable t) {
                    ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

                    Runnable run = new Runnable() {
                        public void run() {
                            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                            loadEventsCategories();
                        }
                    };
                    worker.schedule(run, 2, TimeUnit.SECONDS);
                }
            });
        }
    }

    private void loadCategories() {
        service.getListCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.body() != null) {
                    List<Category> list = response.body().getResponse().getCategories();
                    CategoryRepository categoryRepository = CategoryRepository.getInstance();
                    categoryRepository.deleteCategories();
                    categoryRepository.insertCategories(list);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

                Runnable run = new Runnable() {
                    public void run() {
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                        loadCategories();
                    }
                };
                worker.schedule(run, 1, TimeUnit.HOURS);
            }
        });
    }
}
