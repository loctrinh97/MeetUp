package com.example.meetup.UI.Home;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetup.Base.BaseFragment;
import com.example.meetup.UI.News.NewsFragment;
import com.example.meetup.R;
import com.example.meetup.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends BaseFragment {
    ViewPagerAdapter adapter_home;
    ViewPager viewpager_home;
    TabLayout tabLayout;

    public HomeFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Hello", "onCreate: ");
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewpager_home = view.findViewById(R.id.viewpager_athome);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewpager_home);
        adapter_home = new ViewPagerAdapter(getChildFragmentManager());
        adapter_home.addFrag(new NewsFragment(),"Tin tức");
        adapter_home.addFrag(new NewsFragment(),"Sự kiện");
        viewpager_home.setAdapter(adapter_home);
        return view;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}