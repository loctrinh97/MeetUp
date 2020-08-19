package com.example.meetup.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.meetup.R;
import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.model.response.EventVenue;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.view.home.event.EventsRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadVenueWoker extends Worker {
    public SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences("tokenPref", Context.MODE_PRIVATE);
    String token = sharedPref.getString("token", null);
    public SharedPreferences.Editor sharedPrefEventNearId = sharedPref.edit();

    ApiUtils apiUtils = new ApiUtils();
    private VenueServices venueServices;

    public LoadVenueWoker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        loadListVenueFromApi(token, 8000, 21.017461, 105.780308);
        return Result.success();
    }

    private void loadListVenueFromApi(String token, double radius, double longitue, double latitude) {
        venueServices = apiUtils.getVenueServices();
        venueServices.getListNearlyEvents(token, radius, longitue, latitude).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if ( response.body().getStatus() == 1) {
                    List<EventGetFromApi> listEventNearFromApi = response.body().getResponse().getEvents();
                    List<EventVenue> listEventNearId = new ArrayList<>();
                    StringBuilder sb = new StringBuilder();
                    for (EventGetFromApi e : listEventNearFromApi) {
                        listEventNearId.add(new EventVenue(e.getId(),e.getPhoto(),e.getName(),e.getLink(),e.getGoingCount(), e.getWentCount(), e.getScheduleStartDate(),e.getVenue().getId(), e.getDistance(), e.getVenue().getContactAddress(), e.getVenue().getGeoArea(), e.getVenue().getGeoLong(), e.getVenue().getGeoLat()));
                        sb.append(e.getId()).append(",");
                    }
                    sharedPrefEventNearId.putString("listIdNear", sb.toString());
                    EventsRepository eventsRepository = EventsRepository.getInstance();
                    eventsRepository.updateEventNear(listEventNearId);
//                    eventsRepository.getEventById(listEventNearId);
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(MyApplication.getAppContext(), R.string.api_error, Toast.LENGTH_LONG);
            }
        });
    }
}
