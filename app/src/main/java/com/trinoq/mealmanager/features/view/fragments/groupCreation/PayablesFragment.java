package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.Payables.PayablesModel;
import com.trinoq.mealmanager.features.model.Payables.PayablesModelImplementation;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;
import com.trinoq.mealmanager.features.viewmodel.PayablesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

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

    public PayablesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payables, container, false);
        ButterKnife.bind(this, v);

        model = new PayablesModelImplementation(getActivity());
        viewModel = new ViewModelProvider(getActivity()).get(PayablesViewModel.class);

        nextImage.setOnClickListener(v12 -> postPayablesOperation());

        backBt.setOnClickListener(v1 -> getActivity().getSupportFragmentManager().popBackStack());
        return v;
    }

    private void postPayablesOperation() {
        viewModel.payablesRequest(new PayablesRequest("1", electricityEt.getText().toString(),
                othersEt.getText().toString(), mealAdvanceEt.getText().toString(),
                mealAdvanceEt.getText().toString(), houseRentEt.getText().toString()), model);
        viewModel.payablesCreateSuccess.observe(getActivity(), new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.payablesCreateFailed.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}