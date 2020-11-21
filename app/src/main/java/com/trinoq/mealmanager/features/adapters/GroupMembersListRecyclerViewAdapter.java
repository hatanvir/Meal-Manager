package com.trinoq.mealmanager.features.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinoq.mealmanager.R;

import java.util.ArrayList;

public class GroupMembersListRecyclerViewAdapter extends RecyclerView.Adapter<GroupMembersListRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> memberName=new ArrayList<>();
    private ArrayList<String> phoneNumber=new ArrayList<>();

    public GroupMembersListRecyclerViewAdapter(Context context, ArrayList<String> memberName, ArrayList<String> phoneNumber) {
        this.context = context;
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
    }

    public GroupMembersListRecyclerViewAdapter(Context context, ArrayList<String> phoneNumber) {
        this.context = context;
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public GroupMembersListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.group_members_list_recyclerview,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMembersListRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.memberNameTv.setText(memberName.get(position));
        holder.phoneNumberTv.setText(phoneNumber.get(position));

    }

    @Override
    public int getItemCount() {
        return phoneNumber.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView memberNameTv,phoneNumberTv;
        Button invietBt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            memberNameTv=itemView.findViewById(R.id.memberNameTv);
            phoneNumberTv=itemView.findViewById(R.id.phoneNumberTv);
            invietBt=itemView.findViewById(R.id.invietBt);
        }
    }
}
