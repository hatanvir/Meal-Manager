package com.trinoq.mealmanager.features.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.material.snackbar.Snackbar;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.PhoneAuthModel;
import com.trinoq.mealmanager.features.model.PhoneAuthModelImplementation;
import com.trinoq.mealmanager.features.view.MainActivity;
import com.trinoq.mealmanager.features.viewmodel.PhoneAuthViewModel;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyPhoneNumberFragment extends Fragment {
    PhoneAuthModel model;
    PhoneAuthViewModel viewModel;
    @BindView(R.id.pinView)
    PinView pinView;
    @BindView(R.id.nextImage)
    ImageView nextImage;

    String phoneNumber;
    Dialog loadingDialog;

    public VerifyPhoneNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_phone_number, container, false);
        ButterKnife.bind(this,view);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.pb_loading,null);

        loadingDialog = new Dialog(getActivity());
        loadingDialog.setContentView(v);
        loadingDialog.setCancelable(false);

        phoneNumber = getArguments().getString("phoneNumber");

        model = new PhoneAuthModelImplementation(getActivity());
        // initialize ViewModel
        viewModel = (PhoneAuthViewModel) ViewModelProviders.of(this).get(PhoneAuthViewModel.class);
        phoneAuth();

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manualAuth();
            }
        });
        return view;
    }

    private void manualAuth() {
        viewModel.manualVarifivation(pinView.getText().toString(),model);
        viewModel.progressbarLoading.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (((Boolean)o)){
                    loadingDialog.show();
                }else {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    private void phoneAuth() {
        viewModel.authInfo(phoneNumber, getActivity(),model);

        viewModel.codeSentSuccess.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
               snackbar("Verification code successfully sent");
                if (!o.toString().equals("sent")){
                    pinView.setText(o.toString());
                }
            }
        });
        viewModel.codeSentfailed.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                snackbar("Verification code sent failed ! Retry");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        viewModel.verificationSuccess.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Toast.makeText(getActivity(), "Verification Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
        viewModel.verificationfailed.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                snackbar(((String)o));
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        viewModel.progressbarLoading.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (((Boolean)o)){
                    loadingDialog.show();
                }else {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    private void snackbar(String msg) {
        Snackbar.make(nextImage,msg,Snackbar.LENGTH_SHORT).show();
    }
}
