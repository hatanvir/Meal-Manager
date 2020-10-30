package com.trinoq.mealmanager.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.PostMonth.PostMonthModel;
import com.trinoq.mealmanager.features.model.pojo.request.PostMonthRequest;

import okhttp3.ResponseBody;

public class PostMonthViewModel extends ViewModel {
    public MutableLiveData<ResponseBody> setPostMothSuccess = new MutableLiveData<>();
    public MutableLiveData<String> setPostMothFailed = new MutableLiveData<>();

    public void postMostMonthRequest(PostMonthRequest postMonthRequest, PostMonthModel model){
        model.postMostMonthRequest(postMonthRequest, new RequestCompleteListener<ResponseBody>() {
            @Override
            public void OnSuccessListener(ResponseBody data) {
                setPostMothSuccess.postValue(data);
            }

            @Override
            public void OnFailedListener(String error) {
                setPostMothFailed.postValue(error);
            }
        });
    }
}
