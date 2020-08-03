package com.example.meetup.view.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.meetup.R;
import com.example.meetup.base.BaseFragment;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class PersonalFragment extends BaseFragment {
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    public PersonalFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        viewPager = view.findViewById(R.id.vp_personal);
        tabLayout = view.findViewById(R.id.tl_personal);
        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFrag(new CanJoinFragment(),getString(R.string.can_join));
        viewPagerAdapter.addFrag(new JoinedFragment(),getString(R.string.joined));
        viewPager.setAdapter(viewPagerAdapter);
        return view;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}