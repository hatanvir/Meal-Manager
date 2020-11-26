package com.trinoq.mealmanager.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.MealInputEndTime.MealInputEndTimeModel;
import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;

import okhttp3.ResponseBody;

public class DailyMealInputEndTimeViewModel extends ViewModel {
    public MutableLiveData<ResponseBody> setDailyMealInputEndTimeSuccess = new MutableLiveData<>();
    public MutableLiveData<String> setDailyMealInputEndTimeFailed = new MutableLiveData<>();

    public void dailyMealInputRequest(DailyMealInputEndTimeRequest dailyMealInputEndTimeRequest, MealInputEndTimeModel model){
        model.dailyMealInputRequest(dailyMealInputEndTimeRequest, new RequestCompleteListener<ResponseBody>() {
            @Override
            public void OnSuccessListener(ResponseBody data) {
                setDailyMealInputEndTimeSuccess.postValue(data);
            }

            @Override
            public void OnFailedListener(String error) {
                setDailyMealInputEndTimeFailed.postValue(error);
            }
        });
    }
}
