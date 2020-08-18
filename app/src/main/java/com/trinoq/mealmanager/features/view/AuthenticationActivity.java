package com.trinoq.mealmanager.features.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.view.fragments.SignUpFragment;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        setfragment(new SignUpFragment());
    }

    private void setfragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.authenticationContainer, fragment)
        .commit();
    }
}
