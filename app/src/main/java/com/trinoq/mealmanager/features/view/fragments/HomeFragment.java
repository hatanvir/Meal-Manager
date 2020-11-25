package com.trinoq.mealmanager.features.view.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.models.GroupAllMembersInformation;
import com.trinoq.mealmanager.features.model.pojo.request.Payable;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesUpdateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.UserMealCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMember;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMemberSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.Userinfo;
import com.trinoq.mealmanager.features.model.pojo.response.PayablesResponse;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    @BindView(R.id.decrementImageBt)
    ImageButton decrementImageBt;
    @BindView(R.id.incrementImageBt)
    ImageButton incrementImageBt;
    @BindView(R.id.mealNumberTv)
    TextView mealnumberTv;
    @BindView(R.id.timer)
    TextView timer;
    @BindView(R.id.electricityTv)
    TextView electricityTv;
    @BindView(R.id.othersTv)
    TextView othersTv;
    @BindView(R.id.houseRentTv)
    TextView houseRentTv;
    @BindView(R.id.mealTv)
    TextView mealTv;
    @BindView(R.id.totalPayableTv)
    TextView totalPayableTv;
    @BindView(R.id.updatePayable)
    ImageButton updatePayable;
    @BindView(R.id.addMealBt)
    Button addMealBt;

    String electricity,others,meal,houserent;

    private Runnable runnable;
    private Handler handler = new Handler();
    Api api;
    Retrofit retrofit;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        countDownStart();
        retrofit= RetrofitClient.getClient();
        api=retrofit.create(Api.class);

        getGroupAllMember();

        incrementImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n=Integer.parseInt(mealnumberTv.getText().toString());
                //String string=mealnumberTv.getText().toString();
                mealnumberTv.setText(String.valueOf(n+1));

            }
        });
        decrementImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n=Integer.parseInt(mealnumberTv.getText().toString());
                if (n>0)
                    mealnumberTv.setText(String.valueOf(n-1));
                Log.d("HHH","OKKKJJJ");
            }
        });

        updatePayable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updatedPayable();
            }
        });

        addMealBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date current_date = new Date();
                Call<ResponseBody> call=api.setUserMeall(new UserMealCreateRequest("2","01781998168",dateFormat.format(current_date),mealnumberTv.getText().toString(),mealnumberTv.getText().toString(),mealnumberTv.getText().toString()));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("HGGG",String.valueOf(response.code()+"  "+dateFormat.format(current_date)));
                        if (response.code()==200)
                        {
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        Call<PayablesResponse> PayablesResponseCall=api.GetPayables("2");
        PayablesResponseCall.enqueue(new Callback<PayablesResponse>() {
            @Override
            public void onResponse(Call<PayablesResponse> call, Response<PayablesResponse> response) {
                if (response.code()==200){

                    PayablesResponse PayablesResponse=response.body();
                    if (PayablesResponse.getPayables().size()>0){
                        for (Payable payable:PayablesResponse.getPayables()){

                            electricity=payable.getElectricityGasWater().toString();
                            others=payable.getOthers().toString();
                            meal=payable.getMealAdvanced().toString();
                            houserent=payable.getHouseRent().toString();
                            electricityTv.setText(electricity+"/-");
                            othersTv.setText(others+"/-");
                            mealTv.setText(meal+"/-");
                            houseRentTv.setText(houserent+"/-");
                            totalPayableTv.setText(PayablesResponse.getTotalPayables().toString()+"/-");

                            Toast.makeText(getContext(), PayablesResponse.getTotalPayables().toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), payable.getMealAdvanced().toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(),payable.getHouseRent().toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), payable.getOthers().toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), payable.getElectricityGasWater().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<PayablesResponse> call, Throwable t) {

            }
        });



        return view;
    }

    private void getGroupAllMember() {

        Utils.groupAllMembersInformations.clear();

        Call<GroupMemberSearchRequest> groupMemberSearchRequestCall=api.getAllGroupMember("2");
        groupMemberSearchRequestCall.enqueue(new Callback<GroupMemberSearchRequest>() {
            @Override
            public void onResponse(Call<GroupMemberSearchRequest> call, Response<GroupMemberSearchRequest> response) {

                if (response.code()==200){
                    GroupMemberSearchRequest groupMemberSearchRequest=response.body();
                    if (groupMemberSearchRequest.getGroupMembers().size()>0){
                        for (GroupMember groupMember:groupMemberSearchRequest.getGroupMembers()){
                            for (Userinfo userinfo:groupMember.getUserinfo()){

                                Log.d("USER",userinfo.getFullName()+"  "+userinfo.getPhoneNumber()+"  "+userinfo.getId());

                                GroupAllMembersInformation groupAllMembersInformation=new GroupAllMembersInformation(userinfo.getId(),userinfo.getPhoneNumber(),userinfo.getFullName(),userinfo.getEmail());
                                Utils.groupAllMembersInformations.add(groupAllMembersInformation);
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<GroupMemberSearchRequest> call, Throwable t) {

            }
        });

    }

    private void setUserMeal() {


    }

    private void updatedPayable() {
        Dialog dialog=new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_payable);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
        EditText electricityEt=dialog.findViewById(R.id.electricityEt);
        EditText otherEt=dialog.findViewById(R.id.othersEt);
        EditText mealEt=dialog.findViewById(R.id.mealAdvanceEt);
        EditText houserentEt=dialog.findViewById(R.id.houseRentEt);
        Button updateBt=dialog.findViewById(R.id.updateBt);

        electricityEt.setText(electricity);
        otherEt.setText(others);
        mealEt.setText(meal);
        houserentEt.setText(houserent);

        updateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<ResponseBody> call=api.updatePayable("2",new PayablesUpdateRequest("2",electricityEt.getText().toString(),otherEt.getText().toString(),mealEt.getText().toString(),houserentEt.getText().toString()));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Log.d("OOOO",String.valueOf(response.code()));

                        if (response.code() == 200){

                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {

                            Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                       //oi Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });
            }
        });
    }

    public void countDownStart() {



        runnable = new Runnable() {

            @Override
            public void run() {
                try {

                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date event_date = dateFormat.parse("2020-11-29 23:53:25");
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {

                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        /*String timeLeftFormatted = String.format(Locale.getDefault(),
                                "%d:%d:%02d:%02d", Days, Hours, Minutes, Seconds);*/
                        String timeLeftFormatted = String.format(Locale.getDefault(),
                                "%d:%02d", Hours, Minutes);

                        timer.setText(timeLeftFormatted);
                        Log.d("HHH",timeLeftFormatted);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("HHH",e.toString());
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }
}