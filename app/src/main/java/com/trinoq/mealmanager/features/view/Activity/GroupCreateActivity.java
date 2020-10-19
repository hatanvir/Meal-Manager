package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.view.fragments.SignUpFragment;
import com.trinoq.mealmanager.features.view.fragments.groupCreation.DailyMealInputEndTimeFragment;
import com.trinoq.mealmanager.features.view.fragments.groupCreation.GroupDetailsFragment;
import com.trinoq.mealmanager.features.view.fragments.groupCreation.MealPricingFragment;

import butterknife.BindView;

public class GroupCreateActivity extends AppCompatActivity {

    @BindView(R.id.groupInfoContainer)
    FrameLayout groupInfoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creat);
        /*setfragment(new GroupDetailsFragment());*/
        /*setfragment(new MealPricingFragment());*/
        setfragment(new DailyMealInputEndTimeFragment());
    }

    private void setfragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.groupInfoContainer, fragment)
                .commit();
    }
}