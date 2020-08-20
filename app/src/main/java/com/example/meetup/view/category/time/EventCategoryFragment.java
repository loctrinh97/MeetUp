package com.example.meetup.view.category.time;

import com.example.meetup.view.home.event.EventsFragment;

public class EventCategoryFragment  extends EventsFragment {
    public int STATUS_END = 0;
    public int STATUS_HAPPEN = 1;
    int status;
    SearchViewModel mViewModel;
    public EventCategoryFragment(int status,SearchViewModel viewModel){
        this.status = status;
        this.mViewModel = viewModel;
    }
    @Override
    public void setViewModel() {
        super.eventViewModel = mViewModel;
    }

    @Override
    protected void setList(int pageSize) {
        super.eventList = this.eventViewModel.getEventList(pageSize);
    }
}
