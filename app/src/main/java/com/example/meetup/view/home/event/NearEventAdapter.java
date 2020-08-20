package com.example.meetup.view.home.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meetup.R;
import com.example.meetup.databinding.ItemEventNearBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.ulti.Define;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.meetup.ulti.Define.checkDate;

public class NearEventAdapter extends RecyclerView.Adapter<NearEventAdapter.ViewHolder> {
    public static final String GOING = " sẽ tham gia";
    Context context;
    private OnItemClickListener listener;
    List<Event> eventList;

    private ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth()+45, view.getHeight() , 45F);
        }
    };
    public NearEventAdapter(List<Event> eventList, Context context) {
       this.context = context;
       this.eventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemEventNearBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_event_near,parent,false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);
        Glide.with(context)
                .load(event.getPhoto())
                .into(holder.binding.ivItemNearImg);
        holder.binding.ivItemNearImg.setOutlineProvider(viewOutlineProvider);
        holder.binding.ivItemNearImg.setClipToOutline(true);
        if (event.getGoingCount() == 0) {
            holder.binding.tvItemNearAmount.setVisibility(View.GONE);
        } else {
            holder.binding.tvItemNearAmount.setVisibility(View.VISIBLE);
            holder.binding.tvItemNearAmount.setText(event.getGoingCount() +GOING);
        }
        holder.binding.tvItemNearTitle.setText(event.getName());
        String date = checkDate(event);
        holder.binding.tvItemNearTime.setText(date);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemEventNearBinding binding;
        public ViewHolder(@NonNull ItemEventNearBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
