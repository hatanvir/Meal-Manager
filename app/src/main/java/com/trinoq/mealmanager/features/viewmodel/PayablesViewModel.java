package com.trinoq.mealmanager.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.Payables.PayablesModel;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;

import okhttp3.ResponseBody;

public class PayablesViewModel extends ViewModel {
    public MutableLiveData<ResponseBody> payablesCreateSuccess = new MutableLiveData<>();
    public MutableLiveData<String> payablesCreateFailed = new MutableLiveData<>();

    public void payablesRequest(PayablesRequest payablesRequest, PayablesModel model){
        model.payablesRequest(payablesRequest, new RequestCompleteListener<ResponseBody>() {
            @Override
            public void OnSuccessListener(ResponseBody data) {
                payablesCreateSuccess.postValue(data);
            }

            @Override
            public void OnFailedListener(String error) {
                payablesCreateFailed.postValue(error);
            }
        });
    }
}
