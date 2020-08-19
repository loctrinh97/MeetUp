package com.example.meetup.view.personal.joined;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.text.Html;
import android.util.Log;
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
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class JoinedAdapter extends RecyclerView.Adapter<JoinedAdapter.ViewHolder> {
    public static final String GOING = " sẽ tham gia";
    Locale locale = new Locale("vi");
    private OnItemClickListener listener;
    private Context mContext;
    private List<Event> listEvent;
    private static Date currentTime = Calendar.getInstance().getTime();
    private String[] timeStamp = {"HÔM NAY", "NGÀY MAI", "CUỐI TUẦN NÀY", "TUẦN TỚI", "CUỐI THÁNG NÀY", "THÁNG SAU TRỞ ĐI", "VĨNH VIỄN"};
    private int[] flag = new int[7];
    private static final int EMPTY = -1;

    public JoinedAdapter(List<Event> event, Context context) {
        this.listEvent = event;
        this.mContext = context;
        sort();
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull JoinedAdapter.ViewHolder holder, int position) {
        Event event = listEvent.get(position);
        Glide.with(mContext)
                .load(event.getPhoto())
                .placeholder(R.drawable.error)
                .into(holder.itemJoinedBinding.ivPhoto);
        holder.itemJoinedBinding.ivPhoto.setOutlineProvider(viewOutlineProvider);
        holder.itemJoinedBinding.ivPhoto.setClipToOutline(true);

        if (event.getMyStatus() == 1) {
            Glide.with(mContext)
                    .load(R.drawable.ic_can_join)
                    .into(holder.itemJoinedBinding.ivStatus);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.ic_joined)
                    .into(holder.itemJoinedBinding.ivStatus);
        }

        for (int i = Define.TODAY; i <= Define.PERMANENT; i++) {
            if (flag[i] == position) {
                holder.itemJoinedBinding.btnViewEndTime.setVisibility(View.VISIBLE);
                holder.itemJoinedBinding.btnViewEndTime.setText(timeStamp[i]);
                break;
            } else {
                holder.itemJoinedBinding.btnViewEndTime.setVisibility(View.GONE);
            }
        }
        String date = Define.checkDate(event);
        holder.itemJoinedBinding.tvEventTime.setText(date);
        if (event.getGoingCount() == 0) {
            holder.itemJoinedBinding.ivDot.setVisibility(View.GONE);
            holder.itemJoinedBinding.tvPeopleJoin.setVisibility(View.GONE);
        } else {
            holder.itemJoinedBinding.ivDot.setVisibility(View.VISIBLE);
            holder.itemJoinedBinding.tvPeopleJoin.setVisibility(View.VISIBLE);
            holder.itemJoinedBinding.tvPeopleJoin.setText(event.getGoingCount() + GOING);
        }

        holder.itemJoinedBinding.tvDescription.setText(event.getDescriptionRaw());
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
        sort();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    private void clear() {
        for (int i = 0; i < 7; i++) {
            flag[i] = EMPTY;
        }
    }

    private void sort() {
        clear();
        Collections.sort(listEvent, comparator);
        for (int i = 0; i < listEvent.size(); i++) {
            Event e = listEvent.get(i);
            if (e.getSchedulePermanent() == null) {
                check(e, i);
            } else {
                if (flag[Define.PERMANENT] == EMPTY)
                    flag[Define.PERMANENT] = i;
            }
        }
    }

    private void check(Event e, int position) {

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, yyyy-MM-dd", locale);
            Date date = simpleDateFormat.parse(Objects.requireNonNull(Define.checkDate(e)));
            Calendar calendar = Calendar.getInstance();
            Calendar eventCalendar = Calendar.getInstance();
            eventCalendar.setTime(Objects.requireNonNull(date));
            calendar.setTime(currentTime);
            int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
            int eventDay = eventCalendar.get(Calendar.DAY_OF_YEAR);
            int eventWeek = eventCalendar.get(Calendar.WEEK_OF_YEAR);
            int eventMonth = eventCalendar.get(Calendar.MONTH);
            if (eventDay < currentDay) {
                // do nothing
                Log.d("DAY", "check: ");
            } else if (eventDay == currentDay) {
                if (flag[Define.TODAY] == EMPTY) {
                    flag[Define.TODAY] = position;
                }
            } else if (eventDay == currentDay + 1) {
                if (flag[Define.TOMORROW] == EMPTY) {
                    flag[Define.TOMORROW] = position;
                }
            } else if (eventWeek == currentWeek) {
                if (flag[Define.SAME_WEEK] == EMPTY) {
                    flag[Define.SAME_WEEK] = position;
                }
            } else if (eventWeek == currentWeek + 1) {
                if (flag[Define.NEXT_WEEK] == EMPTY) {
                    flag[Define.NEXT_WEEK] = position;
                }
            } else if (eventMonth == currentMonth) {
                if (flag[Define.SAME_MONTH] == EMPTY) {
                    flag[Define.SAME_MONTH] = position;
                }
            } else {
                if (flag[Define.NEXT_MONTH] == EMPTY) {
                    flag[Define.NEXT_MONTH] = position;
                }
            }


        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    Comparator<Event> comparator = new Comparator<Event>() {
        @Override
        public int compare(Event event, Event t1) {

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, yyyy-MM-dd", locale);
            try {
                Date eventDate = simpleDateFormat.parse(Objects.requireNonNull(Define.checkDate(event)));
                Date t1Date = simpleDateFormat.parse(Objects.requireNonNull(Define.checkDate(t1)));
                return eventDate.compareTo(t1Date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;

        }
    };
}
