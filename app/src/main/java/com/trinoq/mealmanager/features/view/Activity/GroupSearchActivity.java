
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
import com.trinoq.mealmanager.features.adapters.GroupListRecyclerViewAdapter;
import com.trinoq.mealmanager.features.model.models.GroupInformation;
import com.trinoq.mealmanager.features.model.pojo.request.Admin;
import com.trinoq.mealmanager.features.model.pojo.request.GroupSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request.Groupsearch;
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
        SharedPreferences myPreferences=getSharedPreferences("MyPreferences",MODE_PRIVATE);
        if (myPreferences.getString("theme","").equals("true")){
            setTheme(R.style.LightTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }
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

            }

        });
        groupnameEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_GO || i == EditorInfo.IME_ACTION_DONE||i==EditorInfo.IME_ACTION_SEARCH) {
                    //your functionality
                    groupname.clear();
                    phonenumber.clear();
                    mealtype.clear();
                    Call<GroupSearchRequest> groupMemberCall=api.Group_search(groupnameEt.getText().toString().trim());
                    groupMemberCall.enqueue(new Callback<GroupSearchRequest>() {
                        @Override
                        public void onResponse(Call<GroupSearchRequest> call, Response<GroupSearchRequest> response) {
                            Log.d("OOO",String.valueOf(response.code()));
                            if (response.code()==200){
                                //Log.d("OOO",response.body().getGroupMembe);
                                GroupSearchRequest groupRequest=response.body();
                                if (groupRequest.getGroupsearch().size()>0){
                                    for (Groupsearch groupsearch:groupRequest.getGroupsearch())
                                    {
                                        //try {
                                            Toast.makeText(textView.getContext(), "Call", Toast.LENGTH_SHORT).show();

                                            Log.d("FFF",String.valueOf(groupRequest.getGroupsearch().size())+"  "+groupRequest.getMessage());
                                            //Log.d("OOO",groupsearch.getGroupName());
                                            /*GroupInformation groupInformation=new GroupInformation(groupsearch.getId().toString(),
                                                    groupsearch.getGroupName(),groupsearch.getCooksName().toString(),
                                                    groupsearch.getShoppingType(),groupsearch.getMealType(),
                                                    groupsearch.getIsAdmin().toString(),groupsearch.getCreatedAt(),
                                                    groupsearch.getUpdatedAt(),groupsearch.getGroupmemberCount().toString());*/
                                            for (Admin admin:groupsearch.getAdmin()){
                                        GroupInformation groupInformation=new GroupInformation(groupsearch.getId().toString(),
                                                groupsearch.getGroupName(),"null",
                                                groupsearch.getShoppingType(),groupsearch.getMealType(),
                                                "null",groupsearch.getCreatedAt(),
                                                groupsearch.getUpdatedAt(),groupsearch.getGroupmemberCount().toString(),admin.getPhoneNumber());
                                            Utils.groupInformations.add(groupInformation);}



                                      /*  }
                                        catch (Exception e){

                                        }*/
                                    }


                                }
                                Log.d("OOO",Utils.groupInformations.toString());
                                //mAdapter=new GroupListRecyclerViewAdapter(textView.getContext(),groupname,phonenumber,adminName,mealtype,cookingtype,shoppingtype,groupcreated);
                                mAdapter=new GroupListRecyclerViewAdapter(textView.getContext());
                                grouplistRCV.setAdapter(mAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<GroupSearchRequest> call, Throwable t) {

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