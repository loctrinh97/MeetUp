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
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.meetup.R;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.example.meetup.view.category.CategoryFragment;
import com.example.meetup.view.nearme.NearMeFragment;
import com.example.meetup.view.personal.PersonalFragment;
import com.example.meetup.view.personal.login.PersonalLoginFragment;
import com.example.meetup.view.registerlogin.login.LoginViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    String token;
    LoginViewModel loginViewModel;
    ViewPagerAdapter appAdapter;
    ViewPager appViewPager;
    TabLayout appTabLayout;


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
        loginViewModel = new ViewModelProvider(HomeActivity.this).get(LoginViewModel.class);
        appAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        appViewPager = findViewById(R.id.app_viewPager);
        appTabLayout = findViewById(R.id.app_tabLayout);
        appTabLayout.setupWithViewPager(appViewPager);
        appViewPager.setOffscreenPageLimit(appAdapter.getCount());
        appAdapter.addFrag(new HomeFragment(), getString(R.string.MyPage));
        appAdapter.addFrag(new NearMeFragment(), getString(R.string.nearMe));
        appAdapter.addFrag(new CategoryFragment(), getString(R.string.categories));
        token = loginViewModel.getPrefToken();
        if (token == null) {
                appAdapter.addFrag(new PersonalLoginFragment(), getString(R.string.personal));
                SharedPreferences sharedPref = this.getSharedPreferences(Define.PRE_TOKEN, MODE_PRIVATE);
                sharedPref.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
            } else {
                appAdapter.addFrag(new PersonalFragment(), getString(R.string.personal));
            }
            appViewPager.setAdapter(appAdapter);
            setupTabIcons();
            appTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Objects.requireNonNull(tab.getIcon()).setTint(getResources().getColor(R.color.color_common));
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    Objects.requireNonNull(tab.getIcon()).setTint(getResources().getColor(R.color.color_icon_default));
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


        }


    private void setupTabIcons(){

        Objects.requireNonNull(appTabLayout.getTabAt(0)).setIcon(tabIcons[0]);
        Objects.requireNonNull(Objects.requireNonNull(appTabLayout.getTabAt(0)).getIcon()).setTint(getResources().getColor(R.color.color_common));
        Objects.requireNonNull(appTabLayout.getTabAt(1)).setIcon(tabIcons[1]);
        Objects.requireNonNull(appTabLayout.getTabAt(2)).setIcon(tabIcons[2]);
        Objects.requireNonNull(appTabLayout.getTabAt(3)).setIcon(tabIcons[3]);
        appTabLayout.setTabRippleColor(null);
    }

    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
            if (key.equals("token")) {
                // Write your code here
                token = sharedPref.getString("token", null);
            }
        }
    };

}