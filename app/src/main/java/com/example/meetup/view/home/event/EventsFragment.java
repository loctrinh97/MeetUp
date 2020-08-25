package com.example.meetup.view.home.event;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
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
import com.example.meetup.repository.CategoryRepository;
import com.example.meetup.services.worker.UpdateData;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EventsFragment extends Fragment {
    protected RecyclerView recyclerView;
    public EventViewModel eventViewModel;
    protected List<Event> eventList;
    protected EventAdapter adapter;
    int pageSize;
    protected FragmentEventsBinding binding;
    protected SwipeRefreshLayout refreshLayout;
    public SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences(Define.PRE_TOKEN, Context.MODE_PRIVATE);

    protected void setViewModel() {
        eventViewModel = new ViewModelProvider(getParentFragment()).get(EventViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);
        eventList = new ArrayList<>();
        pageSize = Define.PAGE_SIZE_DEFAULT;
        refreshLayout = binding.swipe;
        setViewModel();
        recyclerView = binding.recyclerEvents;
        setUpRecyclerView();
        observe();
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showDetailEvent(position);
            }

            @Override
            public void onClickJoin(int position) {
                joinCheck(position);

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    pageSize += Define.PAGE_SIZE_DEFAULT;
                    eventList = setList(pageSize);
                }
            }
        });

        binding.setLifecycleOwner(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventList = setList(Define.PAGE_SIZE_DEFAULT);
                adapter.setEventList(eventList);
                binding.swipe.setRefreshing(false);
            }
        });
        return binding.getRoot();
    }

    protected List<Event> setList(int pageSize) {
        return eventViewModel.getEventList(pageSize);
    }

    protected void observe() {
        final Observer<List<Event>> eventObserver = new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventList) {
                adapter.setEventList(eventList);
            }
        };
        eventViewModel.getList().observe(getViewLifecycleOwner(), eventObserver);
    }

    protected void showDetailEvent(int position) {
        FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        EventDetailFragment fragment = new EventDetailFragment(eventList.get(position), getContext(), eventViewModel);
        fragmentTransaction.replace(R.id.activity, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    protected void joinCheck(final int position) {
        final String token = sharedPref.getString(Define.TOKEN, "");
        if (token.isEmpty()) {
            DialogLogin dialogLogin = new DialogLogin();
            dialogLogin.showDialog(getActivity());

        } else {
            BottomDialogFragment bottomDialog = new BottomDialogFragment(eventList.get(position).getMyStatus());
            bottomDialog.show(getChildFragmentManager(), "");
            bottomDialog.setBottomListener(new BottomDialogFragment.BottomListener() {
                @Override
                public void onBottomItemClicked(int status) {
                    eventViewModel.updateEvent(status, eventList.get(position).getId());
                    eventList = setList(pageSize);
                    adapter.setEventList(eventList);
                }
            });
        }

    }

    protected void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        eventList = setList(Define.PAGE_SIZE_DEFAULT);
        adapter = new EventAdapter(eventList, getContext());
        recyclerView.setAdapter(adapter);
    }

}