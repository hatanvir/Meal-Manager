package com.trinoq.mealmanager.features.viewmodel;

import android.content.Context;

import com.trinoq.mealmanager.common.AutoAuthRequestCompleteListener;
import com.trinoq.mealmanager.common.ManualAuthCompleteListener;
import com.trinoq.mealmanager.features.model.PhoneAuthModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PhoneAuthViewModel extends ViewModel {
    public MutableLiveData codeSentSuccess = new MutableLiveData<String>();
    public MutableLiveData codeSentfailed = new MutableLiveData<String>();

    public MutableLiveData verificationSuccess = new MutableLiveData<String>();
    public MutableLiveData verificationfailed = new MutableLiveData<String>();

    public MutableLiveData progressbarLoading = new MutableLiveData<Boolean>();
    //MutableLiveData progressBarLiveData = MutableLiveData<Boolean>()

   public void authInfo(String phoneNum,Context context,PhoneAuthModel phoneAuthModel){
       progressbarLoading.postValue(true);
        phoneAuthModel.getAutoVerification(phoneNum,context,new AutoAuthRequestCompleteListener() {
            @Override
            public void OnCodeSentSuccessListener(String code) {
                codeSentSuccess.postValue(code);
                progressbarLoading.postValue(false);
            }

            @Override
            public void OnCodeSentFailedListener(String error) {
                codeSentfailed.postValue(error);
                progressbarLoading.postValue(false);
            }

            @Override
            public void OnVerificationSuccessListener(String s) {
                verificationSuccess.postValue(s);
                progressbarLoading.postValue(false);
            }

            @Override
            public void OnVerificationFailedListener(String error) {
                verificationfailed.postValue(error);
                progressbarLoading.postValue(false);
            }
        });
   }
   public void manualVarifivation(String code,PhoneAuthModel phoneAuthModel){
       progressbarLoading.postValue(true);
       phoneAuthModel.getManualVerification(code, new ManualAuthCompleteListener() {
           @Override
           public void OnSuccessListener(String code) {
               verificationSuccess.postValue(code);
               progressbarLoading.postValue(false);
           }

           @Override
           public void OnFailedListener(String error) {
               verificationfailed.postValue(error);
               progressbarLoading.postValue(false);
           }
       });
   }
}
