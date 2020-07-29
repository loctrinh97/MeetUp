package com.example.meetup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.Model.News;
import com.example.meetup.R;


import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<News> list_News;
    LayoutInflater inflater;
    Context context;
    public NewsAdapter(Context context, List<News> NewsList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list_News = NewsList;
    }
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

//        Glide.with(context).load(R.drawable.stock_photo)
//                .transform(new RoundedCornersTransformation(context,15,1,RoundedCornersTransformation.CornerType.TOP))
//                .into(holder.thumb);
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumb;
        TextView publishDate;
        TextView title;
        TextView author;
        TextView feed;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.thumnail);
            publishDate = itemView.findViewById(R.id.publish_date);
            title = itemView.findViewById(R.id.news_title);
            author = itemView.findViewById(R.id.author);
            feed = itemView.findViewById(R.id.feed);
        }
    }
}
