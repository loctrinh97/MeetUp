package com.example.meetup.view.home.news;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.meetup.model.dataLocal.News;
import com.example.meetup.ulti.Define;
import com.example.meetup.R;
import com.example.meetup.databinding.NewsFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    NewsViewModel newsViewModel;
    List<News> newsList;
    int pageSize;
    private NewsFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        newsList = new ArrayList<>();
        pageSize = Define.PAGE_SIZE_DEFAULT;
        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        newsViewModel = new ViewModelProvider(getParentFragment()).get(NewsViewModel.class);
        recyclerView = binding.recyclerNews;
        setUpRecyclerView();
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).getDetailUrl()));
                startActivity(browserIntent);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    pageSize += 10;
                    newsList = newsViewModel.getNewsList(pageSize);
                    newsAdapter.setListNews(newsList);

                }
            }
        });
        final Observer<List<News>> newsObserver = new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                newsAdapter.setListNews(news);
            }
        };
        newsViewModel.getCurrentList().observe(getViewLifecycleOwner(), newsObserver);
        binding.setLifecycleOwner(this);
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("News", "onRefresh: ");
                newsList = newsViewModel.getNewsList(10);
                binding.swipe.setRefreshing(false);
            }

        });

        return binding.getRoot();
    }

    private void setUpRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        newsList = newsViewModel.getNewsList(pageSize);
        newsAdapter = new NewsAdapter(newsList, getContext());
        recyclerView.setAdapter(newsAdapter);

    }
}