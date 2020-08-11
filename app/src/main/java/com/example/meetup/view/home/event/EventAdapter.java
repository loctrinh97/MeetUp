package com.example.meetup.view.home.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    List<Event> eventList ;
    OnItemClickListener listener;
    Context context;
    Date currentTime = Calendar.getInstance().getTime();
    Locale locale = new Locale("vi");
    @SuppressLint("SimpleDateFormat")
    DateFormat dateFormat = new SimpleDateFormat("E, yyyy-mm-dd",locale);


    ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0,0,view.getWidth(),view.getHeight()+90,90F);
        }
    };

    public EventAdapter(List<Event> eventList,Context context){
        this.eventList = eventList;
        this.context = context;
    }
    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemEventBinding binding = ItemEventBinding.inflate(layoutInflater);

        return new EventHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event event = eventList.get(position);
        Glide.with(context)
                .load(event.getPhoto())
                .placeholder(R.drawable.error)
                .into(holder.binding.thumbnail);
        holder.binding.thumbnail.setOutlineProvider(viewOutlineProvider);
        holder.binding.thumbnail.setClipToOutline(true);
        if(event.getMyStatus()==Define.STATUS_DEFAULT){
            holder.binding.imageJoin.setVisibility(View.GONE);
        }
        else if(event.getMyStatus()==Define.STATUS_GOING){
            Glide.with(context)
                    .load(R.drawable.ic_can_join)
                    .into(holder.binding.imageJoin);
        }
        else{
            Glide.with(context)
                    .load(R.drawable.ic_joined)
                    .into(holder.binding.imageJoin);
        }
        String date = checkDate(event);
        holder.binding.eventDate.setText(date);
        holder.binding.goingCount.setText(event.getGoingCount()+"");
        holder.bind(event);

    }
    private String checkDate(Event event) {
        if(event.getSchedulePermanent()== null){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {

//                String sDate = event.getScheduleEndDate().replace("-","/");
//                String eDate = event.getScheduleEndDate().replace("-","/");

//                String sDate1="31/12/1998";
//                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);

                Date startDate = simpleDateFormat.parse(event.getScheduleStartDate());
                Date endDate = simpleDateFormat.parse(event.getScheduleEndDate());

                if(currentTime.compareTo(startDate)<0){
                    return dateFormat.format(startDate);
                }
                else return dateFormat.format(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
           return null;

        }
        else {
            return Define.EVENT_STATUS_PERMANENT;
        }



    }
    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    public class EventHolder extends RecyclerView.ViewHolder{
        private ItemEventBinding binding;


        public EventHolder(ItemEventBinding binding) {
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
        public void bind(Event event){
            binding.setEvent(event);
            binding.executePendingBindings();
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
    };
}
