package com.trinoq.mealmanager.features.model.phoneAuth;

import android.content.Context;

import com.trinoq.mealmanager.common.AutoAuthRequestCompleteListener;
import com.trinoq.mealmanager.common.ManualAuthCompleteListener;

public interface PhoneAuthModel {
    void getAutoVerification(String phoneNum, Context context, AutoAuthRequestCompleteListener phoneAuthRequestCompleteListener);
    void getManualVerification(String code, ManualAuthCompleteListener manualAuthCompleteListener);
}
