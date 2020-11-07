package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.trinoq.mealmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralActivity extends AppCompatActivity {

    @BindView(R.id.backBt)
    ImageView backBt;
    @BindView(R.id.themeSwC)
    SwitchCompat themeSwC;
    SharedPreferences.Editor myPreferences;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences= getSharedPreferences("MyPreferences",MODE_PRIVATE);
        if (sharedPreferences.getString("theme","").equals("true")){
            setTheme(R.style.LightTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        myPreferences=getSharedPreferences("MyPreferences",MODE_PRIVATE).edit();
        setContentView(R.layout.activity_general);
        ButterKnife.bind(GeneralActivity.this);

        if (sharedPreferences.getString("theme","").equals("true")){
            themeSwC.setChecked(true);

        }
        else {
            themeSwC.setChecked(false);
        }

        themeSwC.setOnCheckedChangeListener(OnCheckedChangeListener());

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                if (sharedPreferences.getInt("check",0)==1){
                    Intent intent = new Intent(GeneralActivity.this , TestActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });


    }

    private CompoundButton.OnCheckedChangeListener OnCheckedChangeListener() {

        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

             switch (compoundButton.getId()) {

                 case R.id.themeSwC:
                     setButtonState(b);
                     break;
             }

            }
        };

    }
    private void setButtonState(boolean state) {
        if (state) {
            myPreferences.putString("theme","true");
            if (sharedPreferences.getInt("check",0)==0){
                myPreferences.putInt("check",1);
            }
           else {
                myPreferences.putInt("check",0);
            }
            myPreferences.commit();
            finish();
            startActivity(new Intent(GeneralActivity.this,GeneralActivity.class));

            Toast.makeText(GeneralActivity.this, "Button enabled!", Toast.LENGTH_SHORT).show();
        } else {
            myPreferences.putString("theme","false");
            if (sharedPreferences.getInt("check",0)==0){
                myPreferences.putInt("check",1);
            }
            else {
                myPreferences.putInt("check",0);
            }
            myPreferences.commit();
            finish();
            startActivity(new Intent(GeneralActivity.this,GeneralActivity.class));

            Toast.makeText(GeneralActivity.this, "Button disabled!", Toast.LENGTH_SHORT).show();
        }
    }
}
