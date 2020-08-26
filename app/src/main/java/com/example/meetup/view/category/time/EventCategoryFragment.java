package com.example.meetup.view.category.time;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.view.category.search.SearchViewModel;
import com.example.meetup.view.home.event.EventsFragment;

import java.util.List;

public class EventCategoryFragment  extends EventsFragment {
    public int STATUS_END = 0;
    public int STATUS_HAPPEN = 1;
    int status;
    String keyword;
    SearchViewModel mViewModel;
    public EventCategoryFragment(int status,SearchViewModel viewModel,String keyword){
        this.status = status;
        this.mViewModel = viewModel;
        this.keyword = keyword;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void setViewModel() {
        super.eventViewModel = mViewModel;
    }
    @Override
    public void observe() {
        final Observer<List<Event>> eventObserver = new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventList) {
                adapter.setEventList(eventList);
            }
        };
        if(status == STATUS_END){
            mViewModel.getListEnd().observe(getViewLifecycleOwner(),eventObserver);
        }
        else{
            mViewModel.getListHappen().observe(getViewLifecycleOwner(),eventObserver);
        }
    }
    @Override
    protected List<Event> setList(int pageSize) {
        if(status == STATUS_END) {
            return mViewModel.getListEnd(pageSize, keyword);
        }
        else{
            return mViewModel.getListHappen(pageSize,keyword);
        }
    }
}
