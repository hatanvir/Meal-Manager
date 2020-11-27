package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.MealInputEndTime.MealInputEndTimeImpl;
import com.trinoq.mealmanager.features.model.MealInputEndTime.MealInputEndTimeModel;
import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;
import com.trinoq.mealmanager.features.viewmodel.DailyMealInputEndTimeViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class DailyMealInputEndTimeFragment extends Fragment {

    @BindView(R.id.breakfastEndTimeEt)
    EditText breakfastEt;
    @BindView(R.id.lunchEndTimeEt)
    EditText lunchEt;
    @BindView(R.id.dinnerEndTimeEt)
    EditText dinnerEt;
    @BindView(R.id.nextImage)
    ImageView nextImage;
    @BindView(R.id.stepIm)
    ImageView stepIm;
    @BindView(R.id.backBt)
    ImageView backBt;

    String h,m,s;

    MealInputEndTimeModel model;
    DailyMealInputEndTimeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily_meal_input_end_time, container, false);
        ButterKnife.bind(this, v);

        String gpId = getArguments().getString("gpId").toString();

        model = new MealInputEndTimeImpl(getActivity());
        viewModel = new ViewModelProvider(this).get(DailyMealInputEndTimeViewModel.class);

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.dailyMealInputRequest(new DailyMealInputEndTimeRequest(gpId,breakfastEt.getText().toString(),lunchEt.getText().toString(),dinnerEt.getText().toString()),model);
                viewModel.setDailyMealInputEndTimeSuccess.observe(getActivity(), new Observer<ResponseBody>() {
                    @Override
                    public void onChanged(ResponseBody responseBody) {
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putString("gpId",gpId);

                        Fragment fragment = new PayablesFragment();

                        fragment.setArguments(bundle);

                        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.groupCreateViewContainer,fragment)
                                .addToBackStack("GRP_DETAILES").commit();
                    }
                });
                viewModel.setDailyMealInputEndTimeFailed.observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        setUi();
        return v;
    }

    private void setUi() {
        breakfastEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker("1");
            }
        });
        lunchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker("2");
            }
        });
        dinnerEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker("3");
            }
        });
    }

    private void timePicker(String tag) {
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);



        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay<10) h = "0"+String.valueOf(hourOfDay);
                else  h = String.valueOf(hourOfDay);

                if(minute<10) m = "0"+String.valueOf(minute);
                else  m = String.valueOf(minute);
                       switch (tag) {
                    case "1":

                        breakfastEt.setText(formattedDate+ " " +h+":"+m+":"+"00");
                        break;
                    case "2":
                        lunchEt.setText(formattedDate+ " " +h+":"+m+":"+"00");
                        break;
                    case "3":
                        dinnerEt.setText(formattedDate+ " " +h+":"+m+":"+"00");
                        break;

                }
            }
        }, hour, minutes, false).show();
    }

}