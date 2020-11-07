package com.trinoq.mealmanager.features.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.view.Activity.GroupDetailsActivity;
import com.trinoq.mealmanager.utils.Utils;

import java.util.ArrayList;

public class GroupListRecyclerViewAdapter extends RecyclerView.Adapter<GroupListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> groupName=new ArrayList<>();
    private ArrayList<String> phoneNumber=new ArrayList<>();
    private ArrayList<String> adminName=new ArrayList<>();
    private ArrayList<String> mealtype=new ArrayList<>();
    private ArrayList<String> cookingtype=new ArrayList<>();
    private ArrayList<String> shoppingtype =new ArrayList<>();
    private ArrayList<String> groupcreated=new ArrayList<>();

    public GroupListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public GroupListRecyclerViewAdapter(Context context, ArrayList<String> groupName, ArrayList<String> phoneNumber, ArrayList<String> adminName, ArrayList<String> mealtype, ArrayList<String> cookingtype, ArrayList<String> shoppingtype, ArrayList<String> groupcreated) {
        this.context = context;
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
        this.adminName = adminName;
        this.mealtype = mealtype;
        this.cookingtype = cookingtype;
        this.shoppingtype = shoppingtype;
        this.groupcreated = groupcreated;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grouplist_recyclerview,parent,false);
        GroupListRecyclerViewAdapter.ViewHolder holder=new GroupListRecyclerViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.groupName.setText(Utils.groupInformations.get(position).getGroupName());
        //holder.phoneNumber.setText(Utils.groupInformations.get(position).getPhoneNumber());
        //Log.d("FFFF",mealtype.get(position));

        holder.groupList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GroupDetailsActivity.class);
                //intent.putExtra("position",groupName.get(position));
                intent.putExtra("position",position);
              /*  intent.putExtra("phonenumber",phoneNumber.get(position));
                //intent.putExtra("admin",adminName.get(position));
                intent.putExtra("mealtype",mealtype.get(position));*/
                //intent.putExtra("cookingtype",cookingtype.get(position));
                //intent.putExtra("shoppingtype",shoppingtype.get(position));
                //intent.putExtra("created",groupcreated.get(position));
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Utils.groupInformations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupName,phoneNumber;
        ConstraintLayout groupList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName=itemView.findViewById(R.id.groupNameTv);
            phoneNumber=itemView.findViewById(R.id.phoneNumberTv);
            groupList=itemView.findViewById(R.id.groupList);
        }
    }
}
