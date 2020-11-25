package com.trinoq.mealmanager.features.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.adapters.BazarListRecyclerViewAdapter;
import com.trinoq.mealmanager.features.model.models.BazarListInformation;
import com.trinoq.mealmanager.features.model.pojo.request.BazarInsertRequest;
import com.trinoq.mealmanager.features.model.pojo.request.BazarListRequest;
import com.trinoq.mealmanager.features.model.pojo.request.Bazarlist;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShoppingFragment extends Fragment  {


    @BindView(R.id.addBazarFab)
    FloatingActionButton addBazarFab;
    @BindView(R.id.bazarListRcv)
    RecyclerView bazarListRcv;

    BazarListInformation bazarListInformation;
    private RecyclerView.LayoutManager layoutManagergroupname;
    private RecyclerView.Adapter adapter;
    //String[] userId={"Selected User Id","2","3"};

    ArrayList<String> userName=new ArrayList<>();
    ArrayList<String> userId=new ArrayList<>();

    DatePickerDialog datePickerDialog;
    String currentdate,userid;
    Retrofit retrofit;
    Api api;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this,view);

        bazarListRcv.setHasFixedSize(true);
        layoutManagergroupname=new LinearLayoutManager(getContext());
        bazarListRcv.setLayoutManager(layoutManagergroupname);

        retrofit= RetrofitClient.getClient();
        api=retrofit.create(Api.class);

        final Calendar calendar=Calendar.getInstance();

        userId.clear();
        userName.clear();

        for (int i=0;i<Utils.groupAllMembersInformations.size();i++){
            userName.add(Utils.groupAllMembersInformations.get(i).getUserName());
            userId.add(String.valueOf(Utils.groupAllMembersInformations.get(i).getUserId()));

        }

        showBazarList();


        addBazarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dialog dialog=new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_bazar);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
                EditText totalBazarEt=dialog.findViewById(R.id.totalBazarEt);
                Spinner userNameSp=dialog.findViewById(R.id.userNameSp);
                TextView userIdTv=dialog.findViewById(R.id.userIdTv);
                Button date=dialog.findViewById(R.id.bazarDateBt);
                FloatingActionButton bazarInsertFab=dialog.findViewById(R.id.bazarInsertFab);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date current_date = new Date();
                date.setText(String.valueOf(dateFormat.format(current_date)));

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int yearc=calendar.get(Calendar.YEAR);
                        int monthc=calendar.get(Calendar.MONTH);
                        final int dayc=calendar.get(Calendar.DAY_OF_MONTH);
                        datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                                calendar.set(Calendar.YEAR,year);
                                calendar.set(Calendar.MONTH,monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                                currentdate= String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+String.valueOf(dayOfMonth);

                                Log.d("DDDDD",currentdate);
                                date.setText(currentdate);

                            }
                        },yearc,monthc,dayc);

                        datePickerDialog.show();
                    }
                });

                ArrayAdapter id=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,userName);
                id.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                userNameSp.setAdapter(id);

                userNameSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getContext(),userName.get(i)+" = "+ userId.get(i), Toast.LENGTH_SHORT).show();
                        userIdTv.setText("User ID : "+userId.get(i));
                        userid=userId.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                bazarInsertFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Call<ResponseBody> setBazarResponse=api.setBazar(new BazarInsertRequest("2",userid,totalBazarEt.getText().toString(),date.getText().toString()));
                        setBazarResponse.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                Log.d("BBBBB",String.valueOf(response.code())+"  "+userid+"  "+totalBazarEt.getText().toString()+"  "+date.getText().toString());

                                if (response.code()==200){
                                    Toast.makeText(getActivity(), "Seccess", Toast.LENGTH_SHORT).show();
                                    showBazarList();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.dismiss();
                    }
                });


            }
        });


        return view;
    }

    private void showBazarList() {



        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date current_date = new Date();

        String fromdate=String.valueOf(dateFormat.format(current_date)+"-01");
        String todate=String.valueOf(dateFormat.format(current_date)+"-31");
        Log.d("FAFAFA",""+fromdate+"  "+todate);
        Call<BazarListRequest> call=api.getBazarList("2",fromdate,todate);
        call.enqueue(new Callback<BazarListRequest>() {
            @Override
            public void onResponse(Call<BazarListRequest> call, Response<BazarListRequest> response) {
                if (response.code()==200){
                    Utils.bazarListInformations.clear();
                    BazarListRequest bazarListRequest=response.body();

                    if (bazarListRequest.getBazarlist().size()>0){
                        for (Bazarlist bazarlist:bazarListRequest.getBazarlist()){

                            bazarListInformation=new BazarListInformation(bazarlist.getId(),
                                    bazarlist.getGroupId(),bazarlist.getUserId(),bazarlist.getTotalAmount(),bazarlist.getDate()
                                    ,bazarlist.getCreatedAt(),bazarlist.getUpdatedAt());

                            Utils.bazarListInformations.add(bazarListInformation);
                        }
                    }
                    adapter=new BazarListRecyclerViewAdapter(getContext());
                    bazarListRcv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BazarListRequest> call, Throwable t) {

            }
        });
    }


}
