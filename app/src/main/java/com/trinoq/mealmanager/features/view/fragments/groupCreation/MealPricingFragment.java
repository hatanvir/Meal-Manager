package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.PostMonth.PostMonthModel;
import com.trinoq.mealmanager.features.model.PostMonth.PostMonthModelImplementation;
import com.trinoq.mealmanager.features.model.PreMonth.PreMonthModel;
import com.trinoq.mealmanager.features.model.PreMonth.PreMonthModelImplementation;
import com.trinoq.mealmanager.features.model.pojo.request.PostMonthRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PremonthRequest;
import com.trinoq.mealmanager.features.viewmodel.PostMonthViewModel;
import com.trinoq.mealmanager.features.viewmodel.PreMonthViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

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

    String breakFast = "0", lunch = "0", dinner = "0";
    @BindView(R.id.backBt)
    ImageView backBt;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;




    PreMonthModel preMonthModel;
    PreMonthViewModel preMonthViewModel;

    PostMonthViewModel postMonthViewModel;
    PostMonthModel postMonthModel;

    private String[] mealPricingType = {"Pree month pricing", "Post month pricing "};
    private String[] mealType = {"Full", "Half"};
    private int melTypePriceType = 0;double postMonthFirstSpData =1 ,postMonthSecondSpData = 1,postMonthThirdSpData = 1;

    public MealPricingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meal_pricing, container, false);
        ButterKnife.bind(this, v);

        preMonthModel = new PreMonthModelImplementation(getActivity());
        preMonthViewModel = new ViewModelProvider(this).get(PreMonthViewModel.class);

        postMonthModel = new PostMonthModelImplementation(getActivity());
        postMonthViewModel = new ViewModelProvider(this).get(PostMonthViewModel.class);

        breakFast = getArguments().getString("breakFast");
        lunch = getArguments().getString("lunch");
        dinner = getArguments().getString("dinner");

        String id = getArguments().getString("gpId");

        Log.d("ttt", String.valueOf(mealPricingType));

        setUi();
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(melTypePriceType == 0){
                    Toast.makeText(getActivity(), "Called", Toast.LENGTH_SHORT).show();
                    Log.d("ttt",postMonthFirstSpData+" "+postMonthSecondSpData+" "+postMonthThirdSpData);
                    preMonthViewModel.postMostMonthRequest(new PremonthRequest(id,breakfastEt.getText().toString(),lunchEt.getText().toString(),dinnerEt.getText().toString()),preMonthModel);
                    preMonthViewModel.setPreMothSuccess.observe(getActivity(), new Observer<ResponseBody>() {
                        @Override
                        public void onChanged(ResponseBody responseBody) {
                            Bundle bundle = new Bundle();
                            bundle.putString("gpId",id);

                            Fragment fragment = new DailyMealInputEndTimeFragment();

                            fragment.setArguments(bundle);

                            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.groupCreateViewContainer, fragment)
                                    .addToBackStack("GRP_DETAILES").commit();
                        }
                    });

                    preMonthViewModel.setPreMothFailed.observe(getActivity(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(melTypePriceType == 1){

                    Log.d("ttt2",postMonthFirstSpData+" "+postMonthSecondSpData+" "+postMonthThirdSpData);
                    postMonthViewModel.postMostMonthRequest(new PostMonthRequest(id,String.valueOf(postMonthFirstSpData),String.valueOf(postMonthSecondSpData),
                            String.valueOf(postMonthThirdSpData)),postMonthModel);
                    postMonthViewModel.setPostMothSuccess.observe(getActivity(), new Observer<ResponseBody>() {
                        @Override
                        public void onChanged(ResponseBody responseBody) {
                            Bundle bundle = new Bundle();
                            bundle.putString("gpId",id);

                            Fragment fragment = new DailyMealInputEndTimeFragment();

                            fragment.setArguments(bundle);

                            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.groupCreateViewContainer, fragment)
                                    .addToBackStack("GRP_DETAILES").commit();
                        }
                    });

                    postMonthViewModel.setPostMothFailed.observe(getActivity(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


        return v;
    }

    private void setUi() {

        postMonthFirstSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) postMonthFirstSpData = 1.0;
                else  postMonthFirstSpData = 0.5;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        postMonthSecondSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) postMonthSecondSpData = 1.0;
                else  postMonthSecondSpData = 0.5;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        postMonthThirdSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) postMonthThirdSpData = 1.0;
                else  postMonthThirdSpData = 0.5;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (breakFast.equals("1")) {
            postMonthFirstSpLay.setVisibility(View.VISIBLE);
            breakfastLay.setVisibility(View.VISIBLE);
        } else {
            postMonthFirstSpLay.setVisibility(View.GONE);
            breakfastLay.setVisibility(View.GONE);
        }
        if (lunch.equals("1")) {
            postMonthSecondSpLay.setVisibility(View.VISIBLE);
            lunchLay.setVisibility(View.VISIBLE);
        } else {
            postMonthSecondSpLay.setVisibility(View.GONE);
            lunchLay.setVisibility(View.GONE);
        }
        if (dinner.equals("1")) {
            postMonthThirdSpLay.setVisibility(View.VISIBLE);
            dinnerLay.setVisibility(View.VISIBLE);
        } else {
            postMonthThirdSpLay.setVisibility(View.GONE);
            dinnerLay.setVisibility(View.GONE);
        }

        ArrayAdapter mealPricingSpAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, mealPricingType);
        mealPricingSp.setAdapter(mealPricingSpAdapter);

        ArrayAdapter mealTypeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, mealType);
        postMonthFirstSp.setAdapter(mealTypeAdapter);
        postMonthSecondSp.setAdapter(mealTypeAdapter);
        postMonthThirdSp.setAdapter(mealTypeAdapter);

        mealPricingSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    if (breakFast == "1") {
                        postMonthFirstSpLay.setVisibility(View.GONE);
                        breakfastLay.setVisibility(View.VISIBLE);
                    } else {
                        postMonthFirstSpLay.setVisibility(View.GONE);
                        breakfastLay.setVisibility(View.GONE);
                    }
                    if (lunch == "1") {
                        postMonthSecondSpLay.setVisibility(View.GONE);
                        lunchLay.setVisibility(View.VISIBLE);
                    } else {
                        postMonthSecondSpLay.setVisibility(View.GONE);
                        lunchLay.setVisibility(View.GONE);
                    }
                    if (dinner == "1") {
                        postMonthThirdSpLay.setVisibility(View.GONE);
                        dinnerLay.setVisibility(View.VISIBLE);
                    } else {
                        postMonthThirdSpLay.setVisibility(View.GONE);
                        dinnerLay.setVisibility(View.GONE);
                    }

                    melTypePriceType = position;

                } else {

                    if (breakFast == "1") {
                        postMonthFirstSpLay.setVisibility(View.VISIBLE);
                    } else {
                        postMonthFirstSpLay.setVisibility(View.GONE);
                    }

                    if (lunch == "1") {
                        postMonthSecondSpLay.setVisibility(View.VISIBLE);
                    } else {
                        postMonthSecondSpLay.setVisibility(View.GONE);
                    }
                    if (dinner == "1") {
                        postMonthThirdSpLay.setVisibility(View.VISIBLE);
                    } else {
                        postMonthThirdSpLay.setVisibility(View.GONE);
                    }

                    melTypePriceType = position;
                    lunchLay.setVisibility(View.GONE);
                    breakfastLay.setVisibility(View.GONE);
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