package com.example.meetup.view.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentEventsBinding;
import com.example.meetup.viewmodel.NewsViewModel;


public class EventsFragment extends Fragment {
    NewsViewModel newsViewModel;
    private  FragmentEventsBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_events,container,false);
       newsViewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);
       binding.setViewmodel(newsViewModel);
       binding.setLifecycleOwner(this);
//        // Inflate the layout for this fragment
//        inflater.inflate(R.layout.fragment_events, container, false);
        return binding.getRoot();
    }

}