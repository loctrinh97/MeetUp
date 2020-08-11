package com.example.meetup.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetup.R;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.example.meetup.view.home.event.EventsFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    ViewPagerAdapter adapter_home;
    ViewPager viewpager_home;
    TabLayout tabLayout;

    public HomeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewpager_home = view.findViewById(R.id.viewpager_athome);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewpager_home);
        adapter_home = new ViewPagerAdapter(getChildFragmentManager());
        adapter_home.addFrag(new NewsFragment(),"TIN TỨC");
        adapter_home.addFrag(new EventsFragment(),"SỰ KIỆN");
        viewpager_home.setAdapter(adapter_home);
        return view;
    }


}