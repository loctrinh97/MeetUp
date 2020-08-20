package com.example.meetup.view.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.R;
import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.view.home.event.EventDetailFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryFragment extends Fragment implements View.OnClickListener {
    private ImageView ivSearch;
    private TextView tvHeader;
    private EditText edtCategorySearch;
    private ImageView ivBack;
    private  RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private CategoryViewModel viewModel;
    List<Category> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);
        recyclerView = view.findViewById(R.id.rvCategories);
        viewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(CategoryViewModel.class);
        list = new ArrayList<>();
        setUpRecyclerView();
        ivSearch = view.findViewById(R.id.ivSearch);
        tvHeader = view.findViewById(R.id.tvHeader);
        edtCategorySearch = view.findViewById(R.id.edtCategorySearch);
        ivBack = view.findViewById(R.id.ivBack);
        adapter.setOnItemListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("Category", "onItemClick: "+position);
                FragmentManager fm  = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                EventByCategoryFragment fragment = new EventByCategoryFragment(list.get(position));
                fragmentTransaction.replace(R.id.activity,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        ivSearch.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        return view;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        list = viewModel.getList();
        adapter = new CategoryAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(ivSearch)){
            ivSearch.setVisibility(View.GONE);
            tvHeader.setVisibility(View.GONE);
            edtCategorySearch.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.VISIBLE);
        }
        if (v.equals(ivBack)){
            ivSearch.setVisibility(View.VISIBLE);
            tvHeader.setVisibility(View.VISIBLE);
            edtCategorySearch.setVisibility(View.GONE);
            ivBack.setVisibility(View.GONE);
        }
    }


}
