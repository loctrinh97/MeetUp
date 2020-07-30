package com.example.meetup.view.news;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetup.view.adapter.NewsAdapter;
import com.example.meetup.R;
import com.example.meetup.databinding.NewsFragmentBinding;
import com.example.meetup.viewmodel.NewsViewModel;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    NewsAdapter news_adapter;
    NewsViewModel newsViewModel;
    private NewsFragmentBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.news_fragment,container,false);
        newsViewModel = new NewsViewModel();
        setUpRecyclerView();
        View view = binding.getRoot();
        return view;
    }
    private void setUpRecyclerView(){
        recyclerView = binding.recyclerNews;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        news_adapter = new NewsAdapter(newsViewModel.getNewsList());
        recyclerView.setAdapter(news_adapter);


    }
}