package com.example.meetup.view.home;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetup.model.News;
import com.example.meetup.view.adapter.NewsAdapter;
import com.example.meetup.R;
import com.example.meetup.databinding.NewsFragmentBinding;
import com.example.meetup.viewmodel.NewsViewModel;

import java.util.List;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    NewsAdapter news_adapter;
    NewsViewModel newsViewModel;
    private NewsFragmentBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.news_fragment,container,false);
        newsViewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);
        recyclerView = binding.recyclerNews;
        setUpRecyclerView();

        final Observer<List<News>> newsObserver = new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                news_adapter.notifyDataSetChanged();
            }
        };
        newsViewModel.getCurrentList().observe(getViewLifecycleOwner(),newsObserver);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
    private void setUpRecyclerView(){


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        news_adapter = new NewsAdapter( newsViewModel.getNewsList());
        recyclerView.setAdapter(news_adapter);


    }
}