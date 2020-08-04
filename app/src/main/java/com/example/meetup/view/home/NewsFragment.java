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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    NewsAdapter news_adapter;
    NewsViewModel newsViewModel;
    List<News> newsList;
    private NewsFragmentBinding binding;
    private final CompositeDisposable mDisposable  = new CompositeDisposable();



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

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
        mDisposable.dispose();
    }

    private void setUpRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        news_adapter = new NewsAdapter(newsViewModel.getNewsList());
        recyclerView.setAdapter(news_adapter);


    }
}