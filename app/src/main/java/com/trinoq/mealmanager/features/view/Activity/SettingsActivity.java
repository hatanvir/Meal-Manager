package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;

import com.trinoq.mealmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.generallinearLayout)
    LinearLayout general;
    @BindView(R.id.accountlinearLayout)
    LinearLayout  account;
    @BindView(R.id.notificationlinearLayout)
    LinearLayout  notification;
    @BindView(R.id.aboutlinearLayout)
    LinearLayout about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(SettingsActivity.this);


        toolbar.setTitle("      Settings");
        //toolbar.setTitleTextColor(000000);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(Html.fromHtml("<font color='#353F40'>"+"Settings"+"</font>"));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbarColor));

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this,GeneralActivity.class));
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this,AccountActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this,NotificationActivity.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this,AboutActivity.class));
            }
        });
    }
}
