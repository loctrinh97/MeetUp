package com.example.meetup.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.model.News;
import com.example.meetup.databinding.ItemNewsBinding;


import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<News> list_News;
    public NewsAdapter(List<News> NewsList) {
        this.list_News = NewsList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding binding = ItemNewsBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = list_News.get(position);
        holder.bind(news);
    }


    @Override
    public int getItemCount() {
        return list_News.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        public ViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(News news) {
            binding.setNews(news);
            binding.executePendingBindings();
        }
    }

}
