package com.trinoq.mealmanager.features.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GroupDetailsActivity extends AppCompatActivity {

    @BindView(R.id.groupNameTv)
    TextView groupNameTv;
    @BindView(R.id.phoneNumberTv)
    TextView phoneNumberTv;
    @BindView(R.id.adminNameTv)
    TextView adminNameTv;
    @BindView(R.id.totalGroupmembersTv)
    TextView totalmembersTv;
    @BindView(R.id.mealPricingTv)
    TextView mealPricingTv;
    @BindView(R.id.mealtypeTv)
    TextView mealtypeTv;
    @BindView(R.id.cookingtypeTv)
    TextView cookingtypeTv;
    @BindView(R.id.shoppingtypeTv)
    TextView shoppingtypeTv;
    @BindView(R.id.gruopcreateddateTV)
    TextView groupcreateddateTv;
    @BindView(R.id.clearBt)
    ImageView clearBt;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        ButterKnife.bind(GroupDetailsActivity.this);

        Intent intent=getIntent();
        position=intent.getIntExtra("position",1);
        groupNameTv.setText(Utils.groupInformations.get(position).getGroupName());
        phoneNumberTv.setText(Utils.groupInformations.get(position).getPhoneNumber());
        adminNameTv.setText(Utils.groupInformations.get(position).getAdminName());
        mealtypeTv.setText(Utils.groupInformations.get(position).getMealtype());
        cookingtypeTv.setText(Utils.groupInformations.get(position).getCookingtype());
        shoppingtypeTv.setText(Utils.groupInformations.get(position).getShoppingtype());
        groupcreateddateTv.setText(Utils.groupInformations.get(position).getGroupcreateddate());
        totalmembersTv.setText(Utils.groupInformations.get(position).getTotalmembers());
        mealPricingTv.setText(Utils.groupInformations.get(position).getMealpricing());

        clearBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
