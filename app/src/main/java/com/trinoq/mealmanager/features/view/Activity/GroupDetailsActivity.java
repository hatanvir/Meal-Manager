package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.adapters.NotificationListAdapter;
import com.trinoq.mealmanager.features.model.fcmNotification.Data;
import com.trinoq.mealmanager.features.model.fcmNotification.NotificationSender;
import com.trinoq.mealmanager.features.model.pojo.request.GroupMemberCreationRequest;
import com.trinoq.mealmanager.features.model.pojo.request.MemberInvitation;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class GroupDetailsActivity extends AppCompatActivity {

    @BindView(R.id.groupNameTv)
    TextView groupNameTv;
    @BindView(R.id.phoneNumberTv)
    TextView phoneNumberTv;
    @BindView(R.id.adminNameTv)
    TextView adminNameTv;
    @BindView(R.id.totalGroupmembersTv)
    TextView totalmembersTv;
    @BindView(R.id.mealPricingTv)
    TextView mealPricingTv;
    @BindView(R.id.mealtypeTv)
    TextView mealtypeTv;
    @BindView(R.id.cookingtypeTv)
    TextView cookingtypeTv;
    @BindView(R.id.shoppingtypeTv)
    TextView shoppingtypeTv;
    @BindView(R.id.gruopcreateddateTV)
    TextView groupcreateddateTv;
    @BindView(R.id.clearBt)
    ImageView clearBt;

    @BindView(R.id.sendRequestBt)
    Button sendRequestBt;

    int position;

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

        setContentView(R.layout.activity_group_details);
        ButterKnife.bind(GroupDetailsActivity.this);

        Intent intent=getIntent();
        position=intent.getIntExtra("position",1);
        groupNameTv.setText(Utils.groupInformations.get(position).getGroupName());
        //phoneNumberTv.setText(Utils.groupInformations.get(position).getPhoneNumber());
        adminNameTv.setText(Utils.groupInformations.get(position).getAdminName());
        mealtypeTv.setText(Utils.groupInformations.get(position).getMealType());
        cookingtypeTv.setText(Utils.groupInformations.get(position).getCooksName());
        shoppingtypeTv.setText(Utils.groupInformations.get(position).getShoppingType());
        groupcreateddateTv.setText(Utils.groupInformations.get(position).getGroupCreatedDate());
        totalmembersTv.setText(Utils.groupInformations.get(position).getTotalMembers());
       // mealPricingTv.setText(Utils.groupInformations.get(position).getMealpricing());

        clearBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sendRequestBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _invitation();
            }
        });

    }

    private void _invitation() {
        Api api = RetrofitClient.getClient().create(Api.class);

        api.invitation(new MemberInvitation(Integer.parseInt(Utils.groupInformations.get(position).getGorupId()),1,1,"2020-05-05"))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200){
                          sendNotificationToReceiver();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(GroupDetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }



    private void sendNotificationToReceiver() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fcm.googleapis.com/").build();

        Data data = new Data("Invitation","Group invitation for "+Utils.groupInformations.get(position).getGroupName());

        NotificationSender notificationSender = new NotificationSender(data,("token"));

        Api api = retrofit.create(Api.class);
        api.sendNotifcation(notificationSender)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200) {

                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
