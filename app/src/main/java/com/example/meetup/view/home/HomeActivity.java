package com.example.meetup.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.meetup.R;
import com.example.meetup.model.Event;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    ViewPagerAdapter app_adapter;
    ViewPager app_viewPager;
    TabLayout app_tabLayout;
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
        app_adapter = new ViewPagerAdapter(getSupportFragmentManager());
        app_viewPager = findViewById(R.id.app_viewPager);
        app_tabLayout = findViewById(R.id.app_tabLayout);
        app_tabLayout.setupWithViewPager(app_viewPager);
        app_adapter.addFrag(new HomeFragment(),"Trang chủ");
        app_adapter.addFrag(new HomeFragment(),"Gần tôi");
        app_adapter.addFrag(new HomeFragment(),"Danh mục");
        app_adapter.addFrag(new HomeFragment(),"Cá nhân");
        app_viewPager.setAdapter(app_adapter);
        setupTabIcons();
        app_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        app_tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        app_tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.color_common));
        app_tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        app_tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        app_tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}