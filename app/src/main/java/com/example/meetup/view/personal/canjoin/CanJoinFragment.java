package com.example.meetup.view.personal.canjoin;

import android.content.Intent;
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
import com.example.meetup.databinding.FragmentPersonalCanJoinBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.personal.joined.JoinedAdapter;
import com.example.meetup.view.personal.joined.JoinedViewModel;
import com.example.meetup.view.registerlogin.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class CanJoinFragment extends Fragment {
    private RecyclerView recyclerView;
    private JoinedAdapter joinedAdapter;
    private List<Event> canJoinList;
    private JoinedViewModel joinedViewModel;
    private int pageSize = Define.PAGE_SIZE_DEFAULT;
    FragmentPersonalCanJoinBinding fragmentPersonalCanJoinBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPersonalCanJoinBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_personal_can_join, container, false);
        canJoinList = new ArrayList<>();
        joinedViewModel = new ViewModelProvider(getParentFragment()).get(JoinedViewModel.class);
        recyclerView = fragmentPersonalCanJoinBinding.rvJoined;
        setUpRecyclerDisplay();

        final Observer<List<Event>> joinedObserver = new Observer<List<Event>>() {
                    @Override
                    public void onChanged(List<Event> joinedList) {
                        joinedAdapter.setListEvent(joinedList);
            }
        };

        // test click item
//        joinedAdapter.setOnItemClickListener(new JoinedAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(getContext(),joinedList.get(position).getId(),Toast.LENGTH_SHORT);
//            }
//        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_DRAGGING) {
                    pageSize += Define.PAGE_SIZE_DEFAULT;
                    canJoinList = joinedViewModel.getUserCanJoin(pageSize,Define.STATUS_GOING);
                    joinedAdapter.setListEvent(canJoinList);
                }
            }
        });

        joinedViewModel.getCanJoinList().observe(getViewLifecycleOwner(),joinedObserver);
        fragmentPersonalCanJoinBinding.setLifecycleOwner(this);
        fragmentPersonalCanJoinBinding.srlPersonalCanjoinSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                canJoinList = joinedViewModel.getUserCanJoin(Define.PAGE_SIZE_DEFAULT,Define.STATUS_GOING);
                fragmentPersonalCanJoinBinding.srlPersonalCanjoinSwipe.setRefreshing(false);
            }
        });
        return fragmentPersonalCanJoinBinding.getRoot();

    }

    private void setUpRecyclerDisplay() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentPersonalCanJoinBinding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        canJoinList = joinedViewModel.getUserCanJoin(pageSize,Define.STATUS_GOING);
        joinedAdapter = new JoinedAdapter(canJoinList,getContext());
        recyclerView.setAdapter(joinedAdapter);
    }
}
