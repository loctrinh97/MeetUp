package com.example.meetup.view.category.time;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetup.R;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.example.meetup.view.home.event.EventViewModel;
import com.example.meetup.view.home.event.EventsFragment;
import com.example.meetup.view.home.news.NewsFragment;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {
    String keyword;
    ViewPagerAdapter adapter;
    ViewPager viewpager;
    TabLayout tabLayout;
    SearchViewModel mViewModel;
    public SearchFragment(String keyword){
        this.keyword = keyword;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment,container,false);
        mViewModel = new ViewModelProvider(getParentFragment()).get(SearchViewModel.class);
        viewpager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewpager);
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new EventCategoryFragment(0,mViewModel),"TIN TỨC");
        adapter.addFrag(new EventCategoryFragment(1,mViewModel),"SỰ KIỆN");
        viewpager.setAdapter(adapter);
        return view;
    }
}