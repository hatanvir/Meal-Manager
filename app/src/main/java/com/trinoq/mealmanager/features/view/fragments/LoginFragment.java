package com.trinoq.mealmanager.features.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.trinoq.mealmanager.R;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements Validator.ValidationListener {

    @NotEmpty
    @BindView(R.id.phoneNumberEt)
    EditText phoneNumberEt;
    @BindView(R.id.nextImage)
    ImageView nextImage;
    @BindView(R.id.haveAccountTv)
    TextView haveAccountTv;
    Validator validator;

    TextView dialogMessageTv;
    Button yesBt;
    Button noBt;

    Dialog confirmPhoneNumberDialog;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);

        validator = new Validator(this);
        validator.setValidationListener(this);

        confirmPhoneNumberDialog = new Dialog(getActivity());
        confirmPhoneNumberDialog.setContentView(R.layout.dialog_alert);

        confirmPhoneNumberDialog.setCancelable(false);

        haveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            setfargment(new SignUpFragment(), "signUpFragment");
        }
      });
        nextImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 validator.validate();
             }
         });

        return view;
    }

    private void setfargment(Fragment fragment, String tag) {
        if(phoneNumberEt.getText().toString().length()<11){
            Toast.makeText(getActivity(), "Invalid phone number", Toast.LENGTH_SHORT).show();
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("name","null");
            if (tag.equals("verifyPhoneNumFrag")) {
                bundle.putString("phoneNumber", optimizedPhoneNumber());
                fragment.setArguments(bundle);
            }

            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.authenticationContainer, fragment)
                    .addToBackStack(tag).commit();
        }

    }

    private String optimizedPhoneNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(phoneNumberEt.getText().toString());
        String phoneNumReverse = String.valueOf(stringBuilder.reverse());
        String phoneNumberRev = "";
        for (int i = 0; i < 11; i++) {
            phoneNumberRev += phoneNumReverse.charAt(i);
        }
        StringBuilder stringBuilderMain = new StringBuilder();
        stringBuilderMain.append(phoneNumberRev);
        String phoneNumberFinal = String.valueOf(stringBuilderMain.reverse());

        return "+88" + phoneNumberFinal;
    }

    @Override
    public void onValidationSucceeded() {
        dialogMessageTv = confirmPhoneNumberDialog.findViewById(R.id.dialogMessageTv);
        yesBt = confirmPhoneNumberDialog.findViewById(R.id.yesBt);
        noBt = confirmPhoneNumberDialog.findViewById(R.id.noBt);

        dialogMessageTv.setText("A verification code will be sent to ("+phoneNumberEt.getText().toString()+") this number. is the number correct?");
        yesBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPhoneNumberDialog.dismiss();
                setfargment(new VerifyPhoneNumberFragment(), "verifyPhoneNumFrag");
            }
        });
        noBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPhoneNumberDialog.dismiss();
            }
        });
        confirmPhoneNumberDialog.show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
        }
    }
}
