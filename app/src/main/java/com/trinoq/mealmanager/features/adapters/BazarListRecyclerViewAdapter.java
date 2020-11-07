package com.trinoq.mealmanager.features.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.totalExtraBazarTv.setText(Utils.bazarListInformations.get(position).getExtraBazar());

    }

    @Override
    public int getItemCount() {
        return Utils.bazarListInformations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView totalBazarTv,totalExtraBazarTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            totalBazarTv=itemView.findViewById(R.id.totalBazarTv);
            totalExtraBazarTv=itemView.findViewById(R.id.totalExtraTv);
        }
    }
}
