
package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
import com.trinoq.mealmanager.features.adapters.GroupListRecyclerViewAdapter;
import com.trinoq.mealmanager.features.model.models.GroupInformation;
import com.trinoq.mealmanager.features.model.pojo.request.GroupMember;
import com.trinoq.mealmanager.features.model.pojo.request.SearchGroupRequest;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GroupSearchActivity extends AppCompatActivity {

    RecyclerView grouplistRCV;
    ImageView clearBt;
    EditText groupnameEt;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManagergroupname;
    ArrayList<String> groupname=new ArrayList<>();
    ArrayList<String> phonenumber=new ArrayList<>();
    ArrayList<String> adminName=new ArrayList<>();
    ArrayList<String> mealtype=new ArrayList<>();
    ArrayList<String> cookingtype=new ArrayList<>();
    ArrayList<String> shoppingtype=new ArrayList<>();
    ArrayList<String> groupcreated=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search);

        grouplistRCV=findViewById(R.id.groupList);
        clearBt=findViewById(R.id.clearBt);
        groupnameEt=findViewById(R.id.groupnameEt);

       /* groupname.add("Group One");
        groupname.add("Group  Two");
        groupname.add("Group Three");
        phonenumber.add("01747477690");
        phonenumber.add("01720466530");
        phonenumber.add("01521423159");*/

        grouplistRCV.setHasFixedSize(true);
        layoutManagergroupname=new LinearLayoutManager(this);
        grouplistRCV.setLayoutManager(layoutManagergroupname);


        Retrofit retrofit= RetrofitClient.getClient();
        Api api=retrofit.create(Api.class);
        groupnameEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBt.setVisibility(View.VISIBLE);
            }
        });

        clearBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupnameEt.setText(null);

                /*groupname.clear();
                phonenumber.clear();
                Call<SearchGroupRequest> groupMemberCall=api.Group_search(groupnameEt.getText().toString().trim());
                groupMemberCall.enqueue(new Callback<SearchGroupRequest>() {
                    @Override
                    public void onResponse(Call<SearchGroupRequest> call, Response<SearchGroupRequest> response) {
                        if (response.code()==200){
                            //Log.d("OOO",response.body().getGroupMembe);
                            SearchGroupRequest groupRequest=response.body();
                            if (groupRequest.getGroupMembers().size()>0){
                                for (GroupMember groupMember:groupRequest.getGroupMembers())
                                {
                                    try {
                                        Log.d("OOO",groupMember.getGroupName());
                                        groupname.add(groupMember.getGroupName());
                                        phonenumber.add(groupMember.getPhoneNumber());

                                    }
                                    catch (Exception e){

                                    }
                                }


                            }
                            mAdapter=new GroupListRecyclerViewAdapter(view.getContext(),groupname,phonenumber);
                            grouplistRCV.setAdapter(mAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchGroupRequest> call, Throwable t) {

                    }
                });*/

            }

        });
        groupnameEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Toast.makeText(textView.getContext(), "Call", Toast.LENGTH_SHORT).show();
                if (i == EditorInfo.IME_ACTION_GO || i == EditorInfo.IME_ACTION_DONE||i==EditorInfo.IME_ACTION_SEARCH) {
                    //your functionality
                    groupname.clear();
                    phonenumber.clear();
                    mealtype.clear();
                    Call<SearchGroupRequest> groupMemberCall=api.Group_search(groupnameEt.getText().toString().trim());
                    groupMemberCall.enqueue(new Callback<SearchGroupRequest>() {
                        @Override
                        public void onResponse(Call<SearchGroupRequest> call, Response<SearchGroupRequest> response) {
                            if (response.code()==200){
                                //Log.d("OOO",response.body().getGroupMembe);
                                SearchGroupRequest groupRequest=response.body();
                                if (groupRequest.getGroupMembers().size()>0){
                                    for (GroupMember groupMember:groupRequest.getGroupMembers())
                                    {
                                        try {

                                           /* groupname.add(groupMember.getGroupName());
                                            phonenumber.add(groupMember.getPhoneNumber());
                                            mealtype.add(groupMember.getMealType());
                                            adminName.add(groupMember.getIsAdmin().toString());
                                            cookingtype.add(groupMember.getCooksName().toString());
                                            shoppingtype.add(groupMember.getShoppingType());
                                            groupcreated.add(groupMember.getCreatedAt());*/

                                            GroupInformation groupInformation=new GroupInformation(groupMember.getGroupName(),
                                                    groupMember.getPhoneNumber(),groupMember.getIsAdmin().toString(), "0",
                                                    "null",groupMember.getMealType(),groupMember.getCooksName().toString(),
                                                    groupMember.getShoppingType(),groupMember.getCreatedAt());
                                            Utils.groupInformations.add(groupInformation);
                                            Log.d("OOO",Utils.groupInformations.toString());


                                        }
                                        catch (Exception e){

                                        }
                                    }


                                }
                                Log.d("OOO",Utils.groupInformations.toString());
                                //mAdapter=new GroupListRecyclerViewAdapter(textView.getContext(),groupname,phonenumber,adminName,mealtype,cookingtype,shoppingtype,groupcreated);
                                mAdapter=new GroupListRecyclerViewAdapter(textView.getContext());
                                grouplistRCV.setAdapter(mAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchGroupRequest> call, Throwable t) {

                        }
                    });


                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(groupnameEt.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    return true;
                }



                return false;
            }
        });


    }
}