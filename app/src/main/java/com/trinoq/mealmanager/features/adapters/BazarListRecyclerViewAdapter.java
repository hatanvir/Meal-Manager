package com.trinoq.mealmanager.features.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.utils.Utils;

public class BazarListRecyclerViewAdapter  extends RecyclerView.Adapter<BazarListRecyclerViewAdapter.ViewHolder> {

    private Context context;

    public BazarListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BazarListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shape_bazar_list_recyclerview,parent,false);
        ViewHolder  holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BazarListRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.totalBazarTv.setText(String.valueOf(Utils.bazarListInformations.get(position).getTotalAmount()));


    }

    @Override
    public int getItemCount() {
        return Utils.bazarListInformations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView totalBazarTv,bazarDateTv,userNameTv,userIdTv;
        ImageButton editBazarIB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            totalBazarTv=itemView.findViewById(R.id.totalBazarTv);
            bazarDateTv=itemView.findViewById(R.id.bazardateTv);
            userNameTv=itemView.findViewById(R.id.userNameTv);
            userIdTv=itemView.findViewById(R.id.userIdTv);
            editBazarIB=itemView.findViewById(R.id.editBazar);
        }
    }
}
