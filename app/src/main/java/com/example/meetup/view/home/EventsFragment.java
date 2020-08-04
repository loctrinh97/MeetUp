package com.example.meetup.view.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentEventsBinding;
import com.example.meetup.services.LoadInforWorker;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.viewmodel.NewsViewModel;


public class EventsFragment extends Fragment {
    NewsViewModel newsViewModel;
    OneTimeWorkRequest workRequest;
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


        binding.testApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
                OneTimeWorkRequest.Builder myBuilder = new OneTimeWorkRequest.Builder(LoadInforWorker.class);
                myBuilder.setConstraints(constraints);
                workRequest = myBuilder.build();
                WorkManager.getInstance(MyApplication.getAppContext()).enqueue(workRequest);
//                Log.d("TestDB", "onClick: "+newsViewModel.getNews(1).toString());
            }
        });
        return binding.getRoot();
    }

}