package com.trinoq.mealmanager.features.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.fcmNotification.Data;
import com.trinoq.mealmanager.features.model.fcmNotification.NotificationSender;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupMemberCreationRequest;
import com.trinoq.mealmanager.features.model.pojo.response.invitation.Inviationdatum;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.Viewholder> {
    Context context;
    List<Inviationdatum> inviationdata;
    String viewTypeTag;

    public NotificationListAdapter(Context context, List<Inviationdatum> inviationdata, String viewType) {
        this.context = context;
        this.inviationdata = inviationdata;
        this.viewTypeTag = viewType;
    }

    @NonNull
    @Override
    public NotificationListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewTypeTag.equals("sender")){
            view = LayoutInflater.from(context).inflate(R.layout.notification_rv_sender,parent,false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.notification_rv_receiver,parent,false);
        }

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationListAdapter.Viewholder holder, int position) {
        if(viewTypeTag.equals("sender")){
            holder.senderMsgTv.setText("Tanvir Accept your request.");
        }else {
            if(inviationdata.get(position).getStatus() == 1){
                holder.receiverAccBt.setBackgroundColor(Color.GRAY);
                holder.receiverDecBt.setBackgroundColor(Color.GRAY);

                holder.receiverDecBt.setClickable(false);
                holder.receiverAccBt.setClickable(false);
            }else if(inviationdata.get(position).getStatus() == 2){
                holder.receiverAccBt.setBackgroundColor(Color.GRAY);
                holder.receiverDecBt.setBackgroundColor(Color.GRAY);

                holder.receiverDecBt.setClickable(false);
                holder.receiverAccBt.setClickable(false);
            }
            holder.receiverMsgTv.setText("Tanvir sent you a invitation request to group one.");
            holder.receiverDecBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accDeclineRequest(2,position,holder);
                }
            });

            holder.receiverAccBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accDeclineRequest(1,position,holder);
                }
            });
        }
    }

    private void accDeclineRequest(int i,int position,NotificationListAdapter.Viewholder holder) {
        Api api = RetrofitClient.getClient().create(Api.class);

        api.invitationUpdate(2,i)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200){
                            addMemberTogroup(position,holder);
                        }else{
                            //Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                            Log.d("tttt",""+response.code()+" "+response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addMemberTogroup(int position,NotificationListAdapter.Viewholder holder) {

        GroupMemberCreationRequest groupMemberCreationRequest=
                new GroupMemberCreationRequest(inviationdata.get(position).getGroupId().toString(),"01781998168");
        Api api = RetrofitClient.getClient().create(Api.class);

        api.addGroupMember(groupMemberCreationRequest)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200) {
                            Toast.makeText(context, "Successfully accepted", Toast.LENGTH_SHORT).show();

                            holder.receiverAccBt.setBackgroundColor(Color.GRAY);
                            holder.receiverDecBt.setBackgroundColor(Color.GRAY);

                            holder.receiverDecBt.setClickable(false);
                            holder.receiverAccBt.setClickable(false);
                        }else {
                            Toast.makeText(context, "Failed to join group", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void sendNotificationToReceiver(int position,String status) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fcm.googleapis.com/").build();

        Data data = new Data("Invitation","Accepted your request.");

        NotificationSender notificationSender = new NotificationSender(data,inviationdata.get(position).getSenderInfo().get(0).getNotificationToken().toString());

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

    @Override
    public int getItemCount() {
        return inviationdata.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView senderMsgTv;

        TextView receiverMsgTv;
        Button receiverAccBt,receiverDecBt;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            if (viewTypeTag.endsWith("sender")){
                senderMsgTv = itemView.findViewById(R.id.notificationSenderTv);
            }else {
                receiverMsgTv = itemView.findViewById(R.id.receiverNotificationTv);
                receiverAccBt = itemView.findViewById(R.id.receiverAcceptBt);
                receiverDecBt = itemView.findViewById(R.id.receiverDeclineBt);
            }

        }
    }
}
