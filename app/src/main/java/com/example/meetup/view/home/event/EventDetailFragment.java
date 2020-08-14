package com.example.meetup.view.home.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meetup.R;
import com.example.meetup.databinding.FragmentDetailEventBinding;
import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.ulti.Define;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventDetailFragment extends Fragment {
    private Event event;
    private Category category;
    private Venue venue;
    RecyclerView recyclerView;
    NearEventAdapter adapter;
    Button buttonCanJoin, buttonJoined;
    List<Event> nearList;
    int status;
    private FragmentDetailEventBinding binding;
    EventDetailViewModel viewModel;
    Context context;

    public EventDetailFragment(Event event, Context context) {
        this.context = context;
        this.event = event;

    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_event, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        viewModel = new EventDetailViewModel();
        status = event.getMyStatus();

        this.category = viewModel.getCategory(event.getCategoryId());
        this.venue = viewModel.getVenue(event.getVenueId());
        nearList = new ArrayList<>();
        binding.setCategory(category);
        binding.setEvent(event);
        binding.setVenue(venue);
        buttonCanJoin = binding.btnCanJoin;
        buttonJoined = binding.btnJoined;
        recyclerView = binding.rvEventNear;
        setUpRecyclerView();
        setWithStatus(status);

        adapter.setOnItemClickListener(new NearEventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                FragmentManager fm;
                fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                EventDetailFragment fragment = new EventDetailFragment(nearList.get(position), context);
                fragmentTransaction.replace(R.id.activity, fragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate();
            }
        });

        mapData(context);

        buttonJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Joined",Toast.LENGTH_SHORT).show();
                status = Define.STATUS_WENt;
                viewModel.eventsRepository.updateEvent(status,event.getId());
                setWithStatus(status);
            }
        });

        buttonCanJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Can Join",Toast.LENGTH_SHORT).show();
                status = Define.STATUS_GOING;
                viewModel.eventsRepository.updateEvent(status,event.getId());
                setWithStatus(status);
            }
        });

        return binding.getRoot();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        nearList = viewModel.getEventNearList();
        adapter = new NearEventAdapter(nearList, getContext());
        recyclerView.setAdapter(adapter);
    }

    private void mapData(Context context) {
        Glide.with(context).load(event.getPhoto()).into(binding.ivImageDetail);
        binding.tvAmount.setText(event.getGoingCount() + " sẽ tham gia");
        binding.tvTime.setText(Define.checkDate(event));
    }
    @SuppressLint("ResourceAsColor")
    private void setWithStatus(int status){
        if (status == Define.STATUS_GOING) {
            buttonCanJoin.setSelected(false);
            buttonCanJoin.setEnabled(false);
            buttonJoined.setSelected(true);
            buttonJoined.setEnabled(true);
        } else if (status == Define.STATUS_WENt) {
            buttonCanJoin.setSelected(false);
            buttonJoined.setSelected(false);
            buttonCanJoin.setEnabled(false);
            buttonJoined.setEnabled(false);
        } else {
            buttonCanJoin.setSelected(true);
            buttonJoined.setSelected(false);
            buttonCanJoin.setEnabled(true);
            buttonJoined.setEnabled(false);
        }
    }
}