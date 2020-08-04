package com.example.meetup.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.meetup.R;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
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
        appAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        appViewPager = findViewById(R.id.app_viewPager);
        appTabLayout = findViewById(R.id.app_tabLayout);
        appTabLayout.setupWithViewPager(appViewPager);
        appAdapter.addFrag(new HomeFragment(),"Trang chủ");
        appAdapter.addFrag(new HomeFragment(),"Gần tôi");
        appAdapter.addFrag(new HomeFragment(),"Danh mục");
        appAdapter.addFrag(new HomeFragment(),"Cá nhân");
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
//        app_tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.color_common));
        appTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        appTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        appTabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}