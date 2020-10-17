package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.trinoq.mealmanager.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupDetailsFragment extends Fragment implements Validator.ValidationListener {

    @NotEmpty
    @BindView(R.id.phoneNumberEt)
    EditText phoneNumberEt;
    @NotEmpty
    @BindView(R.id.addressEt)
    EditText addressEt;

    @BindView(R.id.cooksNameEt)
    EditText cooksNameEt;
    @BindView(R.id.mealTypeTxt)
    TextView mealTypeTxt;

    @BindView(R.id.breakfastCb)
    CheckBox breakfastCb;
    @BindView(R.id.launchCb)
    CheckBox launchCb;
    @BindView(R.id.dinnerCb)
    CheckBox dinnerCb;
    @BindView(R.id.nextImage)
    ImageView nextImage;
    @BindView(R.id.haveAccountTv)
    ImageView haveAccountTv;

    Validator validator;
   /* @BindView(R.id.mealTypeSp)
    Spinner mealTypeSp;*/

    private String[] mealTypeArr = {"Shopping type", "Post-month pricing", "Post-month pricing"};

    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_group_details, container, false);
        ButterKnife.bind(this, v);

        validator = new Validator(this);
        validator.setValidationListener(this);

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
        setUi();
        return v;
    }

    private void setUi() {

    }

    @Override
    public void onValidationSucceeded() {
        String phone = phoneNumberEt.getText().toString();
        String address = addressEt.getText().toString();
        String cooksName = cooksNameEt.getText().toString();


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