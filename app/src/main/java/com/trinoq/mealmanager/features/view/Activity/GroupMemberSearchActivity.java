package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.adapters.GroupMembersListRecyclerViewAdapter;
import com.trinoq.mealmanager.features.model.pojo.request.Admininfo;
import com.trinoq.mealmanager.features.model.pojo.request.Member;
import com.trinoq.mealmanager.features.model.pojo.request.MemberSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMember;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMemberSearchRequest;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GroupMemberSearchActivity extends AppCompatActivity {

    @BindView(R.id.phoneNumberEt)
    EditText phoneNumberEt;
    @BindView(R.id.membersList)
    RecyclerView membersListRc;
    @BindView(R.id.clearBt)
    ImageView clearBt;
    @BindView(R.id.backBt)
    ImageView backImage;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManagergroupname;

    ArrayList<String> phoneNumber=new ArrayList<>();
    ArrayList<String> userName=new ArrayList<>();

    private KProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences myPreferences=getSharedPreferences("MyPreferences",MODE_PRIVATE);
        if (myPreferences.getString("theme","").equals("true")){
            setTheme(R.style.LightTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_group_member_search);
        ButterKnife.bind(GroupMemberSearchActivity.this);



        progressHUD =  KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        Retrofit retrofit= RetrofitClient.getClient();
        Api api=retrofit.create(Api.class);

        membersListRc.setHasFixedSize(true);
        layoutManagergroupname=new LinearLayoutManager(this);
        membersListRc.setLayoutManager(layoutManagergroupname);

        phoneNumberEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBt.setVisibility(View.VISIBLE);
            }
        });
        clearBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumberEt.setText(null);
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        phoneNumberEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_GO || i == EditorInfo.IME_ACTION_DONE||i==EditorInfo.IME_ACTION_SEARCH) {
                    phoneNumber.clear();
                    userName.clear();
                    progressHUD.show();
                    Call<MemberSearchRequest> memberSearchRequestCall=api.getSearchMember(phoneNumberEt.getText().toString().trim());
                    memberSearchRequestCall.enqueue(new Callback<MemberSearchRequest>() {
                        @Override
                        public void onResponse(Call<MemberSearchRequest> call, Response<MemberSearchRequest> response) {
                           // Log.d("OOO",response.body().getMember().toString());
                            if (response.code()==200){
                                Log.d("OOO",response.body().getMember().toString());
                                MemberSearchRequest groupRequest=response.body();

                                if (groupRequest.getMember().size()>0){
                                   /* for (Member groupMember:groupRequest.getMember())
                                    {
                                       *//* try {

                                            phoneNumber.add(groupMember.getPhoneNumber());
                                            Log.d("GGG",groupMember.getPhoneNumber());
                                            Toast.makeText(textView.getContext(), "Call", Toast.LENGTH_SHORT).show();

                                        }
                                        catch (Exception e){

                                        }*//*

                                       for (Admininfo admininfo:groupMember.getAdmininfo()){
                                           phoneNumber.add(admininfo.getPhoneNumber());
                                           userName.add(admininfo.getFullName());
                                           Toast.makeText(textView.getContext(), "Call", Toast.LENGTH_SHORT).show();
                                       }
                                    }*/

                                    mAdapter=new GroupMembersListRecyclerViewAdapter(GroupMemberSearchActivity.this,groupRequest.getMember());
                                    membersListRc.setAdapter(mAdapter);
                                    // Log.d("FSFS",userName.toString()+"  "+phoneNumber.toString())


                                }
                                progressHUD.dismiss();

;                            }else {
                                Toast.makeText(GroupMemberSearchActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                                progressHUD.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<MemberSearchRequest> call, Throwable t) {
                            Toast.makeText(GroupMemberSearchActivity.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressHUD.dismiss();
                        }
                    });


                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(phoneNumberEt.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    return true;
                }



                return false;
            }
        });
    }
}
