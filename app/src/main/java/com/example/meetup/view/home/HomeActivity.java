package com.example.meetup.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.meetup.R;
import com.example.meetup.services.LoadPersonalWorker;
import com.example.meetup.services.LoadVenueWoker;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.example.meetup.view.category.CategoryFragment;
import com.example.meetup.view.nearme.NearMeFragment;
import com.example.meetup.view.personal.PersonalFragment;
import com.example.meetup.view.personal.login.PersonalLoginFragment;
import com.example.meetup.view.registerlogin.login.LoginViewModel;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    String token;
    LoginViewModel loginViewModel;
    ViewPagerAdapter appAdapter;
    ViewPager appViewPager;
    TabLayout appTabLayout;
    OneTimeWorkRequest oneTimeWorkRequest;

    private int[] tabIcons = {
            R.drawable.ic_icon_home,
            R.drawable.ic_icon_located,
            R.drawable.ic_icon_browse,
            R.drawable.ic_icon_profile
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //worker
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        OneTimeWorkRequest.Builder mBuider = new OneTimeWorkRequest.Builder(LoadPersonalWorker.class);
        mBuider.setConstraints(constraints);
        oneTimeWorkRequest = mBuider.build();
        WorkManager.getInstance(MyApplication.getAppContext()).enqueue(oneTimeWorkRequest);

        OneTimeWorkRequest.Builder mBuiderLoadVenue = new OneTimeWorkRequest.Builder(LoadVenueWoker.class);
        mBuiderLoadVenue.setConstraints(constraints);
        oneTimeWorkRequest = mBuiderLoadVenue.build();
        WorkManager.getInstance(MyApplication.getAppContext()).enqueue(oneTimeWorkRequest);
        loginViewModel = new ViewModelProvider(HomeActivity.this).get(LoginViewModel.class);
        appAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        appViewPager = findViewById(R.id.app_viewPager);
        appTabLayout = findViewById(R.id.app_tabLayout);
        appTabLayout.setupWithViewPager(appViewPager);
        appAdapter.addFrag(new HomeFragment(),"Trang chủ");
        appAdapter.addFrag(new NearMeFragment(),"Gần tôi");
        appAdapter.addFrag(new CategoryFragment(),"Danh mục");
        token = loginViewModel.getPrefToken();
        SharedPreferences sharedPref = this.getSharedPreferences("tokenPref",MODE_PRIVATE);
        sharedPref.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);

        if (token == null){
            appAdapter.addFrag(new PersonalLoginFragment(),getString(R.string.personal));
        } else {
            appAdapter.addFrag(new PersonalFragment(),getString(R.string.personal));
        }
        appViewPager.setAdapter(appAdapter);
        setupTabIcons();
        appTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setTint(getResources().getColor(R.color.color_common));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setTint(getResources().getColor(R.color.color_icon_default));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    private void setupTabIcons() {

        appTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        appTabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.color_common));
        appTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        appTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        appTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        appTabLayout.setTabRippleColor(null);
    }
    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
            if (key.equals("token")){
                // Write your code here
                 token = sharedPref.getString("token",null);
            }
        }
    };

}