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

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.adapters.GroupMembersListRecyclerViewAdapter;
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

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManagergroupname;
    ArrayList<String> phoneNumber=new ArrayList<>();

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


        phoneNumberEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_GO || i == EditorInfo.IME_ACTION_DONE||i==EditorInfo.IME_ACTION_SEARCH) {
                    phoneNumber.clear();
                    Call<GroupMemberSearchRequest> groupMemberCall=api.GroupMember(phoneNumberEt.getText().toString().trim());
                    groupMemberCall.enqueue(new Callback<GroupMemberSearchRequest>() {
                        @Override
                        public void onResponse(Call<GroupMemberSearchRequest> call, Response<GroupMemberSearchRequest> response) {
                            if (response.code()==200){
                                //Log.d("OOO",response.body().getGroupMembe);
                                GroupMemberSearchRequest groupRequest=response.body();
                                if (groupRequest.getGroupMembers().size()>0){
                                    for (GroupMember groupMember:groupRequest.getGroupMembers())
                                    {
                                        try {

                                            phoneNumber.add(groupMember.getPhoneNumber());
                                            Log.d("GGG",groupMember.getPhoneNumber());
                                            Toast.makeText(textView.getContext(), "Call", Toast.LENGTH_SHORT).show();

                                        }
                                        catch (Exception e){

                                        }
                                    }


                                }
                                //Log.d("OOO",mealtype.toString());
                                //mAdapter=new GroupListRecyclerViewAdapter(textView.getContext(),groupname,phonenumber,adminName,mealtype,cookingtype,shoppingtype,groupcreated);
                               mAdapter=new GroupMembersListRecyclerViewAdapter(textView.getContext(),phoneNumber);
                                membersListRc.setAdapter(mAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<GroupMemberSearchRequest> call, Throwable t) {

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
