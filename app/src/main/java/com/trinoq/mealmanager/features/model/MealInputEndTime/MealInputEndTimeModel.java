package com.trinoq.mealmanager.features.model.MealInputEndTime;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;

import okhttp3.ResponseBody;

public interface MealInputEndTimeModel {
    void dailyMealInputRequest(DailyMealInputEndTimeRequest dailyMealInputEndTimeRequest, RequestCompleteListener<ResponseBody> requestCompleteListener);
}
