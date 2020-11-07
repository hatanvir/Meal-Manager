package com.trinoq.mealmanager.features.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.view.fragments.HomeFragment;
import com.trinoq.mealmanager.features.view.fragments.NotificationFragment;
import com.trinoq.mealmanager.features.view.fragments.SettingFragment;
import com.trinoq.mealmanager.features.view.fragments.ShoppingFragment;
import com.trinoq.mealmanager.features.view.fragments.SignUpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    SharedPreferences myPreferences;
    SharedPreferences.Editor preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferences=getSharedPreferences("MyPreferences",MODE_PRIVATE);
        preferences=getSharedPreferences("MyPreferences",MODE_PRIVATE).edit();
            if (myPreferences.getString("theme","").equals("true")){
                setTheme(R.style.LightTheme);
            }
            else {
                setTheme(R.style.AppTheme);
            }

        setContentView(R.layout.activity_test);
        ButterKnife.bind(TestActivity.this);

        if (myPreferences.getInt("check",0)==1){
            setFragment(new SettingFragment());
            preferences.putInt("check",0);
            preferences.commit();
            bottomNavigationView.setSelectedItemId(R.id.setting);
        }

       else {
            homeFragment=new HomeFragment();
            setFragment(homeFragment);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        setFragment(new HomeFragment());
                        break;
                    case R.id.shopping:
                        setFragment(new ShoppingFragment());
                        break;
                    case R.id.notification:
                        setFragment(new NotificationFragment());
                        break;
                    case R.id.setting:
                        setFragment(new SettingFragment());
                        break;
                }

                return true;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
