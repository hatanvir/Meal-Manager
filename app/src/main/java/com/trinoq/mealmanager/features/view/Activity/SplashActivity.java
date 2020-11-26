
package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.view.fragments.HomeFragment;
import com.trinoq.mealmanager.features.view.fragments.SignUpFragment;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {
    String currentUserPhnNum="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences=getSharedPreferences("USER_DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    try {
                        currentUserPhnNum = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhoneNumber();
                    }catch (Exception e){

                    }
                    if(currentUserPhnNum !=null){

                        startActivity(new Intent(SplashActivity.this, TestActivity.class));
                        editor.putString("userPhoneNum",currentUserPhnNum).apply();
                    }else {
                        startActivity(new Intent(SplashActivity.this, AuthenticationActivity.class));
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}