package com.example.meetup.UI.News;

import androidx.lifecycle.ViewModelProvider;

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

import com.example.meetup.Model.News;
import com.example.meetup.Adapter.NewsAdapter;
import com.example.meetup.R;

import java.util.List;

public class NewsFragment extends Fragment {
    private RecyclerView news_recycler;
    NewsAdapter news_adapter;
    View view;
    List<News> newsList;
    private NewsViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_fragment, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        news_recycler = view.findViewById(R.id.recycler_news);
        news_adapter = new NewsAdapter(getContext(),newsList);
        news_recycler.setLayoutManager(linearLayoutManager);
        news_recycler.setAdapter(news_adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("NEWS", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("NEWS", "onDestroy: ");
    }
}