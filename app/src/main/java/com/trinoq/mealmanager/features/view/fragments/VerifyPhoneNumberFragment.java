package com.trinoq.mealmanager.features.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.installations.FirebaseInstallations;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.models.UserInformation;
import com.trinoq.mealmanager.features.model.phoneAuth.PhoneAuthModel;
import com.trinoq.mealmanager.features.model.phoneAuth.PhoneAuthModelImplementation;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;
import com.trinoq.mealmanager.features.model.pojo.request.User;
import com.trinoq.mealmanager.features.model.pojo.request.UserInformationRequest;
import com.trinoq.mealmanager.features.model.register.RegisterModel;
import com.trinoq.mealmanager.features.model.register.RegisterModelImplementation;
import com.trinoq.mealmanager.features.view.Activity.AuthenticationActivity;
import com.trinoq.mealmanager.features.view.Activity.SplashActivity;
import com.trinoq.mealmanager.features.view.Activity.TestActivity;
import com.trinoq.mealmanager.features.viewmodel.PhoneAuthViewModel;
import com.trinoq.mealmanager.features.viewmodel.RegisterViewModel;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VerifyPhoneNumberFragment extends Fragment {
    PhoneAuthModel model;
    PhoneAuthViewModel viewModel;
    @BindView(R.id.pinView)
    PinView pinView;
    @BindView(R.id.nextImage)
    ImageView nextImage;
    @BindView(R.id.phnVerificationMsgTv)
    TextView phnVerificationMsgTv;

    String phoneNumber;
    Dialog loadingDialog;

    private KProgressHUD progressHUD;

    private RegisterModel registerModel;
    private RegisterViewModel registerViewModel;
    private String name;

    SharedPreferences.Editor myPreferences;

    SharedPreferences sharedPreferences;

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

        myPreferences=getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE).edit();
        sharedPreferences=getActivity().getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);

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


        phnVerificationMsgTv.setText("A verification code will be sent to" + phoneNumber+" this number.");

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

      //  viewModel.authInfo(phoneNumber, getActivity(),model);

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
               /* Toast.makeText(getActivity(), "Verification Success", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                assert fm != null;
                int count = fm.getBackStackEntryCount();
                for(int i = 0; i < count; ++i) {
                    fm.popBackStack();
                }

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.authenticationContainer, new WelcomeFragment())
                        .addToBackStack("welcomeFrag").commit();*/

                getCurrentUserInfo();
            }
        });
    }

    private void phoneAuthWithName() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(getActivity(),  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken", newToken);

                registerViewModel.register(new RegisterRequest(phoneNumber,name,newToken),registerModel);

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
        Snackbar.make(nextImage,msg,Snackbar.LENGTH_SHORT).show();
        //Log.d("ttt",msg);
    }



    private void getCurrentUserInfo() {
        Retrofit retrofit= RetrofitClient.getClient();
        Api api=retrofit.create(Api.class);
        Utils.userInformations.clear();

        Call<UserInformationRequest> call=api.UserInformation(phoneNumber);
        call.enqueue(new Callback<UserInformationRequest>() {
            @Override
            public void onResponse(Call<UserInformationRequest> call, Response<UserInformationRequest> response) {
                if (response.code()==200){
                    UserInformationRequest informationRequest=response.body();
                    if (informationRequest.getUser().size()>0) {
                        for (User user:informationRequest.getUser()){

                            myPreferences.putInt("UserId",user.getId());

                            //Log.d("IIIII",String.valueOf(user.getActiveGroupid()));
                            if (String.valueOf(user.getActiveGroupid()).equals("null")){
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
                            else {
                                UserInformation userInformation=new UserInformation(user.getId().toString(),user.getPhoneNumber(),
                                        "null",user.getFullName(),String.valueOf(user.getEmail()),String.valueOf(user.getImage()),String.valueOf(user.getActiveGroupid()),
                                        user.getCreatedAt(),user.getUpdatedAt());
                                Utils.userInformations.add(userInformation);
                                Utils.useridTest=user.getId().toString();

                                myPreferences.putInt("GroupId",Integer.parseInt(user.getActiveGroupid()));
                                myPreferences.apply();

                                startActivity(new Intent(getActivity(), TestActivity.class));
                            }


                        }
                    }
                }else {
                    startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                }
            }

            @Override
            public void onFailure(Call<UserInformationRequest> call, Throwable t) {

            }
        });


    }
}
