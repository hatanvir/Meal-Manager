package com.trinoq.mealmanager.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;
import com.trinoq.mealmanager.features.model.register.RegisterModel;

import okhttp3.ResponseBody;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<String> registerSuccess = new MutableLiveData<String>();
    public MutableLiveData<String> registerFailed = new MutableLiveData<String>();

    public void register(RegisterRequest registerRequest, RegisterModel model){
        model.register(registerRequest, new RequestCompleteListener<ResponseBody>() {
            @Override
            public void OnSuccessListener(ResponseBody data) {
                registerSuccess.postValue("Success");
            }

            @Override
            public void OnFailedListener(String error) {
                registerFailed.postValue(error);
            }
        });
    }
}
