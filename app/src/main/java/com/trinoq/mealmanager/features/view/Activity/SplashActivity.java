package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.models.ActiveGroupInformation;
import com.trinoq.mealmanager.features.model.models.UserInformation;
import com.trinoq.mealmanager.features.model.pojo.request.ActiveGroup;
import com.trinoq.mealmanager.features.model.pojo.request.ActiveGroupRequest;
import com.trinoq.mealmanager.features.model.pojo.request.ActiveGroupUpdatedRequest;
import com.trinoq.mealmanager.features.model.pojo.request.User;
import com.trinoq.mealmanager.features.model.pojo.request.UserInformationRequest;
import com.trinoq.mealmanager.features.view.fragments.WelcomeFragment;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity {

    Retrofit retrofit;
    Api api;
    ActiveGroupInformation activeGroupInformation;
    ArrayList<String> groupName=new ArrayList<>();
    ArrayList<String> groupId=new ArrayList<>();
    String groupid;
    SharedPreferences.Editor myPreferences;

    SharedPreferences sharedPreferences;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;
    String currentPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(SplashActivity.this);

        retrofit= RetrofitClient.getClient();
        api=retrofit.create(Api.class);

        myPreferences=getSharedPreferences("MyPreferences",MODE_PRIVATE).edit();
        sharedPreferences=getSharedPreferences("MyPreferences",MODE_PRIVATE);

        try{
            currentPhoneNumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
            if(currentPhoneNumber == ""){
                startActivity(new Intent(this,AuthenticationActivity.class));
            }else {
                getCurrentUserInfo();
            }
        }catch (Exception e){
            startActivity(new Intent(this,AuthenticationActivity.class));
        }

      /*  if(currentPhoneNumber == null){
            startActivity(new Intent(this,AuthenticationActivity.class));
        }else {
            getCurrentUserInfo();
        }*/

    }

    private void getCurrentUserInfo() {
        Call<UserInformationRequest> call=api.UserInformation(currentPhoneNumber);
        call.enqueue(new Callback<UserInformationRequest>() {
            @Override
            public void onResponse(Call<UserInformationRequest> call, Response<UserInformationRequest> response) {
                if (response.code()==200){
                    UserInformationRequest informationRequest=response.body();
                    if (informationRequest.getUser().size()>0) {
                        for (User user:informationRequest.getUser()){

                            myPreferences.putInt("UserId",user.getId());

                            //Log.d("IIIII",String.valueOf(user.getActiveGroupid()));
                            if (String.valueOf(user.getActiveGroupid()).equals("null")){
                                getActiveGroup();
                            }
                            else {
                                UserInformation userInformation=new UserInformation(user.getId().toString(),user.getPhoneNumber(),
                                        "null",user.getFullName(),String.valueOf(user.getEmail()),"null",String.valueOf(user.getActiveGroupid()),
                                        user.getCreatedAt(),user.getUpdatedAt());
                                Utils.userInformations.add(userInformation);
                                Utils.useridTest=user.getId().toString();
                                Log.d("GGGGGG",String.valueOf(user.getActiveGroupid()));
                                myPreferences.putInt("GroupId",Integer.parseInt(user.getActiveGroupid()));
                                final Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(2000);

                                            startActivity(new Intent(SplashActivity.this, TestActivity.class));

                                            finish();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                thread.start();
                            }
                            myPreferences.apply();
                            Log.d("FFF",String.valueOf(sharedPreferences.getInt("UserId",0)));

                        }
                    }
                }else {
                    startActivity(new Intent(SplashActivity.this,AuthenticationActivity.class));
                }
            }

            @Override
            public void onFailure(Call<UserInformationRequest> call, Throwable t) {

            }
        });


    }
    private void getActiveGroup(){
        Call<ActiveGroupRequest> activeGroupRequestCall=api.getActiveGroup(currentPhoneNumber);
        activeGroupRequestCall.enqueue(new Callback<ActiveGroupRequest>() {
            @Override
            public void onResponse(Call<ActiveGroupRequest> call, Response<ActiveGroupRequest> response) {
                if (response.code()==200){
                    ActiveGroupRequest activeGroupRequest=response.body();
                    if (activeGroupRequest.getActiveGroup().size()> 0){
                        for (ActiveGroup activeGroup:activeGroupRequest.getActiveGroup()){
                            activeGroupInformation =new ActiveGroupInformation(activeGroup.getId(),activeGroup.getGroupName());
                            groupId.add(String.valueOf(activeGroup.getId()));
                            groupName.add(activeGroup.getGroupName());
                            Utils.activeGroupInformations.add(activeGroupInformation);
                        }
                        Dialog dialog=new Dialog(SplashActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_set_active_group);
                        dialog.getWindow().setGravity(Gravity.BOTTOM);
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        dialog.show();

                        Spinner groupNameSp=dialog.findViewById(R.id.groupNameSp);
                        TextView groupIdTv=dialog.findViewById(R.id.groupIdTv);
                        FloatingActionButton addActivietGroup=dialog.findViewById(R.id.addActivitGroupFab);
                        ArrayAdapter id=new ArrayAdapter(SplashActivity.this,android.R.layout.simple_spinner_item,groupName);
                        id.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        groupNameSp.setAdapter(id);
                        groupNameSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(SplashActivity.this,groupName.get(i)+" = "+ groupId.get(i), Toast.LENGTH_SHORT).show();
                                groupIdTv.setText("Group ID : "+groupId.get(i));
                                groupid=groupId.get(i);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        addActivietGroup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Call<ResponseBody> activeGroupUpdated=api.setActiveGroup(currentPhoneNumber,new ActiveGroupUpdatedRequest(groupid));
                                activeGroupUpdated.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.code()==200){
                                            myPreferences.putInt("GroupId",Integer.parseInt(groupid));
                                            myPreferences.apply();
                                            Toast.makeText(SplashActivity.this, "Seccess", Toast.LENGTH_SHORT).show();

                                            final Thread thread = new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Thread.sleep(2000);

                                                        startActivity(new Intent(SplashActivity.this, TestActivity.class));

                                                        finish();
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                            thread.start();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });
                                dialog.dismiss();
                            }
                        });
                    }
                    else {
                        imageView.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        frameLayout.setVisibility(View.VISIBLE);
                        setFragment(new WelcomeFragment());
                    }
                }
            }

            @Override
            public void onFailure(Call<ActiveGroupRequest> call, Throwable t) {

            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}