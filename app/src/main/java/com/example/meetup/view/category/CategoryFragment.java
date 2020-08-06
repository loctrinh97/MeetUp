package com.example.meetup.view.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.meetup.R;

public class CategoryFragment extends Fragment implements View.OnClickListener {
    ImageView ivSearch;
    TextView tvHeader;
    EditText edtCategorySearch;
    ImageView ivBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);
        ivSearch = view.findViewById(R.id.ivSearch);
        tvHeader = view.findViewById(R.id.tvHeader);
        edtCategorySearch = view.findViewById(R.id.edtCategorySearch);
        ivBack = view.findViewById(R.id.ivBack);
        ivSearch.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        return view;
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
