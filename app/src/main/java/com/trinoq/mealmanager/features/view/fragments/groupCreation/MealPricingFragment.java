package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.trinoq.mealmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealPricingFragment extends Fragment {


    @BindView(R.id.mealPricingSp)
    Spinner mealPricingSp;

    @BindView(R.id.mealPricingSpLay)
    LinearLayout mealPricingSpLay;

    @BindView(R.id.postMonthFirstSp)
    Spinner postMonthFirstSp;
    @BindView(R.id.postMonthFirstSpLay)
    LinearLayout postMonthFirstSpLay;
    @BindView(R.id.postMonthSecondSp)
    Spinner postMonthSecondSp;
    @BindView(R.id.postMonthSecondSpLay)
    LinearLayout postMonthSecondSpLay;
    @BindView(R.id.postMonthThirdSp)
    Spinner postMonthThirdSp;
    @BindView(R.id.postMonthThirdSpLay)
    LinearLayout postMonthThirdSpLay;

    @BindView(R.id.specificPricingSp)
    Spinner specificPricingSp;

    @BindView(R.id.specificPricingSpLay)
    LinearLayout specificPricingSpLay;
    @BindView(R.id.breakfastEt)
    EditText breakfastEt;
    @BindView(R.id.breakfastLay)
    LinearLayout breakfastLay;
    @BindView(R.id.lunchEt)
    EditText lunchEt;
    @BindView(R.id.lunchLay)
    LinearLayout lunchLay;
    @BindView(R.id.dinnerEt)
    EditText dinnerEt;
    @BindView(R.id.dinnerLay)
    LinearLayout dinnerLay;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.nextImage)
    ImageView nextImage;
    @BindView(R.id.haveAccountTv)
    ImageView haveAccountTv;

    private String[] mealPricingType = {"Pree month pricing", "Post month pricing "};
    private String[] mealType = {"Full","Half"};

    public MealPricingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meal_pricing, container, false);
        ButterKnife.bind(this, v);

        setUi();
        return v;
    }

    private void setUi() {
        ArrayAdapter mealPricingSpAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, mealPricingType);
        mealPricingSp.setAdapter(mealPricingSpAdapter);

        ArrayAdapter mealTypeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, mealType);
        postMonthFirstSp.setAdapter(mealTypeAdapter);
        postMonthSecondSp.setAdapter(mealTypeAdapter);
        postMonthThirdSp.setAdapter(mealTypeAdapter);

        mealPricingSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    postMonthFirstSpLay.setVisibility(View.GONE);
                    postMonthSecondSpLay.setVisibility(View.GONE);
                    postMonthThirdSpLay.setVisibility(View.GONE);

                    breakfastLay.setVisibility(View.VISIBLE);
                    lunchLay.setVisibility(View.VISIBLE);
                    dinnerLay.setVisibility(View.VISIBLE);
                }else {
                    postMonthFirstSpLay.setVisibility(View.VISIBLE);
                    postMonthSecondSpLay.setVisibility(View.VISIBLE);
                    postMonthThirdSpLay.setVisibility(View.VISIBLE);

                    breakfastLay.setVisibility(View.GONE);
                    lunchLay.setVisibility(View.GONE);
                    dinnerLay.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //mealPricingSp.
    }
}