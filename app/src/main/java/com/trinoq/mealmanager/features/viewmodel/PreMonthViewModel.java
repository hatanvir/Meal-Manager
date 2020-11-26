package com.trinoq.mealmanager.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.PreMonth.PreMonthModel;
import com.trinoq.mealmanager.features.model.pojo.request.PremonthRequest;

import okhttp3.ResponseBody;

public class PreMonthViewModel extends ViewModel {
    public MutableLiveData<ResponseBody> setPreMothSuccess = new MutableLiveData<>();
    public MutableLiveData<String> setPreMothFailed = new MutableLiveData<>();

    public  void postMostMonthRequest(PremonthRequest premonthRequest, PreMonthModel model){
        model.preeMonthRequest(premonthRequest, new RequestCompleteListener<ResponseBody>() {
            @Override
            public void OnSuccessListener(ResponseBody data) {
                setPreMothSuccess.postValue(data);
            }

            @Override
            public void OnFailedListener(String error) {
                setPreMothFailed.postValue(error);
            }
        });
    }
}
