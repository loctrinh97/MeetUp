package com.example.meetup.view.nearme;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Outline;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;

import com.example.meetup.R;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.ulti.PermissionUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.meetup.ulti.Define.REQUEST_CODE_GPS_PERMISSION;

public class NearMeFragment extends Fragment implements OnMapReadyCallback {

    ImageView ivNearImg;
    GoogleMap map;
    MapView mapView;
    NearMeViewModel nearMeViewModel;
    List<Event> nearMe;
    List<Venue> venues;
    LatLng eventLatLng;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_me, container, false);
        nearMeViewModel = new ViewModelProvider(getActivity()).get(NearMeViewModel.class);
        ivNearImg = view.findViewById(R.id.ivNearImg);
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth() + 90, view.getHeight(), 10F);
            }
        };
        ivNearImg.setOutlineProvider(viewOutlineProvider);
        ivNearImg.setClipToOutline(true);
        nearMeViewModel.getEventNearMe();
        nearMe = new ArrayList<>();
        nearMe = nearMeViewModel.getEventNearMe();
        venues = nearMeViewModel.getVenuesNearMe();
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
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        LatLng india = new LatLng(21.017461, 105.780308);
        for (int i = 0; i < venues.size(); i++) {
            eventLatLng = new LatLng(Double.parseDouble(venues.get(i).getGeoLat()), Double.parseDouble(venues.get(i).getGeoLong()));
            map.addMarker(new MarkerOptions().position(eventLatLng).title(venues.get(i).getName()));
        }
        map.addMarker(new MarkerOptions().position(india).title("My Location"));
        CameraPosition myPosition = new CameraPosition.Builder()
                .target(india).zoom(15).tilt(0).build();
        googleMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(myPosition));
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
                PermissionUtils.setShouldShowStatus(getContext(), Manifest.permission.SEND_SMS);
            }
        }
    }
}
