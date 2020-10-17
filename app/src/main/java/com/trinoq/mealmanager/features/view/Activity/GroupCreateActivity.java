package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.view.fragments.SignUpFragment;
import com.trinoq.mealmanager.features.view.fragments.groupCreation.GroupDetailsFragment;

public class GroupCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creat);
        setfragment(new GroupDetailsFragment());
    }

    private void setfragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.groupCreateViewContainer, fragment)
                .commit();
    }
}