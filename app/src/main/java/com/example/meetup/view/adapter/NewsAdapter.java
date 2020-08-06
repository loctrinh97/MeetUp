package com.example.meetup.view.adapter;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.meetup.R;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.databinding.ItemNewsBinding;


import java.util.List;

import static com.example.meetup.R.*;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<News> listNews;
    public OnItemClickListener listener;
    ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0,0,view.getWidth(),view.getHeight()+90,90F);
        }
    };
    Context context;
    public NewsAdapter(List<News> NewsList,Context context) {
        this.listNews = NewsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding binding = ItemNewsBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder holder, int position) {
        News news = listNews.get(position);

        if(news.getThumb()!=null){
            Glide.with(context)
                    .load(news.getThumb())
                    .placeholder(drawable.error)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            return false;
                        }
                    })
                    .into(holder.binding.thumbnail);
            ImageView image = holder.binding.thumbnail;
            image.setOutlineProvider(viewOutlineProvider);
            image.setClipToOutline(true);
        }
        else{
            holder.binding.thumbnail.setVisibility(View.GONE);
        }

        holder.bind(news);


    }
    public void setListNews(List<News> news){
        this.listNews = news;
        notifyDataSetChanged();
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
