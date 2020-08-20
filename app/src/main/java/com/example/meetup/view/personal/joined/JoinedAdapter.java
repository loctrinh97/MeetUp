package com.example.meetup.view.personal.joined;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meetup.R;
import com.example.meetup.databinding.ItemJoinedBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.ulti.MyApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JoinedAdapter extends RecyclerView.Adapter<JoinedAdapter.ViewHolder> {
    private OnItemClickListener listener;
    private Context mContext;
    private List<Event> listEvent;
    private String notification;
    private List<String> noti = new ArrayList<>();

    Locale locale = new Locale("vi");
    @SuppressLint("SimpleDateFormat")
    DateFormat dateFormat = new SimpleDateFormat("E, yyyy-MM-dd", locale);

    public JoinedAdapter(List<Event> event, Context context) {
        this.listEvent = event;
        this.mContext = context;
    }

    @NonNull
    @Override
    public JoinedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ItemJoinedBinding itemJoinedBinding = ItemJoinedBinding.inflate(inflater, parent, false);
        return new ViewHolder(itemJoinedBinding);
    }

    ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + 90, 90F);
        }
    };

    @Override
    public void onBindViewHolder(@NonNull JoinedAdapter.ViewHolder holder, int position) {
//        Collections.sort(listEvent, new Comparator<Event>() {
//            @Override
//            public int compare(Event o1, Event o2) {
//                if (o1.getScheduleStartDate() == null || o1.getScheduleEndDate() == null){
//                    return 0;
//                }
//                return o1.getScheduleStartDate().compareTo(o2.getScheduleStartDate());
//            }
//        });
        Event event = listEvent.get(position);
        Glide.with(mContext)
                .load(event.getPhoto())
                .placeholder(R.drawable.error)
                .into(holder.itemJoinedBinding.ivPhoto);
        holder.itemJoinedBinding.ivPhoto.setOutlineProvider(viewOutlineProvider);
        holder.itemJoinedBinding.ivPhoto.setClipToOutline(true);
        String date = checkDate(event);
        if (event.getMyStatus() == 1) {
            Glide.with(mContext)
                    .load(R.drawable.ic_can_join)
                    .into(holder.itemJoinedBinding.ivStatus);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.ic_joined)
                    .into(holder.itemJoinedBinding.ivStatus);
        }
        checkEndDay(event);
        noti.add(notification);
        if (position >= 1 && noti.size() > position && notification.equals(noti.get(position - 1))) {
            holder.itemJoinedBinding.btnViewEndTime.setVisibility(View.GONE);
        } else {
            holder.itemJoinedBinding.btnViewEndTime.setVisibility(View.VISIBLE);
        }
        holder.itemJoinedBinding.btnViewEndTime.setText(notification);
        holder.itemJoinedBinding.tvEventTime.setText(date);
        holder.itemJoinedBinding.tvPeopleJoin.setText(event.getGoingCount() + " " + MyApplication.getAppContext().getString(R.string.will_join));
        holder.itemJoinedBinding.tvDescription.setText(Html.fromHtml(event.getDescriptionHtml().substring(0, 110) + "..."));
        holder.bind(event);
    }


    @Override
    public int getItemCount() {
        return listEvent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemJoinedBinding itemJoinedBinding;
        private Button btnViewEndTime;

        public ViewHolder(ItemJoinedBinding itemJoinedBinding) {
            super(itemJoinedBinding.getRoot());
            this.itemJoinedBinding = itemJoinedBinding;
//            itemJoinedBinding.getRoot().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    listener.onItemClick(position);
//                }
//            });
        }

        public void bind(Event event) {
            itemJoinedBinding.setJoined(event);
            itemJoinedBinding.executePendingBindings();
        }
    }

    public void setListEvent(List<Event> event) {
        this.listEvent = event;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private String checkDate(Event event) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = simpleDateFormat.parse(event.getScheduleStartDate());
            return dateFormat.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkEndDay(Event event) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        long currentDate = date.getTime();
        try {
            Date endDate = formatter.parse(event.getScheduleEndDate());
            Date startDate = formatter.parse(event.getScheduleStartDate());
            long endDatelong = endDate.getTime();
            long startDatelong = startDate.getTime();
            if (currentDate == endDatelong) {
                notification = mContext.getString(R.string.end_to_day);
            }
            else if (currentDate > endDatelong) {
                notification = mContext.getString(R.string.finish);
            }
            else if (currentDate < endDatelong) {
                notification = mContext.getString(R.string.upcoming);
            }
            else if (currentDate < endDatelong && currentDate > startDatelong) {
                notification = mContext.getString(R.string.happenning);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
