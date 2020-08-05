package com.example.meetup.view.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import com.example.meetup.R;
import com.example.meetup.view.adapter.ViewPagerAdapter;
import com.example.meetup.view.personal.canjoin.CanJoinFragment;
import com.example.meetup.view.personal.joined.JoinedFragment;
import com.example.meetup.view.registerlogin.LoginActivity;
import com.example.meetup.view.registerlogin.login.LoginViewModel;
import com.google.android.material.tabs.TabLayout;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView ivLogout;
    LoginViewModel loginViewModel;

    public PersonalFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        viewPager = view.findViewById(R.id.vp_personal);
        tabLayout = view.findViewById(R.id.tl_personal);
        ivLogout = view.findViewById(R.id.ivLogout);
        tabLayout.setupWithViewPager(viewPager);
        ivLogout.setOnClickListener(this);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFrag(new CanJoinFragment(),getString(R.string.can_join));
        viewPagerAdapter.addFrag(new JoinedFragment(),getString(R.string.joined));
        viewPager.setAdapter(viewPagerAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        loginViewModel.clearPrefToken();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}