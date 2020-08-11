package com.example.meetup.view.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.repository.VenueRepository;
import com.example.meetup.services.LoadInforWorker;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.view.adapter.EventAdapter;
import com.example.meetup.viewmodel.EventViewModel;
import com.example.meetup.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


public class EventsFragment extends Fragment {
    private RecyclerView recyclerView;
    EventViewModel eventViewModel;
    List<Event> eventList;
    EventAdapter adapter;
    int pageSize;
    private FragmentEventsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        eventList = new ArrayList<Event>();
        pageSize = Define.PAGE_SIZE_DEFAULT;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);
        eventViewModel = new ViewModelProvider(getActivity()).get(EventViewModel.class);

        recyclerView = binding.recyclerEvents;
        setUpRecyclerView();
        final Observer<List<Event>> eventObserver = new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventList) {
                adapter.setEventList(eventList);
            }
        };
        eventViewModel.getList().observe(getViewLifecycleOwner(),eventObserver);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
       eventList = eventViewModel.getEventList(pageSize);
        adapter = new EventAdapter(eventList,getContext());
        recyclerView.setAdapter(adapter);
    }

}