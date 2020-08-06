package com.example.meetup.view.personal.joined;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.R;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.view.adapter.JoinedAdapter;

import java.util.ArrayList;
import java.util.List;

public class JoinedFragment extends Fragment {
    private RecyclerView mRecyclerJoined;
    private ArrayList<News> mJoinedList;
    private JoinedAdapter mJoinedAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_joined,container,false);
        mRecyclerJoined = view.findViewById(R.id.rv_Joined);
        mJoinedList = new ArrayList<>();
        createJoinedList();
        mJoinedAdapter = new JoinedAdapter(this,mJoinedList);
        mRecyclerJoined.setAdapter(mJoinedAdapter);
        mRecyclerJoined.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void createJoinedList() {
        mJoinedList.add(new News("toto","test","test","test","test","test"));
    }
}
