package com.example.meetup.view.home.event;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meetup.R;
import com.example.meetup.databinding.FragmentEventsBinding;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.repository.CategoryRepository;
import com.example.meetup.ulti.Define;
import com.example.meetup.ulti.MyApplication;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EventsFragment extends Fragment{
    private RecyclerView recyclerView;
    EventViewModel eventViewModel;
    List<Event> eventList;
    EventAdapter adapter;
    int pageSize;
    private FragmentEventsBinding binding;
    public SharedPreferences sharedPref = MyApplication.getAppContext()
            .getSharedPreferences("tokenPref", Context.MODE_PRIVATE);
    CategoryRepository repository = CategoryRepository.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        eventList = new ArrayList<>();
        pageSize = Define.PAGE_SIZE_DEFAULT;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);
        eventViewModel = new ViewModelProvider(getParentFragment()).get(EventViewModel.class);
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
                FragmentManager fm  = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                EventDetailFragment fragment = new EventDetailFragment(eventList.get(position),getContext());
                fragmentTransaction.replace(R.id.activity,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
                    pageSize += 10;
                    eventList = eventViewModel.getEventList(pageSize);
                    adapter.setEventList(eventList);

                }
            }
        });
        eventViewModel.getList().observe(getViewLifecycleOwner(), eventObserver);
        binding.setLifecycleOwner(this);
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventList = eventViewModel.getEventList(Define.PAGE_SIZE_DEFAULT);
                binding.swipe.setRefreshing(false);
            }
        });
        return binding.getRoot();
    }

    private void joinCheck(final int position) {
        String token = sharedPref.getString("token", "null");
        if (token.equalsIgnoreCase("null")) {
            DialogLogin dialogLogin = new DialogLogin();
            dialogLogin.showDialog(getActivity());

        } else {
            BottomDialogFragment bottomDialog = new BottomDialogFragment(eventList.get(position).getMyStatus());
            bottomDialog.show(getChildFragmentManager(),"");
            bottomDialog.setBottomListener(new BottomDialogFragment.BottomListener() {
                @Override
                public void onBottomItemClicked(int status) {
                    eventViewModel.updateEvent(status,eventList.get(position).getId());
                    eventList = eventViewModel.getEventList(pageSize);
                    adapter.setEventList(eventList);
                }
            });
        }

    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        eventList = eventViewModel.getEventList(pageSize);
        adapter = new EventAdapter(eventList, getContext());
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public void onBottonItemClicked(int position) {
//        Log.d("Bottom", "onBottonItemClicked: "+position);
//    }
}