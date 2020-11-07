package com.trinoq.mealmanager.features.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShoppingFragment extends Fragment {


    @BindView(R.id.bazarEt)
    EditText bazarEt;
    @BindView(R.id.addBazarFab)
    FloatingActionButton addBazarFab;
    @BindView(R.id.bazarListRcv)
    RecyclerView bazarListRcv;

    BazarListInformation bazarListInformation;
    private RecyclerView.LayoutManager layoutManagergroupname;
    private RecyclerView.Adapter adapter;

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

        Retrofit retrofit= RetrofitClient.getClient();
        Api api=retrofit.create(Api.class);


        addBazarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call=api.setBazar(new BazarInsertRequest("2","9",bazarEt.getText().toString()));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code()==200){
                            Toast.makeText(getActivity(), "Seccess", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Call<BazarListRequest> call=api.getBazarList("2");
        call.enqueue(new Callback<BazarListRequest>() {
            @Override
            public void onResponse(Call<BazarListRequest> call, Response<BazarListRequest> response) {
                if (response.code()==200){
                    Utils.bazarListInformations.clear();
                    BazarListRequest bazarListRequest=response.body();

                    if (bazarListRequest.getBazarlist().size()>0){
                        for (Bazarlist bazarlist:bazarListRequest.getBazarlist()){

                            //String extra=bazarlist.getExtraBazar().toString();

                           // if (extra.equals(null)){
                                 bazarListInformation=new BazarListInformation(bazarlist.getId(),
                                        bazarlist.getGroupId(),bazarlist.getUserId(),bazarlist.getTotalAmount(),
                                        "0.00",bazarlist.getCreatedAt(),bazarlist.getUpdatedAt());


                          /*  }
                            else {
                                 bazarListInformation=new BazarListInformation(bazarlist.getId(),
                                        bazarlist.getGroupId(),bazarlist.getUserId(),bazarlist.getTotalAmount(),
                                        bazarlist.getExtraBazar().toString(),bazarlist.getCreatedAt(),bazarlist.getUpdatedAt());
                            }*/

                            Utils.bazarListInformations.add(bazarListInformation);
                        }
                    }
                    adapter=new BazarListRecyclerViewAdapter(getContext());
                    bazarListRcv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BazarListRequest> call, Throwable t) {

            }
        });


        return view;
    }
}
