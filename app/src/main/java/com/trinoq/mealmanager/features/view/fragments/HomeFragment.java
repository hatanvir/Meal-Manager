package com.trinoq.mealmanager.features.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.trinoq.mealmanager.features.model.models.BazarListInformation;
import com.trinoq.mealmanager.features.model.models.DailyMealInputEndTime;
import com.trinoq.mealmanager.features.model.models.GroupAllMembersInformation;
import com.trinoq.mealmanager.features.model.pojo.request.BazarListRequest;
import com.trinoq.mealmanager.features.model.pojo.request.Bazarlist;
import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputTimeRequest;
import com.trinoq.mealmanager.features.model.pojo.request.Dailymealinputdatum;
import com.trinoq.mealmanager.features.model.pojo.request.DateMeal;
import com.trinoq.mealmanager.features.model.pojo.request.Payable;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesUpdateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.UserMealCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.UserTotalMealRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMember;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMemberSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.Userinfo;
import com.trinoq.mealmanager.features.model.pojo.response.PayablesResponse;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.text.ParseException;
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

import static android.content.Context.MODE_PRIVATE;

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
    @BindView(R.id.mealNameTv)
    TextView mealNameTv;
    @BindView(R.id.totalMealTv)
    TextView totalUserMealTv;
    @BindView(R.id.totalPaidTv)
    TextView userTotalPaidTv;
    @BindView(R.id.mealRateTv)
    TextView mealReatTv;
    @BindView(R.id.totalconsumedTv)
    TextView totalConsumedTv;
   /* @BindView(R.id.totalDueTv)
    TextView totalDueTv;*/
    @BindView(R.id.totalRemainingTv)
    TextView totalRemainingTv;

    String electricity,others,meal,houserent;

    private Runnable runnable;
    private Handler handler = new Handler();
    Api api;
    Retrofit retrofit;
    BazarListInformation bazarListInformation;
    String fromdate;
    String todate;
    //private String userId,breakfastTime,lunchTime,dinnerTime;
    private int userTotalBazar=0,groupTotalBazar=0,totaldue,totalRemaining;
    double mealRate,totalConsumed;
    SharedPreferences sharedPreferences;
    SharedPreferences myPreferences;
    int groupId,userId;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        retrofit= RetrofitClient.getClient();
        api=retrofit.create(Api.class);

        myPreferences=getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        userId=myPreferences.getInt("UserId",0);
        groupId=myPreferences.getInt("GroupId",0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date current_date = new Date();
        Log.d("IIIII",String.valueOf(userId)+"   "+String.valueOf(groupId));

        fromdate=String.valueOf(dateFormat.format(current_date)+"-01");
        todate=String.valueOf(dateFormat.format(current_date)+"-31");

        getGroupAllMember();
        showBazarList();
        getMealInputEndTime();

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

        /*addMealBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date current_date = new Date();
               // Call<ResponseBody> call=api.setUserMeall(new UserMealCreateRequest("2",Utils.userInformations.get(0).getUserId(),"01747477690",dateFormat.format(current_date),mealnumberTv.getText().toString(),mealNameTv.getText().toString()));
                Call<ResponseBody> call=api.setUserMeall(new UserMealCreateRequest(String.valueOf(groupId),Utils.userInformations.get(0).getUserId(),"01747477690",dateFormat.format(current_date),Integer.parseInt(mealnumberTv.getText().toString()),mealNameTv.getText().toString()));

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
*/

        Call<PayablesResponse> PayablesResponseCall=api.GetPayables(String.valueOf(groupId));
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
    private void getUserMeal(int groupTotalBazar,int userTotalBazar){
        final int[] totalmeal = {0};
        final  int[] totalUserMeals={0};

        final Call<UserTotalMealRequest> userTotalMeal = api.getUserMeal(String.valueOf(groupId), fromdate, todate);
        userTotalMeal.enqueue(new Callback<UserTotalMealRequest>() {
            @Override
            public void onResponse(Call<UserTotalMealRequest> call, Response<UserTotalMealRequest> response) {
                if (response.code()==200){
                    UserTotalMealRequest userTotalMealRequest=response.body();
                    if (userTotalMealRequest.getDateMeal().size()>0){
                        for (DateMeal dateMeal:userTotalMealRequest.getDateMeal()) {

                                totalmeal[0]=totalmeal[0]+dateMeal.getIsBreakfast()+dateMeal.getIsDinner();

                            if (!String.valueOf(dateMeal.getIsLunch()).equals("null")){
                                totalmeal[0]=totalmeal[0]+dateMeal.getIsLunch();
                            }
                            if (dateMeal.getUserId()==userId) {
                                totalUserMeals[0] = totalUserMeals[0]+dateMeal.getIsBreakfast()+dateMeal.getIsDinner();
                                if (!String.valueOf(dateMeal.getIsLunch()).equals("null")){
                                    totalUserMeals[0]=totalUserMeals[0]+dateMeal.getIsLunch();
                                }
                            }

                        }

                    }
                    mealRate=Double.valueOf(groupTotalBazar)/totalmeal[0];
                    mealReatTv.setText(String.format("%.2f",mealRate)+"/-");
                    totalConsumed= mealRate*totalUserMeals[0];
                    totaldue= (int) (totalConsumed-userTotalBazar);

                    totalConsumedTv.setText(String.format("%.2f",totalConsumed)+"/-");
                    totalRemainingTv.setText(String.valueOf(totaldue)+"/-");

                    totalUserMealTv.setText(String.valueOf(totalUserMeals[0]));
                    Log.d("MMMM",String.valueOf(totalmeal[0])+"  "+String.valueOf(totalUserMeals[0]));
                }
            }

            @Override
            public void onFailure(Call<UserTotalMealRequest> call, Throwable t) {

            }
        });

    }
    private void setDailyMeal(){
        addMealBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date current_date = new Date();
                // Call<ResponseBody> call=api.setUserMeall(new UserMealCreateRequest("2",Utils.userInformations.get(0).getUserId(),"01747477690",dateFormat.format(current_date),mealnumberTv.getText().toString(),mealNameTv.getText().toString()));
                Call<ResponseBody> call=api.setUserMeall(new UserMealCreateRequest(String.valueOf(groupId),Utils.userInformations.get(0).getUserId(),"01747477690",dateFormat.format(current_date),Integer.parseInt(mealnumberTv.getText().toString()),mealNameTv.getText().toString()));

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

    }

    private void getMealInputEndTime() {
        Call<DailyMealInputTimeRequest> dailyMealInputTimeRequestCall=api.getMealEndTime(String.valueOf(groupId));
        dailyMealInputTimeRequestCall.enqueue(new Callback<DailyMealInputTimeRequest>() {
            @Override
            public void onResponse(Call<DailyMealInputTimeRequest> call, Response<DailyMealInputTimeRequest> response) {
                if (response.code()==200){

                    DailyMealInputTimeRequest mealInputTimeRequest=response.body();

                    if (mealInputTimeRequest.getDailymealinputdata().size()>0){
                            for (Dailymealinputdatum dailymealinputdatum:mealInputTimeRequest.getDailymealinputdata()){

                                DailyMealInputEndTime dailyMealInputEndTime=new DailyMealInputEndTime(dailymealinputdatum.getBreakfastDateTime(),dailymealinputdatum.getLunchDateTime(),dailymealinputdatum.getDinnerDateTime());

                                Utils.dailyMealInputEndTimeinfo.add(dailyMealInputEndTime);
                        }
                        String[] breakfastTime=Utils.dailyMealInputEndTimeinfo.get(0).getBreakfastTime().split(" ");
                        String[] lunchTime=Utils.dailyMealInputEndTimeinfo.get(0).getLunchTime().split(" ");
                        String[] dinnerTime=Utils.dailyMealInputEndTimeinfo.get(0).getDinnerTime().split(" ");
                            setTimer(breakfastTime[1],lunchTime[1],dinnerTime[1]);

                    }
                }

            }

            @Override
            public void onFailure(Call<DailyMealInputTimeRequest> call, Throwable t) {

            }
        });
    }
    private void setTimer(String breakfastTime, String lunchTime, String dinnerTime){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date current_date = new Date();
            Date breakfast_Time = dateFormat.parse(dateFormat1.format(current_date)+" "+breakfastTime);
            Date lunch_Time = dateFormat.parse(dateFormat1.format(current_date)+" "+lunchTime);
            Date dinner_Time = dateFormat.parse(dateFormat1.format(current_date)+" "+dinnerTime);
            if (!current_date.after(breakfast_Time)){
                mealNameTv.setText("Breakfast");
                countDownStart(breakfast_Time);
                setDailyMeal();
            }
            else if (!current_date.after(lunch_Time)){
                mealNameTv.setText("Lunch");
                countDownStart(lunch_Time);
                setDailyMeal();
            }
            else if (!current_date.after(dinner_Time)){
                mealNameTv.setText("Dinner");
                countDownStart(dinner_Time);
                setDailyMeal();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void getGroupAllMember() {

        Utils.groupAllMembersInformations.clear();

        Call<GroupMemberSearchRequest> groupMemberSearchRequestCall=api.getAllGroupMember(String.valueOf(groupId));
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

                Call<ResponseBody> call=api.updatePayable(String.valueOf(groupId),new PayablesUpdateRequest(String.valueOf(groupId),electricityEt.getText().toString(),otherEt.getText().toString(),mealEt.getText().toString(),houserentEt.getText().toString()));

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

    private void showBazarList() {

        Log.d("FAFAFA",""+fromdate+"  "+todate);
        Call<BazarListRequest> call=api.getBazarList(String.valueOf(groupId),fromdate,todate);
        call.enqueue(new Callback<BazarListRequest>() {
            @Override
            public void onResponse(Call<BazarListRequest> call, Response<BazarListRequest> response) {
                if (response.code()==200){
                    Utils.bazarListInformations.clear();
                    BazarListRequest bazarListRequest=response.body();

                    if (bazarListRequest.getBazarlist().size()>0){
                        for (Bazarlist bazarlist:bazarListRequest.getBazarlist()){

                            bazarListInformation=new BazarListInformation(bazarlist.getId(),
                                    bazarlist.getGroupId(),bazarlist.getUserId(),bazarlist.getTotalAmount(),bazarlist.getDate()
                                    ,bazarlist.getCreatedAt(),bazarlist.getUpdatedAt());

                            Utils.bazarListInformations.add(bazarListInformation);
                            Utils.count++;
                            groupTotalBazar=groupTotalBazar+bazarlist.getTotalAmount();
                            Log.d("BBBBB",String.valueOf(userId)+bazarlist.getUserId());
                            if (userId==bazarlist.getUserId()){
                                userTotalBazar=userTotalBazar+bazarlist.getTotalAmount();
                            }
                        }
                        getUserMeal(groupTotalBazar,userTotalBazar);
                        userTotalPaidTv.setText(String.valueOf(userTotalBazar)+"/-");
                    }

                }
            }

            @Override
            public void onFailure(Call<BazarListRequest> call, Throwable t) {

            }
        });
    }


    public void countDownStart(Date end_Time) {

        runnable = new Runnable() {

            @Override
            public void run() {
                try {

                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date event_date = dateFormat.parse("2020-11-26 23:53:25");
                    Date current_date = new Date();
                    if (!current_date.after(end_Time)) {

                        long diff = end_Time.getTime() - current_date.getTime();
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