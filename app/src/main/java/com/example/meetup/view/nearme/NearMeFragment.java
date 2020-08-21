package com.example.meetup.view.nearme;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentNearMeBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.ulti.PermissionUtils;
import com.example.meetup.view.home.event.NearEventAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;
import static com.example.meetup.ulti.Define.REQUEST_CODE_GPS_PERMISSION;

public class NearMeFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    MapView mapView;
    NearMeViewModel nearMeViewModel;
    List<Event> nearMe;
    List<Venue> venues;
    List<Marker> listMarker;
    LatLng eventLatLng, chooseLatLng;
    Marker myMarker;
    FusedLocationProviderClient fusedLocationProviderClient;
    NearEventAdapter nearEventAdapter;
    Context context;
    RecyclerView recyclerView;
    FragmentNearMeBinding fragmentNearMeBinding;
    double latitude,longitude;
    int lastScrollPosition;
    private MutableLiveData<String> eventName = new MutableLiveData<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentNearMeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_near_me, container, false);
        fragmentNearMeBinding.setLifecycleOwner(getViewLifecycleOwner());
        nearMe = new ArrayList<>();

        if (isGpsOn()){
            nearMeViewModel = new ViewModelProvider(getActivity()).get(NearMeViewModel.class);
            venues = nearMeViewModel.getVenuesNearMe();
            recyclerView = fragmentNearMeBinding.rvEventNear;
            setUpRecyclerView();
            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
            nearEventAdapter.setOnItemClickListener(new NearEventAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
        } else {
            AlertDialog.Builder dialogGPS = new AlertDialog.Builder(getActivity());
            dialogGPS.setTitle("Vui lòng bật GPS");
        }
        return fragmentNearMeBinding.getRoot();
    }
    private void setUpRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        nearMe = nearMeViewModel.getEventNearMe();
        nearEventAdapter = new NearEventAdapter(nearMe, getContext());
        recyclerView.setAdapter(nearEventAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastScrollPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                Log.d("test1", String.valueOf(lastScrollPosition));
                if (lastScrollPosition>0){
                    eventName.setValue(venues.get(lastScrollPosition).getName());
                }
            }
        });
    }
    @Override
    public void onResume() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        getLastLocation();
        super.onResume();
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PermissionUtils.neverAskAgainSelected(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                    displayNeverAskAgainDialog();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_GPS_PERMISSION);
                }
            }
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    map.addMarker(new MarkerOptions().position(latLng).title("My Location"));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                }
            }
        });
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
        if (isGpsOn()){
            MapsInitializer.initialize(getContext());
            map = googleMap;
            listMarker = new ArrayList<>();
            final BitmapDescriptor notSelected = BitmapDescriptorFactory.fromResource(R.drawable.not_selected);
            final BitmapDescriptor selected = BitmapDescriptorFactory.fromResource(R.drawable.selected);
            for (int i = 0; i < venues.size(); i++) {
                eventLatLng = new LatLng(Double.parseDouble(venues.get(i).getGeoLat()), Double.parseDouble(venues.get(i).getGeoLong()));
                myMarker = map.addMarker(new MarkerOptions().position(eventLatLng).icon(notSelected).title(venues.get(i).getName()));
                listMarker.add(myMarker);
            }
            eventName.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    for (int i =0 ; i < listMarker.size();i++){
                        if (listMarker.get(i).getTitle().equals(s)){
                            listMarker.get(i).setIcon(selected);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(listMarker.get(i).getPosition(),15f));
                        } else {
                            listMarker.get(i).setIcon(notSelected);
                        }
                    }

                }
            });
        }
    }

    private boolean isGpsOn() {
        LocationManager manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void displayNeverAskAgainDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.permission_ask)
                + getString(R.string.permission_setting));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.manual_access, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", MyApplication.getAppContext().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        if (REQUEST_CODE_GPS_PERMISSION == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), R.string.granted_permissions_success, Toast.LENGTH_LONG).show();
            } else {
                PermissionUtils.setShouldShowStatus(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }
    }
}
