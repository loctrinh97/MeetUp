package com.example.meetup.view.personal.joined;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentPersonalJoinedBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.services.LoadPersonalWorker;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.registerlogin.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class JoinedFragment extends Fragment {
    String token;
    private RecyclerView recyclerView;
    private JoinedAdapter joinedAdapter;
    private List<Event> joinedList;
    private JoinedViewModel joinedViewModel;
    private int pageSize = Define.PAGE_SIZE_DEFAULT;
    FragmentPersonalJoinedBinding fragmentPersonalJoinedBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPref = getContext().getSharedPreferences("tokenPref",MODE_PRIVATE);
        sharedPref.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        token = sharedPref.getString("token",null);
        if (token == null){
            Toast.makeText(getContext(),"asdsa",Toast.LENGTH_SHORT);
        }

        fragmentPersonalJoinedBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_personal_joined, container, false);
        joinedList = new ArrayList<>();
        joinedViewModel = new ViewModelProvider(getParentFragment()).get(JoinedViewModel.class);
        recyclerView = fragmentPersonalJoinedBinding.rvJoined;
        setUpRecyclerDisplay();
        final Observer<List<Event>> joinedObserver = new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> joinedList) {
                joinedAdapter.setListEvent(joinedList);
            }
        };

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_DRAGGING) {
                    pageSize += Define.PAGE_SIZE_DEFAULT;
                    joinedList = joinedViewModel.getUserEventList(pageSize,Define.STATUS_WENT);
                    joinedAdapter.setListEvent(joinedList);
                }
            }
        });
        joinedViewModel.getList().observe(getViewLifecycleOwner(),joinedObserver);
        fragmentPersonalJoinedBinding.setLifecycleOwner(this);
        fragmentPersonalJoinedBinding.srlPersonalJoinedSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                joinedList = joinedViewModel.getUserEventList(Define.PAGE_SIZE_DEFAULT,Define.STATUS_WENT);
                fragmentPersonalJoinedBinding.srlPersonalJoinedSwipe.setRefreshing(false);
            }
        });
        return fragmentPersonalJoinedBinding.getRoot();
    }

    private void setUpRecyclerDisplay() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentPersonalJoinedBinding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        joinedList = joinedViewModel.getUserEventList(pageSize,Define.STATUS_WENT);
        joinedAdapter = new JoinedAdapter(joinedList,getContext());
        recyclerView.setAdapter(joinedAdapter);
    }

    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
            if (key.equals("token")){
                // Write your code here
                token = sharedPref.getString("token",null);
            }
        }
    };

}

