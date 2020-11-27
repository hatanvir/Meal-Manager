package com.trinoq.mealmanager.features.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.adapters.NotificationListAdapter;
import com.trinoq.mealmanager.features.model.pojo.response.invitation.Inviationdatum;
import com.trinoq.mealmanager.features.model.pojo.response.invitation.Invitation;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;

    public NotificationFragment() {
        // Required empty public constructor
    }

    KProgressHUD progressHUD;

    List<Inviationdatum>notificationList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.notificationRv);
        notificationList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressHUD =  KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        getNotificationData();
        return view;
    }

    private void getNotificationData() {
        progressHUD.show();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Api api = RetrofitClient.getClient().create(Api.class);

        int gpId = sharedPreferences.getInt("GroupId",0);
        int userId =  sharedPreferences.getInt("UserId",0);

        api.getInvitationData(String.valueOf(userId))
                .enqueue(new Callback<Invitation>() {
                    @Override
                    public void onResponse(Call<Invitation> call, Response<Invitation> response) {
                        if(response.code() == 200){
                        /*    String senderId = response.body().getInviationdata().
                            if()
                            recyclerView.setAdapter(new NotificationListAdapter(getActivity(),response.body().getInviationdata(),));*/
                            String rvViewType="";

                            for (Inviationdatum i:response.body().getInviationdata()){
                                String senderId = i.getSenderId().toString();
                                String receiverId = i.getReceiverId().toString();

                                if(senderId.equals("3")){
                                    rvViewType = "sender";
                                }else rvViewType = "receiver";

                                notificationList.add(i);
                                recyclerView.setAdapter(new NotificationListAdapter(getActivity(),notificationList,rvViewType));
                            }

                          //  Toast.makeText(getActivity(), ""+notificationList.size(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                        }
                        progressHUD.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Invitation> call, Throwable t) {
                        Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressHUD.dismiss();
                    }
                });
    }
}
