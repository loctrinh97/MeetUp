package com.example.meetup.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.meetup.model.News;
import com.example.meetup.databinding.ItemNewsBinding;
import com.example.meetup.ulti.MyApplication;


import java.util.List;

import io.reactivex.Flowable;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<News> listNews;
    public OnItemClickListener listener;
    public NewsAdapter(List<News> NewsList) {
        this.listNews = NewsList;
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
        News news = listNews.get(position);
        Glide.with(holder.binding.getRoot())
                .load(news.getDetailUrl())
                .override(300,100)
                .into(holder.binding.thumbnail);
        holder.bind(news);


    }
    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        public ViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.d("Adapter", "onClick: "+ position);
                    listener.onItemClick(position);
                }
            });


        }

        public void bind(News news) {
            binding.setNews(news);
            binding.executePendingBindings();
        }
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
