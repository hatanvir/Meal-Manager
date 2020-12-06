package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.models.GroupInformation;
import com.trinoq.mealmanager.features.view.Activity.GroupMemberSearchActivity;
import com.trinoq.mealmanager.features.view.Activity.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CongrastFragment extends Fragment {

    @BindView(R.id.searchMemberTv)
    TextView searchMemberTv;

    @BindView(R.id.searchMemberTvLay)
    LinearLayout searchMemberTvLay;

    @BindView(R.id.skipTv)
    TextView skipTv;

    @BindView(R.id.nextImageBt)
    ImageView nextImageBt;

    public CongrastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_congrast, container, false);
        ButterKnife.bind(this,view);

        nextImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TestActivity.class));
                getActivity().finish();
            }
        });

        searchMemberTvLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GroupMemberSearchActivity.class));
            }
        });

        return view;
    }
}