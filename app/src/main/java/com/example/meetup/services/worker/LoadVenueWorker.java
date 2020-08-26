package com.example.meetup.services.worker;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.model.response.EventVenue;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.services.ApiUtils;
import com.example.meetup.services.BaseService;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadVenueWorker extends Worker {
    ApiUtils apiUtils = new ApiUtils();
    BaseService service = apiUtils.getService();
    public SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences(Define.PRE_TOKEN, Context.MODE_PRIVATE);
    String token = sharedPref.getString(Define.TOKEN, "");
    public SharedPreferences.Editor sharedPrefEventNearId = sharedPref.edit();

    public LoadVenueWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private void loadListVenueFromApi(final String token, final double radius, final double longitue, final double latitude) {
        service.getListNearlyEvents(token, radius, longitue, latitude).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    List<EventGetFromApi> listEventNearFromApi = response.body().getResponse().getEvents();
                    List<EventVenue> listEventNearId = new ArrayList<>();
                    StringBuilder sb = new StringBuilder();
                    for (EventGetFromApi e : listEventNearFromApi) {
                        listEventNearId.add(new EventVenue(e.getId(), e.getPhoto(), e.getName(), e.getLink(), e.getGoingCount(), e.getWentCount(), e.getScheduleStartDate(), e.getVenue().getId(), e.getDistance(), e.getVenue().getContactAddress(), e.getVenue().getGeoArea(), e.getVenue().getGeoLong(), e.getVenue().getGeoLat()));
                        sb.append(e.getId()).append(",");
                    }
                    sharedPrefEventNearId.putString("listIdNear", sb.toString());
                    EventsRepository eventsRepository = EventsRepository.getInstance();
                    eventsRepository.updateEventNear(listEventNearId);
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

                Runnable run = new Runnable() {
                    public void run() {
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                        loadListVenueFromApi(token,radius,longitue,latitude);
                    }
                };
                worker.schedule(run, 2, TimeUnit.SECONDS);
            }
        });
    }

    @NonNull
    @Override
    public Result doWork() {
        if (Define.CURRENT_LOCATION_LAT != 0.0 && Define.CURRENT_LOCATION_LONG != 0.0) {
            loadListVenueFromApi(token, 7, Define.CURRENT_LOCATION_LONG, Define.CURRENT_LOCATION_LAT);
        }
        return Result.success();
    }
}
