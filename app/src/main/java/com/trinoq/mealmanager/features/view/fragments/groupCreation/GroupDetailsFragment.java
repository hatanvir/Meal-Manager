package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.GroupDetails.GroupCreateImplimentation;
import com.trinoq.mealmanager.features.model.GroupDetails.GroupCreateModel;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.viewmodel.GroupDetailsViewmodel;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class GroupDetailsFragment extends Fragment implements Validator.ValidationListener {

    @NotEmpty
    @BindView(R.id.groupNameEt)
    EditText groupNameEt;
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

    private GroupCreateModel model;
    private GroupDetailsViewmodel viewmodel;

    private KProgressHUD progressHUD;
    String breakFast="0",lunch="0",dinner="0";

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

        model = new GroupCreateImplimentation(getActivity());

        progressHUD =  KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

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
        viewmodel = new ViewModelProvider(this).get(GroupDetailsViewmodel.class);
        progressHUD.setLabel("Saving...").show();
        String groupName = groupNameEt.getText().toString();
        String address = addressEt.getText().toString();
        String cooksName = cooksNameEt.getText().toString();

        if(breakfastCb.isChecked()) {breakFast = "1";}else {breakFast = "0";}
        if(launchCb.isChecked()) {lunch = "1";}else {lunch = "0";}
        if (dinnerCb.isChecked()) {dinner = "1";}else {dinner = "0";}

      /*  viewmodel.groupDetailsRequest(new GroupCreateRequest(groupName,address,cooksName,"null",breakFast+"/"+lunch+"/"+dinner,"1"),model);

        viewmodel.groupDetailsRequestSuccess.observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                setfargment(new MealPricingFragment());
                progressHUD.dismiss();
            }
        });
        viewmodel.groupDetailsRequestFailed.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), "Failed to save data.Try Again"+s, Toast.LENGTH_SHORT).show();
                progressHUD.dismiss();
            }
        });*/
        setfargment(new MealPricingFragment());
        progressHUD.dismiss();
    }
    private void setfargment(Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putString("breakFast",breakFast);
        bundle.putString("lunch",lunch);
        bundle.putString("dinner",dinner);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.groupCreateViewContainer, fragment)
                .addToBackStack("GRP_DETAILES").commit();
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