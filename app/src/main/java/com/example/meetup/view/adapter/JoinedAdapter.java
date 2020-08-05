package com.example.meetup.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.R;
import com.example.meetup.model.News;
import com.example.meetup.view.personal.joined.JoinedFragment;

import java.util.ArrayList;

public class JoinedAdapter extends RecyclerView.Adapter<JoinedAdapter.ViewHolder> {
    private Context mContext;

    public JoinedAdapter(JoinedFragment joinedFragment, ArrayList<News> mJoinedList) {
    }

    @NonNull
    @Override
    public JoinedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.item_joined, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JoinedAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnViewEndTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
