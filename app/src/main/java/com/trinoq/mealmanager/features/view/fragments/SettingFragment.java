package com.trinoq.mealmanager.features.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.models.UserInformation;
import com.trinoq.mealmanager.features.model.pojo.request.User;
import com.trinoq.mealmanager.features.model.pojo.request.UserInformationRequest;
import com.trinoq.mealmanager.features.view.Activity.AboutActivity;
import com.trinoq.mealmanager.features.view.Activity.AccountActivity;
import com.trinoq.mealmanager.features.view.Activity.AuthenticationActivity;
import com.trinoq.mealmanager.features.view.Activity.GeneralActivity;
import com.trinoq.mealmanager.features.view.Activity.NotificationActivity;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingFragment extends Fragment {

    @BindView(R.id.circleImageView)
    CircleImageView profileImage;
    @BindView(R.id.userNameTv)
    TextView userNameTv;
    @BindView(R.id.userPhoneNumberTv)
    TextView phoneNumberTv;
    @BindView(R.id.generallinearLayout)
    LinearLayout generalLinearLayout;
    @BindView(R.id.accountlinearLayout)
    LinearLayout accountLinearLayout;
    @BindView(R.id.notificationlinearLayout)
    LinearLayout notificationLinearLayout;
    @BindView(R.id.aboutlinearLayout)
    LinearLayout aboutLinearLayout;
    @BindView(R.id.logoutTv)
    TextView logoutTv;

    FirebaseAuth firebaseAuth;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this,view);

        firebaseAuth=FirebaseAuth.getInstance();
        logoutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), AuthenticationActivity.class));
            }
        });

       userNameTv.setText(Utils.userInformations.get(0).getUserName());
       phoneNumberTv.setText(Utils.userInformations.get(0).getPhoneNumber());

        Log.d("IIIIMMM",Utils.userInformations.get(0).getImage());
        Picasso.get().load(Utils.IMAGE_BASE_URL+Utils.userInformations.get(0).getImage()).resize(400,400).centerCrop().into(profileImage);

        generalLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),GeneralActivity.class));

            }
        });
        accountLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountActivity.class));
            }
        });
        notificationLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });
        aboutLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });


        return view;
    }
    
}
