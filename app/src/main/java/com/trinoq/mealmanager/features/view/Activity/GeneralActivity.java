package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.LightTheme);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_general);
        ButterKnife.bind(GeneralActivity.this);
        themeSwC.setOnCheckedChangeListener(OnCheckedChangeListener());

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void setThem(){
        themeSwC.setOnCheckedChangeListener(OnCheckedChangeListener());
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
            setTheme(R.style.LightTheme);
            Toast.makeText(GeneralActivity.this, "Button enabled!", Toast.LENGTH_SHORT).show();
        } else {
            setTheme(R.style.AppTheme);
            Toast.makeText(GeneralActivity.this, "Button disabled!", Toast.LENGTH_SHORT).show();
        }
    }
}
