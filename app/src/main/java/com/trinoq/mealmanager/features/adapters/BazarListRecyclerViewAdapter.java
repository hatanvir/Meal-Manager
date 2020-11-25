package com.trinoq.mealmanager.features.adapters;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.pojo.request.BazarInsertRequest;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BazarListRecyclerViewAdapter  extends RecyclerView.Adapter<BazarListRecyclerViewAdapter.ViewHolder> {

    private Context context;

    //String[] userId={"Selected User Id","2","3"};
    String userName,bazarDate,bazarId;
    Integer userId,totalAmount;
    Retrofit retrofit;
    Api api;
    DatePickerDialog datePickerDialog;

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

        totalAmount=Utils.bazarListInformations.get(position).getTotalAmount();
        bazarDate=Utils.bazarListInformations.get(position).getDate();
        userId=Utils.bazarListInformations.get(position).getUserId();
        bazarId=String.valueOf(Utils.bazarListInformations.get(position).getId());
        holder.totalBazarTv.setText(String.valueOf(totalAmount));
        holder.bazarDateTv.setText(bazarDate);
        holder.userIdTv.setText("User Id: "+String.valueOf(userId));
        for (int i =0;i<Utils.groupAllMembersInformations.size();i++){
            if (userId==Utils.groupAllMembersInformations.get(i).getUserId()){
                userName=Utils.groupAllMembersInformations.get(i).getUserName();
                holder.userNameTv.setText(userName);
            }
        }

        setUpdatedBazar(holder.editBazarIB,totalAmount,bazarId,bazarDate,userId,userName);

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

    public void setUpdatedBazar(ImageButton editBazarIB, Integer totalAmount, String bazarId, String bazarDate, Integer userId, String userName){

        final Calendar calendar=Calendar.getInstance();
        retrofit= RetrofitClient.getClient();
        api=retrofit.create(Api.class);
        editBazarIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_bazar);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
                EditText totalBazarEt=dialog.findViewById(R.id.totalBazarEt);
                Spinner userNameSp=dialog.findViewById(R.id.userNameSp);
                Button date=dialog.findViewById(R.id.bazarDateBt);
                TextView userNameTv=dialog.findViewById(R.id.userNameTv);
                TextView userIdTv=dialog.findViewById(R.id.userIdTv);
                FloatingActionButton bazarUpdatedFab=dialog.findViewById(R.id.bazarInsertFab);

                userNameSp.setVisibility(View.GONE);
                userNameTv.setVisibility(View.VISIBLE);

                date.setText(bazarDate);
                totalBazarEt.setText(String.valueOf(totalAmount));
                userNameTv.setText(userName);
                userIdTv.setText("User Id : "+String.valueOf(userId));


                /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date current_date = new Date();
                date.setText(String.valueOf(dateFormat.format(current_date)));

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int yearc=calendar.get(Calendar.YEAR);
                        int monthc=calendar.get(Calendar.MONTH);
                        final int dayc=calendar.get(Calendar.DAY_OF_MONTH);
                        datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                                calendar.set(Calendar.YEAR,year);
                                calendar.set(Calendar.MONTH,monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                                String currentdate= String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+String.valueOf(dayOfMonth);

                                Log.d("DDDDD",currentdate);
                                date.setText(currentdate);

                            }
                        },yearc,monthc,dayc);

                        datePickerDialog.show();
                    }
                });*/

                //userIdSp.setOnItemSelectedListener(getContext());
                //userIdSp.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) getContext());

               /* ArrayAdapter id=new ArrayAdapter(context,android.R.layout.simple_spinner_item,userId);
                id.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                userIdSp.setAdapter(id);

                userIdSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(context, userId[i], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/

               bazarUpdatedFab.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Call<ResponseBody> bazarUpdated=api.setBazarUpdated(bazarId,new BazarInsertRequest("2",String.valueOf(userId),totalBazarEt.getText().toString(),bazarDate));

                       bazarUpdated.enqueue(new Callback<ResponseBody>() {
                           @Override
                           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                               if (response.code()==200){
                                   Toast.makeText(context, "Seccess Bazar Updated", Toast.LENGTH_SHORT).show();
                               }
                           }

                           @Override
                           public void onFailure(Call<ResponseBody> call, Throwable t) {

                               Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                           }
                       });
                       dialog.dismiss();
                   }
               });

            }
        });

    }

}
