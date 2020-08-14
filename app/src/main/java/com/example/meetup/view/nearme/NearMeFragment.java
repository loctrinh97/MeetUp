package com.example.meetup.view.nearme;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Outline;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.meetup.R;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.personal.joined.JoinedViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class NearMeFragment extends Fragment implements OnMapReadyCallback {

    ImageView ivNearImg;
    GoogleMap map;
    MapView mapView;
    NearMeViewModel nearMeViewModel;
    List<Event> nearMe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_me, container, false);
        nearMeViewModel = new ViewModelProvider(getActivity()).get(NearMeViewModel.class);
        ivNearImg = view.findViewById(R.id.ivNearImg);
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth()+90,view.getHeight(),10F);
            }
        };
        ivNearImg.setOutlineProvider(viewOutlineProvider);
        ivNearImg.setClipToOutline(true);

        nearMe = new ArrayList<>();
        nearMe = nearMeViewModel.getEventNearMe();

        return view;
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        LatLng india = new LatLng(21.017461, 105.780308);
        map.addMarker(new MarkerOptions().position(india).title("Rikkeisoft"));
        CameraPosition myPosition = new CameraPosition.Builder()
                .target(india).zoom(15).tilt(0).build();
        googleMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(myPosition));
    }


}
