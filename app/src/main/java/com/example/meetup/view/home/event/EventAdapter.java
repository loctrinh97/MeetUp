package com.example.meetup.view.home.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.meetup.R;
import com.example.meetup.databinding.ItemEventBinding;
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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> eventList;
    OnItemClickListener listener;
    private Context context;
    private ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + 90, 90F);
        }
    };

    public EventAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemEventBinding binding = ItemEventBinding.inflate(layoutInflater, parent, false);

        return new EventAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventAdapter.ViewHolder holder, final int position) {
        Event event = eventList.get(position);
        Glide.with(context)
                .load(event.getPhoto())
                .placeholder(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.binding.thumbnail);
        holder.binding.thumbnail.setOutlineProvider(viewOutlineProvider);
        holder.binding.thumbnail.setClipToOutline(true);
        if (event.getMyStatus() == Define.STATUS_DEFAULT) {
            Glide.with(context)
                    .load(R.drawable.ic_join)
                    .into(holder.binding.ivJoin);
        } else if (event.getMyStatus() == Define.STATUS_GOING) {
            Glide.with(context)
                    .load(R.drawable.ic_can_join)
                    .into(holder.binding.ivJoin);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_joined)
                    .into(holder.binding.ivJoin);
        }
        holder.binding.ivJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickJoin(position);
                }
            }
        });
        String date = checkDate(event);
        holder.binding.tvEventDate.setText(date);
        if (event.getGoingCount() == 0) {
            holder.binding.imgDot.setVisibility(View.GONE);
            holder.binding.tvGoingCount.setVisibility(View.GONE);
        } else {
            holder.binding.imgDot.setVisibility(View.VISIBLE);
            holder.binding.tvGoingCount.setVisibility(View.VISIBLE);
            holder.binding.tvGoingCount.setText(event.getGoingCount() + " sẽ tham gia");
        }
        holder.bind(event);

    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEventBinding binding;


        public ViewHolder(ItemEventBinding binding) {
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

        public void bind(Event event) {
            binding.setEvent(event);
            binding.executePendingBindings();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onClickJoin(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
