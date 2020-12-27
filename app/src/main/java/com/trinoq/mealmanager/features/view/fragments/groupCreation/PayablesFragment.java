package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.adapters.NotificationListAdapter;
import com.trinoq.mealmanager.features.model.Payables.PayablesModel;
import com.trinoq.mealmanager.features.model.Payables.PayablesModelImplementation;
import com.trinoq.mealmanager.features.model.pojo.request.GroupMemberCreationRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;
import com.trinoq.mealmanager.features.view.Activity.TestActivity;
import com.trinoq.mealmanager.features.viewmodel.PayablesViewModel;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayablesFragment extends Fragment {

    @BindView(R.id.electricityEt)
    EditText electricityEt;
    @BindView(R.id.othersEt)
    EditText othersEt;
    @BindView(R.id.mealAdvanceEt)
    EditText mealAdvanceEt;
    @BindView(R.id.houseRentEt)
    EditText houseRentEt;
    @BindView(R.id.nextImage)
    ImageView nextImage;
    @BindView(R.id.backBt)
    ImageView backBt;

    private PayablesModel model;
    private PayablesViewModel viewModel;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String gpId;
    KProgressHUD progressHUD;

    public PayablesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payables, container, false);
        ButterKnife.bind(this, v);

        sharedPreferences = getActivity().getSharedPreferences("GRP_INFO", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
         gpId = getArguments().getString("gpId").toString();



        model = new PayablesModelImplementation(getActivity());
        viewModel = new ViewModelProvider(getActivity()).get(PayablesViewModel.class);

        progressHUD =  KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        nextImage.setOnClickListener(v12 -> postPayablesOperation());

        backBt.setOnClickListener(v1 -> getActivity().getSupportFragmentManager().popBackStack());
        return v;
    }

    private void postPayablesOperation() {
        progressHUD.setLabel("Saving..").show();

        viewModel.payablesRequest(new PayablesRequest("1", electricityEt.getText().toString(),
                othersEt.getText().toString(), mealAdvanceEt.getText().toString(),
                mealAdvanceEt.getText().toString(), houseRentEt.getText().toString()), model);
        viewModel.payablesCreateSuccess.observe(getActivity(), new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
               // getActivity().finish();
               // startActivity(new Intent(getActivity(), TestActivity.class));

                addMemberTogroup();
                editor.putString("gpId",gpId).apply();
                progressHUD.dismiss();
            }
        });
        viewModel.payablesCreateFailed.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                progressHUD.dismiss();
            }
        });
    }

    private void addMemberTogroup() {
        progressHUD.setLabel("Saving..").show();
        GroupMemberCreationRequest groupMemberCreationRequest=
                new GroupMemberCreationRequest(gpId, FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        Api api = RetrofitClient.getClient().create(Api.class);

        api.addGroupMember(groupMemberCreationRequest)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200) {
                             getActivity().finish();
                             //startActivity(new Intent(getActivity(), Add.class));
                            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.groupCreateViewContainer, new CongrastFragment())
                                    .addToBackStack("CONGRAST_FRAG").commit();
                             progressHUD.dismiss();
                        }else {
                            Toast.makeText(getActivity(), "Failed to join group", Toast.LENGTH_SHORT).show();
                            progressHUD.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressHUD.dismiss();
                    }
                });
    }
}