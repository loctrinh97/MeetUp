package com.example.meetup.view.home.event;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;

public class EventDetailFragment extends Fragment {
       private Event event;
       private Category category;
       private Venue venue;
       private Context mContext;

        public EventDetailFragment(Event event,Category category,Venue venue,Context context){
            this.event  = event;
            this.category  =category;
            this.venue = venue;
            mContext = context;
        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return null;
    }
}
