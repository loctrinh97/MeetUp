package com.example.meetup.view.home.event;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.meetup.R;
import com.example.meetup.ulti.Define;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomDialogFragment extends BottomSheetDialogFragment {
    private BottomListener bottomListener;
    TextView tvCanJoin;
    ImageView ivCanJoin;
    TextView tvJoined;
    ImageView ivJoined;
    TextView tvNotJoin;
    ImageView ivNotJoin;
    private int status=0;

    public BottomDialogFragment(int status) {
        this.status = status;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        tvCanJoin = view.findViewById(R.id.tvCanJoin);
        ivCanJoin = view.findViewById(R.id.ivTickCanJoin);
        tvJoined = view.findViewById(R.id.tvJoined);
        ivJoined = view.findViewById(R.id.ivTickJoined);
        tvNotJoin = view.findViewById(R.id.tvNotJoin);
        ivNotJoin = view.findViewById(R.id.ivTickNotJoin);
        if(status== Define.STATUS_DEFAULT){
            setNotJoin();
        }else if(status==Define.STATUS_GOING){
            setCanJoin();
        }else {
            setJoined();
        }
        tvCanJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomListener.onBottomItemClicked(1);
                setCanJoin();
            }
        });
        tvJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomListener.onBottomItemClicked(2);
                setJoined();
            }
        });
        tvNotJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomListener.onBottomItemClicked(0);
                setNotJoin();
            }
        });
        return view;
    }

    public interface BottomListener {
        void onBottomItemClicked(int status);
    }

    public void setBottomListener(BottomListener listener) {
        this.bottomListener = listener;
    }

    private void setCanJoin() {
        ivCanJoin.setVisibility(View.VISIBLE);
        ivNotJoin.setVisibility(View.INVISIBLE);
        ivJoined.setVisibility(View.INVISIBLE);
    }

    private void setJoined() {
        ivJoined.setVisibility(View.VISIBLE);
        ivNotJoin.setVisibility(View.INVISIBLE);
        ivCanJoin.setVisibility(View.INVISIBLE);
    }

    private void setNotJoin() {
        ivJoined.setVisibility(View.INVISIBLE);
        ivNotJoin.setVisibility(View.VISIBLE);
        ivCanJoin.setVisibility(View.INVISIBLE);
    }
}
