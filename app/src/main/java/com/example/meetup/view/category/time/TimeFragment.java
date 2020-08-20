package com.example.meetup.view.category.time;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.meetup.databinding.FragmentCategoryByTimeBinding;
import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.personal.joined.JoinedAdapter;

import java.util.List;

public class TimeFragment extends Fragment {
    RecyclerView rvEvent;
    JoinedAdapter adapter;
    TimeViewModel viewModel;
    List<Event> eventList;
    int page = Define.PAGE_SIZE_DEFAULT;
    Category category;
    SwipeRefreshLayout refreshLayout ;
    FragmentCategoryByTimeBinding binding;
    public TimeFragment(Category category){
        this.category = category;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_category_by_time,container,false);
        rvEvent = binding.rvEvent;
        refreshLayout =binding.swl;
        if(getParentFragment()!=null){
        viewModel = new ViewModelProvider(getParentFragment()).get(TimeViewModel.class);
        setUpRecyclerView();
        final Observer<List<Event>> observer = new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventList) {
                adapter.setListEvent(eventList);
            }
        };
        viewModel.getList().observe(getViewLifecycleOwner(),observer);

        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventList = viewModel.getEventList(Define.PAGE_SIZE_DEFAULT,category.getId());
                refreshLayout.setRefreshing(false);
            }
        });
        rvEvent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1)&& newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    page += Define.PAGE_SIZE_DEFAULT;
                    eventList = viewModel.getEventList(page,category.getId());
                }
            }
        });
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        rvEvent.setLayoutManager(layoutManager);
        eventList = viewModel.getEventList(page,category.getId());
        adapter = new JoinedAdapter(eventList,getContext());
        rvEvent.setAdapter(adapter);
    }
}
