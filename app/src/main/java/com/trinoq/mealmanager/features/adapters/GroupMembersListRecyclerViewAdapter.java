package com.trinoq.mealmanager.features.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.fcmNotification.Data;
import com.trinoq.mealmanager.features.model.fcmNotification.NotificationSender;
import com.trinoq.mealmanager.features.model.pojo.request.Member;
import com.trinoq.mealmanager.features.model.pojo.request.MemberInvitation;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class GroupMembersListRecyclerViewAdapter extends RecyclerView.Adapter<GroupMembersListRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> memberName = new ArrayList<>();
    private ArrayList<String> phoneNumber = new ArrayList<>();

    List<Member> members;

    private KProgressHUD progressHUD;


    public GroupMembersListRecyclerViewAdapter(Context context, List<Member> members) {
        this.context = context;
        this.members = members;
    }

    /* public GroupMembersListRecyclerViewAdapter(Context context, ArrayList<String> phoneNumber) {
         this.context = context;
         this.phoneNumber = phoneNumber;
     }
 */
    @NonNull
    @Override
    public GroupMembersListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_members_list_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);

        progressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMembersListRecyclerViewAdapter.ViewHolder holder, int position) {

        // holder.memberNameTv.setText(members.get(position).getAdmininfo().get(0).getFullName());
        holder.phoneNumberTv.setText(members.get(position).getPhoneNumber());

        // holder.invietBt.setBackgroundColor();
        holder.invietBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInvitationRequest(position);
            }
        });
    }

    private void sendInvitationRequest(int position) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Api api = RetrofitClient.getClient().create(Api.class);

        int gpId = sharedPreferences.getInt("GroupId", -1);
        int userId = sharedPreferences.getInt("UserId", -1);

        api.invitation(new MemberInvitation(Integer.parseInt(String.valueOf(gpId)), userId, members.get(position).getId(), "2020-05-05")).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Log.d("ttttt",response.body().toString());
                progressHUD.show();
                if (response.code() == 200) {
                    progressHUD.dismiss();
                    //Toast.makeText(context, "Sent request successfully", Toast.LENGTH_SHORT).show();
                    sendNotificationToReceiver(position);
                } else {
                   // Log.d("tttt", "" + response.code());
                    Toast.makeText(context, "Failed to sent request", Toast.LENGTH_SHORT).show();
                    progressHUD.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressHUD.dismiss();
            }
        });
    }

    private void sendNotificationToReceiver(int position) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fcm.googleapis.com/").addConverterFactory(GsonConverterFactory.create()).build();

        Data data = new Data("Invitation", "Tanvir want you to group oONE");

        try {
            NotificationSender notificationSender = new NotificationSender(data, members.get(position).getAdmininfo().get(0).getNotificationToken().toString());
            Api api = retrofit.create(Api.class);
            api.sendNotifcation(notificationSender)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.code() == 200) {
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                           /* if (response.body().success != 1) {
                               // Toast.makeText(SendNotif.this, "Failed ", Toast.LENGTH_LONG);
                            }*/
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
        } catch (Exception e) {
            Log.d("errrr", e.getMessage());
            Toast.makeText(context, "Failed try again" + e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView memberNameTv, phoneNumberTv;
        Button invietBt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            memberNameTv = itemView.findViewById(R.id.memberNameTv);
            phoneNumberTv = itemView.findViewById(R.id.phoneNumberTv);
            invietBt = itemView.findViewById(R.id.invietBt);
        }
    }
}
