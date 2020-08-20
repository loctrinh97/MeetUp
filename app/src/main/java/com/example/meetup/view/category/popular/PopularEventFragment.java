package com.example.meetup.view.category.popular;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentEventsBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.view.category.popular.PopularViewModel;
import com.example.meetup.view.home.event.BottomDialogFragment;
import com.example.meetup.view.home.event.DialogLogin;
import com.example.meetup.view.home.event.EventAdapter;
import com.example.meetup.view.home.event.EventDetailFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PopularEventFragment extends Fragment {
    private RecyclerView recyclerView;
    public PopularViewModel viewModel;
    List<Event> eventList;
    EventAdapter adapter;
    int categoryId;
    int pageSize;
    private FragmentEventsBinding binding;
    public SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences(Define.PRE_TOKEN, Context.MODE_PRIVATE);
    public PopularEventFragment(int categoryId){
        this.categoryId = categoryId;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);
        viewModel = new ViewModelProvider(getParentFragment()).get(PopularViewModel.class);
        eventList = new ArrayList<>();
        pageSize = Define.PAGE_SIZE_DEFAULT;
        recyclerView = binding.recyclerEvents;
        setUpRecyclerView();
        final Observer<List<Event>> eventObserver = new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventList) {
                adapter.setEventList(eventList);
            }
        };
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showDetailEvent(position);
            }

            @Override
            public void onClickJoin(int position) {
                joinCheck(position);

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    pageSize += Define.PAGE_SIZE_DEFAULT;
                    eventList = viewModel.getEventList(pageSize,categoryId); }
            }
        });
        viewModel.getList().observe(getViewLifecycleOwner(), eventObserver);
        binding.setLifecycleOwner(this);
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventList = viewModel.getEventList(Define.PAGE_SIZE_DEFAULT,categoryId);
                binding.swipe.setRefreshing(false);
            }
        });
        return binding.getRoot();
    }

    protected void showDetailEvent(int position) {
        FragmentManager fm  = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        EventDetailFragment fragment = new EventDetailFragment(eventList.get(position),getContext(),viewModel);
        fragmentTransaction.replace(R.id.activity,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    protected void joinCheck(final int position) {
        String token = sharedPref.getString(Define.TOKEN, "");
        if (token.isEmpty()) {
            DialogLogin dialogLogin = new DialogLogin();
            dialogLogin.showDialog(getActivity());

        } else {
            BottomDialogFragment bottomDialog = new BottomDialogFragment(eventList.get(position).getMyStatus());
            bottomDialog.show(getChildFragmentManager(),"");
            bottomDialog.setBottomListener(new BottomDialogFragment.BottomListener() {
                @Override
                public void onBottomItemClicked(int status) {
                    viewModel.updateEvent(status,eventList.get(position).getId());
                    eventList = viewModel.getEventList(pageSize,categoryId);

                }
            });
        }

    }

    protected void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        eventList = viewModel.getEventList(pageSize,categoryId);
        adapter = new EventAdapter(eventList, getContext());
        recyclerView.setAdapter(adapter);
    }
}
