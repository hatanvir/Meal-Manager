package com.trinoq.mealmanager.features.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.view.Activity.GroupCreateActivity;
import com.trinoq.mealmanager.features.view.Activity.GroupMemberSearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeFragment extends Fragment {

    @BindView(R.id.logOut)
    TextView logOut;
    @BindView(R.id.groupSearchLayout)
    LinearLayout groupSearchLayout;
    @BindView(R.id.createGroupLayout)
    LinearLayout createGroupLayout;
    @BindView(R.id.createGroupTv)
    TextView createGroupTv;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, v);

        groupSearchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GroupMemberSearchActivity.class));
            }
        });
        createGroupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GroupCreateActivity.class));
            }
        });
        return v;
    }
}
