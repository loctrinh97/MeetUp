package com.example.meetup.view.personal.joined;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentPersonalJoinedBinding;
import com.example.meetup.databinding.ItemJoinedBinding;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.model.dataLocal.UsersEvents;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.adapter.JoinedAdapter;
import com.example.meetup.view.adapter.NewsAdapter;
import com.example.meetup.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class JoinedFragment extends Fragment {
    private RecyclerView recyclerView;
    private JoinedAdapter joinedAdapter;
    private List<UsersEvents> joinedList;
    private JoinedViewModel joinedViewModel;
    private int pageSize = 10;
    FragmentPersonalJoinedBinding fragmentPersonalJoinedBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPersonalJoinedBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_personal_joined, container, false);
        joinedList = new ArrayList<>();
        joinedViewModel = new ViewModelProvider(getParentFragment()).get(JoinedViewModel.class);
        recyclerView = fragmentPersonalJoinedBinding.rvJoined;
        setUpRecyclerDisplay();

        // test click item
        joinedAdapter.setOnItemClickListener(new JoinedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(),joinedList.get(position).getEventId(),Toast.LENGTH_SHORT);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_DRAGGING) {
                    pageSize += 10;
                    joinedList = joinedViewModel.getUserEventList();
                    joinedAdapter.setListUsersEvent(joinedList);
                }
            }
        });
        // TODO: 8/10/2020
        return fragmentPersonalJoinedBinding.getRoot();

    }

    private void setUpRecyclerDisplay() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentPersonalJoinedBinding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        joinedList = joinedViewModel.getUserEventList();
        joinedAdapter = new JoinedAdapter(joinedList,getContext());
        recyclerView.setAdapter(joinedAdapter);
    }

}

