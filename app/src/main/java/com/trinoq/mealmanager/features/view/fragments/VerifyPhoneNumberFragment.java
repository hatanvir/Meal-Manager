package com.trinoq.mealmanager.features.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.installations.FirebaseInstallations;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.phoneAuth.PhoneAuthModel;
import com.trinoq.mealmanager.features.model.phoneAuth.PhoneAuthModelImplementation;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;
import com.trinoq.mealmanager.features.model.register.RegisterModel;
import com.trinoq.mealmanager.features.model.register.RegisterModelImplementation;
import com.trinoq.mealmanager.features.viewmodel.PhoneAuthViewModel;
import com.trinoq.mealmanager.features.viewmodel.RegisterViewModel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;
import java.util.concurrent.Executor;

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

    private KProgressHUD progressHUD;

    private RegisterModel registerModel;
    private RegisterViewModel registerViewModel;
    private String name;

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
        name = getArguments().getString("name");
        model = new PhoneAuthModelImplementation(getActivity());
        // initialize ViewModel
        viewModel = new ViewModelProvider(this).get(PhoneAuthViewModel.class);


        registerModel = new RegisterModelImplementation(getActivity());
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        progressHUD =  KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        if(name == "null"){
            Log.d("tttt","called");
            phoneAuth();
        }else {
            phoneAuthWithName();
        }

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
                    progressHUD.setLabel("Sending...").show();
                }else {
                    progressHUD.dismiss();
                }
            }
        });
    }
    private void phoneAuth(){
        viewModel.authInfo(phoneNumber, getActivity(),model);

        viewModel.authInfo(phoneNumber, getActivity(),model);

        viewModel.codeSentSuccess.observe(getActivity(), new Observer() {
            @Override
            public void onChanged(Object o) {
                snackbar("Verification code successfully sent");
                if (!o.toString().equals("sent")){
                    pinView.setText(o.toString());
                }
            }
        });
        viewModel.codeSentfailed.observe(getActivity(), new Observer() {
            @Override
            public void onChanged(Object o) {
                snackbar("Verification code sent failed ! Retry");
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        viewModel.verificationSuccess.observe(getActivity(), new Observer() {
            @Override
            public void onChanged(Object o) {
                Toast.makeText(getActivity(), "Verification Success", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                assert fm != null;
                int count = fm.getBackStackEntryCount();
                for(int i = 0; i < count; ++i) {
                    fm.popBackStack();
                }

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.authenticationContainer, new WelcomeFragment())
                        .addToBackStack("welcomeFrag").commit();
            }
        });
    }

    private void phoneAuthWithName() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(getActivity(),  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken", newToken);

                registerViewModel.register(new RegisterRequest(name,phoneNumber,newToken),registerModel);

                registerViewModel.registerSuccess.observe(Objects.requireNonNull(getActivity()), new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        if(o.toString() == "Success"){

                            viewModel.authInfo(phoneNumber, getActivity(),model);

                            viewModel.codeSentSuccess.observe(getActivity(), new Observer() {
                                @Override
                                public void onChanged(Object o) {
                                    snackbar("Verification code successfully sent");
                                    if (!o.toString().equals("sent")){
                                        pinView.setText(o.toString());
                                    }
                                }
                            });
                            viewModel.codeSentfailed.observe(getActivity(), new Observer() {
                                @Override
                                public void onChanged(Object o) {
                                    snackbar("Verification code sent failed ! Retry");
                                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
                                }
                            });
                            viewModel.verificationSuccess.observe(getActivity(), new Observer() {
                                @Override
                                public void onChanged(Object o) {
                                    Toast.makeText(getActivity(), "Verification Success", Toast.LENGTH_SHORT).show();
                                    FragmentManager fm = getFragmentManager();
                                    assert fm != null;
                                    int count = fm.getBackStackEntryCount();
                                    for(int i = 0; i < count; ++i) {
                                        fm.popBackStack();
                                    }

                                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.authenticationContainer, new WelcomeFragment())
                                            .addToBackStack("welcomeFrag").commit();
                                }
                            });
                        }
                    }
                });
                registerViewModel.registerFailed.observe(Objects.requireNonNull(getActivity()), new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        snackbar(o.toString()+". Try login.");
                        setfargment(new LoginFragment(),"LOGON_FRAG");
                    }
                });


                viewModel.verificationfailed.observe(getActivity(), new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        snackbar(((String)o));
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
                viewModel.progressbarLoading.observe(getActivity(), new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        if (((Boolean)o)){
                            progressHUD.setLabel("Sending...").show();
                        }else {
                            progressHUD.dismiss();
                        }
                    }
                });
            }
        });

    }

    private void setfargment(Fragment fragment, String tag) {
        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.authenticationContainer, fragment)
                .addToBackStack(tag).commit();
    }

    private void snackbar(String msg) {
        //Snackbar.make(nextImage,msg,Snackbar.LENGTH_SHORT).show();
        Log.d("ttt",msg);
    }
}
