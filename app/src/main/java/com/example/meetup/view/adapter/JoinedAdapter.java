package com.example.meetup.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.R;
import com.example.meetup.databinding.ItemJoinedBinding;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.model.dataLocal.UsersEvents;
import com.example.meetup.view.personal.joined.JoinedFragment;

import java.util.ArrayList;
import java.util.List;

public class JoinedAdapter extends RecyclerView.Adapter<JoinedAdapter.ViewHolder> {
    private OnItemClickListener listener;
    private Context mContext;
    private List<UsersEvents> usersEvents;

    public JoinedAdapter(List<UsersEvents> usersEvents, Context context) {
        this.usersEvents = usersEvents;
        this.mContext = context;
    }
    @NonNull
    @Override
    public JoinedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ItemJoinedBinding itemJoinedBinding = ItemJoinedBinding.inflate(inflater, parent, false);
        return new ViewHolder(itemJoinedBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinedAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemJoinedBinding itemJoinedBinding;
        private Button btnViewEndTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public ViewHolder(ItemJoinedBinding itemJoinedBinding) {
            super(itemJoinedBinding.getRoot());
            this.itemJoinedBinding = itemJoinedBinding;
            itemJoinedBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
        }
    }
    public void setListUsersEvent(List<UsersEvents> usersEvent){
        this.usersEvents = usersEvent;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
