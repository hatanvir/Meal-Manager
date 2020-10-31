package com.trinoq.mealmanager.features.view.fragments.groupCreation;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.trinoq.mealmanager.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily_meal_input_end_time, container, false);
        ButterKnife.bind(this, v);

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getActivity().getSupportFragmentManager().popBackStack();
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

        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                switch (tag) {
                    case "1":
                        breakfastEt.setText(hourOfDay + " : " + minute);
                        break;
                    case "2":
                        lunchEt.setText(hourOfDay + " : " + minute);
                        break;
                    case "3":
                        dinnerEt.setText(hourOfDay + " : " + minute);
                        break;

                }
            }
        }, hour, minutes, false).show();
    }
}