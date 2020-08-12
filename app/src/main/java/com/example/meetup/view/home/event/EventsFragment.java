package com.example.meetup.view.home.event;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentEventsBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.ulti.Define;

import java.util.ArrayList;
import java.util.List;


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
        eventList = new ArrayList<>();
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
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String test = eventList.get(position).toString();
                Log.d("Event", "onItemClick: "+ test);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_DRAGGING) {
                    pageSize += 10;
                    eventList = eventViewModel.getEventList(pageSize);
                    adapter.setEventList(eventList);

                }
            }
        });
        eventViewModel.getList().observe(getViewLifecycleOwner(),eventObserver);
        binding.setLifecycleOwner(this);
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Event", "onRefresh: ");
                eventList = eventViewModel.getEventList(Define.PAGE_SIZE_DEFAULT);
                binding.swipe.setRefreshing(false);
            }
        });
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